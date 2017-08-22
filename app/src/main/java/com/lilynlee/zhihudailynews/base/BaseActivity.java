package com.lilynlee.zhihudailynews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.lilynlee.zhihudailynews.interfaze.ISkinChangedListener;
import com.lilynlee.zhihudailynews.skin.SkinManager;
import com.lilynlee.zhihudailynews.skin.attr.SkinAttr;
import com.lilynlee.zhihudailynews.skin.attr.SkinAttrSupport;
import com.lilynlee.zhihudailynews.skin.attr.SkinView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/3/6.
 */

public class BaseActivity extends AppCompatActivity implements ISkinChangedListener{
    static final Class<?>[] sConstructorSignature = new Class[]{
            Context.class, AttributeSet.class};
    private static final Map<String, Constructor<? extends View>> sConstructorMap
            = new ArrayMap<>();
    private final Object[] mConstructorArgs = new Object[2];

    private Method mCreateViewMethod = null;
    static final Class<?>[] sCreateViewSignature = new Class[]{View.class,String.class,Context.class,AttributeSet.class};
    private final Object[] mCreateViewArgs = new Object[4];

    @Override
    public void onCreate(Bundle savedInstanceState) {

        SkinManager.getInstance().registerListener(this);

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflaterCompat.setFactory(mInflater, new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

                //系统有没有使用setFactory
                //完成appcompat factory的工作
                //如果Tag是EdiText 我们要生成一个AppCompatEditText的View
                AppCompatDelegate delegate = getDelegate();
                View view = null;
                List<SkinAttr> skinAttrs = null;

                try {
                    if (mCreateViewMethod == null){
                        mCreateViewMethod = delegate.getClass().getMethod("createView",sCreateViewSignature);
                    }
                    mCreateViewArgs[0] = parent;
                    mCreateViewArgs[1] = name;
                    mCreateViewArgs[2] = context;
                    mCreateViewArgs[3] = attrs;

                    view = (View) mCreateViewMethod.invoke(delegate,mCreateViewArgs);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                skinAttrs = SkinAttrSupport.getSkinAttrs(attrs,context);
                if (skinAttrs.isEmpty()){
                    //没有任何属性是以skin_开头的
                    //也就是说这个view的属性不需要换肤
                    return null;
                }

                //以上是appcompat关注的view的创建
                //还有一些view是appcompat不关注的，那么createViewFromTag是照搬V7包下的AppCompatView的方法来做
                if (view == null){
                    view = createViewFromTag(context,name,attrs);
                }

                if (view != null){
                    //在这里可以构建我们的SkinView了
                    injectSkin(view,skinAttrs);
                }
                return view;
            }
        });
        super.onCreate(savedInstanceState);
    }

    private void injectSkin(View view, List<SkinAttr> skinAttrs) {
        List<SkinView>  skinViews = SkinManager.getInstance().getSkinViews(this);
        if (skinViews == null){
            skinViews = new ArrayList<SkinView>();
            SkinManager.getInstance().addSkinView(this,skinViews);
        }
        skinViews.add(new SkinView(view,skinAttrs));

        //当前是否需要自动换肤,如果需要则换肤
        if (SkinManager.getInstance().needChangeSkin()){
            SkinManager.getInstance().skinChange(this);
        }
    }


    private View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }

        try {
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attrs;

            if (-1 == name.indexOf('.')) {
                return createView(context,name,"android.widget.");
            } else {
                return createView(context, name, null);
            }
        } catch (Exception e) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null;
        } finally {
            // Don't retain references on context.
            mConstructorArgs[0] = null;
            mConstructorArgs[1] = null;
        }
    }

    private View createView(Context context, String name, String prefix)
            throws ClassNotFoundException, InflateException {
        Constructor<? extends View> constructor = sConstructorMap.get(name);

        try {
            if (constructor == null) {
                // Class not found in the cache, see if it's real, and try to add it
                Class<? extends View> clazz = context.getClassLoader().loadClass(
                        prefix != null ? (prefix + name) : name).asSubclass(View.class);

                constructor = clazz.getConstructor(sConstructorSignature);
                sConstructorMap.put(name, constructor);
            }
            constructor.setAccessible(true);
            return constructor.newInstance(mConstructorArgs);
        } catch (Exception e) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null;
        }
    }

    /**
     * 换肤完成的回调方法
     */
    @Override
    public void onSkinChanged() {
        Toast.makeText(this,"换肤成功", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        SkinManager.getInstance().unRegisterListener(this);
        super.onDestroy();
    }
}

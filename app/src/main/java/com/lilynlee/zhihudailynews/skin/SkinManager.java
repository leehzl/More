package com.lilynlee.zhihudailynews.skin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.lilynlee.zhihudailynews.interfaze.ISkinChangedListener;
import com.lilynlee.zhihudailynews.interfaze.ISkinChangingCallback;
import com.lilynlee.zhihudailynews.skin.attr.SkinView;
import com.lilynlee.zhihudailynews.util.PrefUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：Lilynlee on 2017/3/6
 * 邮箱：1132860085@qq.com
 */

public class SkinManager {
    private Context mContext;
    private static SkinManager sInstance;
    private ResourcesManager mResourcesManager;
    private List<ISkinChangedListener> mListeners = new ArrayList<ISkinChangedListener>();
    private Map<ISkinChangedListener,List<SkinView>> mSkinViewMaps = new HashMap<ISkinChangedListener,List<SkinView>>();

    private PrefUtils mPrefUtils;

    private String mCurPath;
    private String mCurPkg;
    private String mSuffix;

    private SkinManager() {

    }

    public static SkinManager getInstance(){
        if (sInstance == null){
            synchronized (SkinManager.class){
                if (sInstance == null){
                    sInstance = new SkinManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context){
        mContext = context.getApplicationContext();
        mPrefUtils = new PrefUtils(mContext);

        try {
            String pluginPath = mPrefUtils.getPluginPath();
            String pluginPkg = mPrefUtils.getPluginPkg();
            mSuffix = mPrefUtils.getSuffix();
            File file = new File(pluginPath);
            if (file.exists()){
                loadPlugin(pluginPath,pluginPkg);
            }

        } catch (Exception e) {
            e.printStackTrace();
            mPrefUtils.clear();
        }
    }

    private void loadPlugin(String skinPluginPath, String skinPluginPkg) throws Exception{
        if (skinPluginPath.equals(mCurPath)&&skinPluginPkg.equals(mCurPkg)){
            return;
        }
        AssetManager assetManager = AssetManager.class.newInstance();
        Method addAssetPathmethod = assetManager.getClass().getMethod("addAssetPath",String.class);
        addAssetPathmethod.invoke(assetManager, skinPluginPath);

        Resources superResource = mContext.getResources();

        Resources resources = new Resources(assetManager,superResource.getDisplayMetrics(),superResource.getConfiguration());
        mResourcesManager = new ResourcesManager(resources,skinPluginPkg,null);
        mCurPath = skinPluginPath;
        mCurPkg = skinPluginPkg;
    }

    public ResourcesManager getResourcesManager(){
        if (!usePlugin()){
            //如果是应用内换肤
            return new ResourcesManager(mContext.getResources(),mContext.getPackageName(),mSuffix);
        }
        return mResourcesManager;
    }

    public List<SkinView> getSkinViews(ISkinChangedListener listener){
        return mSkinViewMaps.get(listener);
    }

    public void addSkinView(ISkinChangedListener listener,List<SkinView> skinViews){
        mSkinViewMaps.put(listener,skinViews);
    }

    public void registerListener(ISkinChangedListener listener){
        mListeners.add(listener);
    }

    public void unRegisterListener(ISkinChangedListener listener){
        mListeners.remove(listener);
        mSkinViewMaps.remove(listener);
    }

    /**
     * 应用内换肤，只需传入一个后缀即可
     * @param suffix 换肤的后缀
     */
    public void changeSkin(String suffix){
        clearPluginInfo();
        mSuffix = suffix;
        mPrefUtils.saveSuffix(suffix);
        notifyChangedListener();
    }

    private void clearPluginInfo() {
        mCurPath = null;
        mCurPkg = null;
        mSuffix = null;
        mPrefUtils.clear();
    }

    /**
     * 插件换肤
     * @param skinPluginPath 插件的路径
     * @param skinPluginPkg 插件的包名
     * @param callback 换肤回调
     */
    public void changeSkin(final String skinPluginPath,
                           final String skinPluginPkg, ISkinChangingCallback callback) {
        if (callback == null){
            callback = ISkinChangingCallback.DEFAULT_CALLBACK;
        }

        final ISkinChangingCallback finalCallback = callback;
        finalCallback.onStart();

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... params) {
                try {
                    loadPlugin(skinPluginPath,skinPluginPkg);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
                return 0;
            }

            @Override
            protected void onPostExecute(Integer code) {
                if (code == -1){
                    finalCallback.onError(null);
                    return;
                }
                try {
                    notifyChangedListener();
                    finalCallback.onComplete();

                    updatePluginInfo(skinPluginPath,skinPluginPkg);
                } catch (Exception e) {
                    e.printStackTrace();
                    finalCallback.onError(e);
                }
            }
        }.execute();
    }

    private void updatePluginInfo(String skinPluginPath, String skinPluginPkg) {
        mPrefUtils.savePluginPath(skinPluginPath);
        mPrefUtils.savePluginPkg(skinPluginPkg);
    }

    /**
     * 通过这个方法调用其他activity以及其他skinview进行换肤
     */
    private void notifyChangedListener() {
        for (ISkinChangedListener listener : mListeners){
            skinChange(listener);
            listener.onSkinChanged();
        }
    }

    public void skinChange(ISkinChangedListener listener) {
        List<SkinView> skinViews = mSkinViewMaps.get(listener);
        for (SkinView skinView : skinViews){
            skinView.apply();
        }
    }

    public boolean needChangeSkin() {
        return usePlugin() || useSuffix();
    }

    private boolean usePlugin(){
        return mCurPath!=null && !mCurPath.trim().equals("");
    }

    private boolean useSuffix(){
        return mSuffix!=null&& !mSuffix.trim().equals("");
    }
}

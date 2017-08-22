package com.lilynlee.zhihudailynews.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lilynlee.zhihudailynews.skin.ResourcesManager;
import com.lilynlee.zhihudailynews.skin.SkinManager;

/**
 * 作者：Lilynlee on 2017/3/6
 * 邮箱：1132860085@qq.com
 */

public enum  SkinAttrType {
    BACKGROUND("background") {
        @Override
        public void apply(View view, String resName) {
            ColorStateList colorStateList = getResourceManager().getColorByResName(resName);
            if (colorStateList != null) {
                view.setBackgroundTintList(colorStateList);
            }
        }
    },SRC("src") {
        @Override
        public void apply(View view, String resName) {
            Drawable drawable = getResourceManager().getDrawableByResName(resName);
            if (view instanceof ImageView){
                ImageView imageView = (ImageView) view;
                if (drawable != null)
                    imageView.setImageDrawable(drawable);
            }
        }
    },TEXT_COLOR("textColor") {
        @Override
        public void apply(View view, String resName) {
            ColorStateList colorStateList = getResourceManager().getColorByResName(resName);
            if (view instanceof TextView){
                TextView textView = (TextView) view;
                if (colorStateList != null)
                textView.setTextColor(colorStateList);
            }
        }
    },BACKGROUND_TINK("backgroundTint"){
        @Override
        public void apply(View view, String resName) {
            Log.d("111", "apply: ");
            ColorStateList colorStateList = getResourceManager().getColorByResName(resName);
            if (colorStateList != null) {
                view.setBackgroundTintList(colorStateList);
            }
        }
    };

    String resType;


    public String getResType() {
        return resType;
    }

    SkinAttrType(String type){
        resType = type;
    }

    public abstract void apply(View view, String resName);

    public ResourcesManager getResourceManager(){
        return SkinManager.getInstance().getResourcesManager();
    }
}

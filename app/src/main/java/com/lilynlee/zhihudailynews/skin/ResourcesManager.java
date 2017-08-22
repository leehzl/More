package com.lilynlee.zhihudailynews.skin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * Created by dell on 2017/3/6.
 */

public class ResourcesManager {
    private Resources mResources;
    private String mPkgName;

    private String mSuffix;

    public ResourcesManager(Resources resources, String pkgName, String suffix) {
        mResources = resources;
        mPkgName = pkgName;
        if (suffix == null) {
            suffix = "";
        }
        mSuffix = suffix;
    }

    public Drawable getDrawableByResName(String name) {
        name = appendSuffix(name);
        try {
            return mResources.getDrawable(mResources.getIdentifier(name, "drawable", mPkgName));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String appendSuffix(String name) {
        if (!TextUtils.isEmpty(mSuffix)) {
            name = name + "_" + mSuffix;
        }
        return name;
    }

    public ColorStateList getColorByResName(String name) {
        name = appendSuffix(name);
        try {
            return mResources.getColorStateList(mResources.getIdentifier(name, "color", mPkgName));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

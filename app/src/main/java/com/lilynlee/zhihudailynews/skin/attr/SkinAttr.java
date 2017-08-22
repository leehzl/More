package com.lilynlee.zhihudailynews.skin.attr;

import android.util.Log;
import android.view.View;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * 作者：Lilynlee on 2017/3/6
 * 邮箱：1132860085@qq.com
 */

public class SkinAttr {
    private String mResName;
    private SkinAttrType mType;


    public SkinAttr(String resName, SkinAttrType type) {
        this.mResName = resName;
        this.mType = type;
    }

    public String getResName() {
        return mResName;
    }

    public SkinAttrType getType() {
        return mType;
    }

    public void setResName(String resName) {
        this.mResName = resName;
    }

    public void setType(SkinAttrType type) {
        this.mType = type;
    }

    public void apply(View view) {
        mType.apply(view, mResName);
    }
}

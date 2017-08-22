package com.lilynlee.zhihudailynews.skin.attr;

import android.view.View;

import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/6
 * 邮箱：1132860085@qq.com
 */

public class SkinView {
    private View mView;
    private List<SkinAttr> mAttrs;

    public SkinView(View view, List<SkinAttr> attrs){
        mView = view;
        mAttrs = attrs;
    }

    public View getView() {
        return mView;
    }

    public List<SkinAttr> getAttrs() {
        return mAttrs;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void setAttrs(List<SkinAttr> attrs) {
        this.mAttrs = attrs;
    }

    public void apply(){
        for (SkinAttr attr : mAttrs){
            attr.apply(mView);
        }
    }
}

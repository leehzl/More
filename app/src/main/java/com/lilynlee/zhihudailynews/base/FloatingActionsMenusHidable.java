package com.lilynlee.zhihudailynews.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * 作者：Lilynlee on 2017/3/23
 * 邮箱：1132860085@qq.com
 * 由于Github上的FabMenu没有隐藏，所以需要继承该FabMenu
 * 编写show和hide函数实现隐藏和显示
 */

public class FloatingActionsMenusHidable extends FloatingActionsMenu {

    private boolean isShown = true;
    private static final int ANIM_DURATION = 200;
    private boolean mVisible = false;

    public FloatingActionsMenusHidable(Context context) {
        super(context);
    }

    public FloatingActionsMenusHidable(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingActionsMenusHidable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void show(boolean isVisible){
        mVisible = isVisible;
        int translationX = isVisible ? 0 : (getWidth()/2)*getMarginRight();
        this.animate().translationX(translationX).setDuration(ANIM_DURATION).start();
    }

    public int getMarginRight() {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams){
            marginBottom = ((MarginLayoutParams) layoutParams).rightMargin;
        }
        return marginBottom;
    }

    public boolean getVisible(){
        return mVisible;
    }

    public boolean isShown(){
        return isShown;
    }

    public void hide(){
       show(false);
    }

    public void show(){
        show(true);
    }

}

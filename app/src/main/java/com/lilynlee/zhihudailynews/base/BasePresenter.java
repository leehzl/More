package com.lilynlee.zhihudailynews.base;

/**
 * Created by dell on 2017/2/26.
 */

public interface BasePresenter {
    /**
     * 获取数据并改变界面显示，调用时机为Fragment的OnResume中
     */
    void start();
}

package com.lilynlee.zhihudailynews.base;

import android.view.View;

/**
 * Created by dell on 2017/2/26.
 */

public interface BaseView<T> {
    //为View设置Presenter
    void setPresenter(T presenter);

    //初始化界面控件
    void initViews(View view);
}

package com.lilynlee.zhihudailynews.app;

import android.app.Application;

import com.lilynlee.zhihudailynews.skin.SkinManager;

/**
 * Created by dell on 2017/2/27.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}

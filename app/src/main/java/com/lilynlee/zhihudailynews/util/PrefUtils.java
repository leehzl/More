package com.lilynlee.zhihudailynews.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.lilynlee.zhihudailynews.Const.Const;

/**
 * Created by dell on 2017/3/6.
 */

public class PrefUtils {
    private Context mContext;

    public PrefUtils(Context context){
        this.mContext = context;
    }

    public void savePluginPath(String path){
        SharedPreferences sp = mContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        sp.edit();
        sp.edit().putString(Const.KEY_PLUGIN_PATH,path).apply();
    }

    public void savePluginPkg(String pkg){
        SharedPreferences sp = mContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        sp.edit();
        sp.edit().putString(Const.KEY_PLUGIN_PKG,pkg).apply();
    }

    public String getPluginPath(){
        SharedPreferences sp = mContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(Const.KEY_PLUGIN_PATH,"");
    }

    public String getPluginPkg(){
        SharedPreferences sp = mContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(Const.KEY_PLUGIN_PKG,"");
    }

    public void clear() {
        SharedPreferences sp = mContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    public void saveSuffix(String suffix){
        SharedPreferences sp = mContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        sp.edit();
        sp.edit().putString(Const.KEY_SUFFIX,suffix).apply();
    }

    public String getSuffix(){
        SharedPreferences sp = mContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(Const.KEY_SUFFIX,"");
    }
}

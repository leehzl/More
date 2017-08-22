package com.lilynlee.zhihudailynews.http;

import com.lilynlee.zhihudailynews.Const.Const;
import com.lilynlee.zhihudailynews.bean.DoubanEvents;
import com.lilynlee.zhihudailynews.http.service.DoubanEventHttpService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Lilynlee on 2017/3/31
 * 邮箱：1132860085@qq.com
 */

public class DoubanEventHttpManager {
    private volatile static DoubanEventHttpManager INSTANCE;
    private DoubanEventHttpService mService;

    private DoubanEventHttpManager(){

    }

    public static DoubanEventHttpManager getInstance(){
        if (INSTANCE == null){
            synchronized (DoubanEventHttpManager.class){
                if (INSTANCE == null){
                    INSTANCE = new DoubanEventHttpManager();
                }
            }
        }
        return INSTANCE;
    }

    public DoubanEventHttpService getService(){
        if (mService == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Const.DOUBAN_EVENT)
                    .build();
            mService = retrofit.create(DoubanEventHttpService.class);
        }
        return mService;
    }

    public Observable<DoubanEvents> getDoubanEvents(String city, String type, int count, int start){
        Observable<DoubanEvents> observable = getService().getDoubanEvents(city,type,count,start);
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException());
    }
}

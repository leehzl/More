package com.lilynlee.zhihudailynews.http;

import com.lilynlee.zhihudailynews.Const.Const;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyNews;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStory;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStoryDetail;
import com.lilynlee.zhihudailynews.http.service.ZhihuHttpService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者：Lilynlee on 2017/3/19
 * 邮箱：1132860085@qq.com
 */

public class ZhihuDailyHttpManager {

    private volatile static ZhihuDailyHttpManager INSTANCE;
    private static ZhihuHttpService mService;

    private ZhihuDailyHttpManager(){}

    public static ZhihuDailyHttpManager getInstance(){
        if (INSTANCE == null){
            synchronized (ZhihuDailyHttpManager.class){
                if (INSTANCE == null){
                    INSTANCE = new ZhihuDailyHttpManager();
                }
            }
        }
        return INSTANCE;
    }

    public static ZhihuHttpService getService() {
        if (mService == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Const.ZHIHU_DAILY_NEWS)
                    .build();

            mService = retrofit.create(ZhihuHttpService.class);
        }
        return mService;
    }

    public Observable<ZhihuDailyStory> getNews(String date){

        Observable<ZhihuDailyNews> observable = getService().getZhihuDailyNews(date);

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException())
                .map(new Func1<ZhihuDailyNews, List<ZhihuDailyStory>>() {
                    @Override
                    public List<ZhihuDailyStory> call(ZhihuDailyNews zhihuDailyNews) {
                        return zhihuDailyNews.getStories();
                    }
                })
                .flatMap(new Func1<List<ZhihuDailyStory>, Observable<ZhihuDailyStory>>() {
                    @Override
                    public Observable<ZhihuDailyStory> call(List<ZhihuDailyStory> zhihuStoryBeen) {
                        return Observable.from(zhihuStoryBeen);
                    }
                });


    }

    public Observable<ZhihuDailyStoryDetail> getStory(String id){

        Observable<ZhihuDailyStoryDetail> observable = getService().getZhihuDailyStory(id);

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException());
    }

    public void getLastest(){

    }

    public void getLongComments(String id){

    }

    public void getShortComments(String id){

    }
}

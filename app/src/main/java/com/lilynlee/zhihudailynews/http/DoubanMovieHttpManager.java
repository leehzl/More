package com.lilynlee.zhihudailynews.http;

import com.lilynlee.zhihudailynews.Const.Const;
import com.lilynlee.zhihudailynews.bean.DoubanMovieDescribe;
import com.lilynlee.zhihudailynews.bean.DoubanMovies;
import com.lilynlee.zhihudailynews.http.service.DoubanMovieHttpService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Lilynlee on 2017/3/24
 * 邮箱：1132860085@qq.com
 */

public class DoubanMovieHttpManager {

    private volatile static DoubanMovieHttpManager INSTANCE;
    private static DoubanMovieHttpService mService;

    private DoubanMovieHttpManager(){

    }

    public static DoubanMovieHttpManager getInstance(){
        if (INSTANCE == null){
            synchronized (DoubanMovieHttpManager.class){
                if (INSTANCE == null){
                    INSTANCE = new DoubanMovieHttpManager();
                }
            }
        }
        return INSTANCE;
    }

    public static DoubanMovieHttpService getService(){
        if (mService == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Const.DOUBAN_MOVIE)
                    .build();

            mService = retrofit.create(DoubanMovieHttpService.class);
        }
        return mService;
    }

    /**
     * 获取正在热映的电影数据
     * 传入的参数是url中的参数
     * @param city 获取哪个城市的热映电影
     * @param count 返回热映电影的数量
     * @param start api中热映电影的index
     * @return
     */
    public Observable<DoubanMovies> getDoubanMovieInTheater(String city, int count, int start){

        Observable<DoubanMovies> observable = getService()
                .getDoubanMoiveInTheater(city,count,start);

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException());
    }

    /**
     * 获取即将上映的电影数据
     * 传入的参数是url中的参数
     * @param city 获取哪个城市的热映电影
     * @param count 返回热映电影的数量
     * @param start api中热映电影的index
     * @return
     */
    public Observable<DoubanMovies> getDoubanMovieComingSoon(String city, int count, int start){
        Observable<DoubanMovies> observable = getService()
                .getDoubanMovieComingSoon(city,count,start);

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException());
    }

    /**
     * 获取电影的描述信息
     * @param id 电影的id
     * @return
     */
    public Observable<DoubanMovieDescribe> getDoubanMovieDescribe(String id){
        Observable<DoubanMovieDescribe> observable = getService()
                .getDoubanMovieDescribe(id);

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException());
    }

    /**
     * 获取豆瓣电影的照片
     * 因为没有豆瓣api权限，采用解析html的方法去获取照片的url
     * @param id 电影的id
     * @return htnml
     */
    public Observable<String> getDoubanMoviePhotos(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Const.DOUBAN_MOVIE_DETAIL)
                .build();

        DoubanMovieHttpService service = retrofit.create(DoubanMovieHttpService.class);

        Observable<String> observable = service.getDoubanMoviePhotos(id);

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException());
    }

    /**
     * 获取豆瓣电影的评论
     * 因为没有豆瓣api权限，采用解析html的方法去获取评论数据
     * @param id 电影的id
     * @return htnml
     */
    public Observable<String> getDoubanMovieComments(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Const.DOUBAN_MOVIE_DETAIL)
                .build();

        DoubanMovieHttpService service = retrofit.create(DoubanMovieHttpService.class);

        Observable<String> observable = service.getDoubanMovieComments(id);

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException());
    }
}

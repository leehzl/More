package com.lilynlee.zhihudailynews.homepage.doubanmovie;

import android.content.Context;
import android.content.Intent;

import com.lilynlee.zhihudailynews.bean.BeanType;
import com.lilynlee.zhihudailynews.bean.DoubanMovie;
import com.lilynlee.zhihudailynews.bean.DoubanMovies;
import com.lilynlee.zhihudailynews.detail.DetailActivity;
import com.lilynlee.zhihudailynews.http.DoubanMovieHttpManager;
import com.lilynlee.zhihudailynews.util.NetworkState;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 作者：Lilynlee on 2017/3/23
 * 邮箱：1132860085@qq.com
 */

public class DoubanMoviePresenter implements DoubanMovieContract.Presenter {

    private DoubanMovieContract.View view;
    private Context context;
    private ArrayList<DoubanMovie> list = new ArrayList<DoubanMovie>();

    /*保存一下参数数据*/
    private String mCity;
    private int mCount;
    private int mStart;
    private int mTotal = 99;

    /*判断是InTheater还是Comingsoon*/
    public static int mType;

    public static final int TYPE_INTHEATER = 0;
    public static final int TYPE_COMINGSOON = 1;

    public DoubanMoviePresenter(DoubanMovieContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        //loadPosts(true);
    }

    @Override
    public void loadPosts(int type, String city, int count, int start, boolean clearing) {
        mCity = city;
        mStart = start;
        mCount = count;
        mType = type;
        if (clearing){
            view.showLoading();
        }
        if (NetworkState.networkConnected(context)){
            Observable<DoubanMovies> observable;
            if (type == TYPE_INTHEATER) {
                observable = DoubanMovieHttpManager
                        .getInstance().getDoubanMovieInTheater(city,count,start);
            } else {
                observable = DoubanMovieHttpManager
                        .getInstance().getDoubanMovieComingSoon(city, count, start);
            }
            observable.map(new Func1<DoubanMovies, List<DoubanMovie>>() {
                @Override
                public List<DoubanMovie> call(DoubanMovies doubanMovieInTheater) {
                    mTotal = doubanMovieInTheater.getTotal();
                    return doubanMovieInTheater.getSubjects();
                }
            }).flatMap(new Func1<List<DoubanMovie>, Observable<DoubanMovie>>() {
                @Override
                public Observable<DoubanMovie> call(List<DoubanMovie> doubanMovies) {
                    return Observable.from(doubanMovies);
                }
            }).filter(new Func1<DoubanMovie, Boolean>() {
                @Override
                public Boolean call(DoubanMovie doubanMovie) {
                    return doubanMovie.getRating().getAverage()>0;
                }
            }).subscribe(new Subscriber<DoubanMovie>() {
                @Override
                public void onCompleted() {
                    view.stopLoading();
                    view.showResults(list);
                }

                @Override
                public void onError(Throwable e) {
                    view.stopLoading();
                    view.showError();
                }

                @Override
                public void onNext(DoubanMovie doubanMovie) {
                    list.add(doubanMovie);
                }
            });
        }
    }

    @Override
    public void refresh() {
        loadPosts(mType, mCity, mCount, mStart,true);
    }

    @Override
    public void loadMore(int type, String city, int count, int start) {
        if (start>= mTotal){
            view.noMoreData();
            return;
        }
        loadPosts(type,city,count,start,false);
    }

    @Override
    public void startDetail(int position) {
        context.startActivity(new Intent(context, DetailActivity.class)
            .putExtra("type", BeanType.TYPE_MOVIE)
            .putExtra("id",list.get(position).getId())
            .putExtra("title",list.get(position).getTitle())
            .putExtra("original_title",list.get(position).getOriginal_title())
            .putExtra("genres",getMovieGenres(list.get(position).getGenres()))
            .putExtra("stars",list.get(position).getCollect_count())
            .putExtra("average",list.get(position).getRating().getAverage())
            .putExtra("coverImg",list.get(position).getImages().getLarge()));
    }

    /**
     * 将movie的genres字符串转为String
     * @param genres
     * @return
     */
    private String getMovieGenres(List<String> genres) {
        String genresStr = "";
        for (String genre : genres){
            genresStr += genre+"、";
        }
        return genresStr.substring(0,genresStr.length()-1);
    }

    @Override
    public void clearData() {
        list.clear();
    }
}

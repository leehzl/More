package com.lilynlee.zhihudailynews.detail.doubanmovie;

import android.content.Context;

import com.lilynlee.zhihudailynews.bean.DoubanMovieDescribe;

/**
 * 作者：Lilynlee on 2017/3/24
 * 邮箱：1132860085@qq.com
 */

public class DetailDoubanMoviePresenter implements DetailDoubanMovieContract.Presenter {

    /*来自Intent的数据*/
    private String id;
    private String title;
    private String original_title;
    private String geners;
    private String stars;
    private double average;
    private String coverImg;

    private DetailDoubanMovieContract.View view;

    private Context context;

    private DoubanMovieDescribe describe;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setGeners(String geners) {
        this.geners = geners;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public DetailDoubanMoviePresenter(DetailDoubanMovieContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestData() {
        view.setTitle(title);
        view.setOriginalTitle(original_title);
        view.setGeners(geners);
        view.setStars(stars);
        view.setAverage(average);
        view.setCoverImg(coverImg);
        view.setToolbarTitle(title);
    }

    @Override
    public String getId() {
        return id;
    }
}

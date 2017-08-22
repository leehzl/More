package com.lilynlee.zhihudailynews.http.service;

import com.lilynlee.zhihudailynews.bean.ZhihuDailyNews;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStory;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStoryDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 作者：Lilynlee on 2017/3/19
 * 邮箱：1132860085@qq.com
 */

public interface ZhihuHttpService {
    @GET("news/before/{date}")
    Observable<ZhihuDailyNews> getZhihuDailyNews(@Path("date") String date);

    @GET("news/{id}")
    Observable<ZhihuDailyStoryDetail> getZhihuDailyStory(@Path("id") String id);

    @GET("story/{id}/long-comments")
    Observable<ZhihuDailyStory> getZhihuDailyStoryLongComments(@Path("id") String id);

    @GET("story/{id}/short-comments")
    Observable<ZhihuDailyStory> getZhihuDailyStoryShortComments(@Path("id") String id);
}

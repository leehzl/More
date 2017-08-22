package com.lilynlee.zhihudailynews.http.service;


import com.lilynlee.zhihudailynews.bean.DoubanMovies;
import com.lilynlee.zhihudailynews.bean.DoubanMovieDescribe;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：Lilynlee on 2017/3/23
 * 邮箱：1132860085@qq.com
 */

public interface DoubanMovieHttpService {
    @GET("in_theaters")
    Observable<DoubanMovies> getDoubanMoiveInTheater(@Query("city") String city, @Query("count") int count, @Query("start") int start);

    @GET("coming_soon")
    Observable<DoubanMovies> getDoubanMovieComingSoon(@Query("city") String city, @Query("count") int count, @Query("start") int start);

    @GET("subject/{id}")
    Observable<DoubanMovieDescribe> getDoubanMovieDescribe(@Path("id") String id);

    @GET("{id}/photos")
    Observable<String> getDoubanMoviePhotos(@Path("id") String id);

    @GET("{id}/comments")
    Observable<String> getDoubanMovieComments(@Path("id") String id);
}

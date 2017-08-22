package com.lilynlee.zhihudailynews.http.service;

import com.lilynlee.zhihudailynews.bean.DoubanEvents;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：Lilynlee on 2017/3/31
 * 邮箱：1132860085@qq.com
 */

public interface DoubanEventHttpService {
    @GET("list")
    Observable<DoubanEvents> getDoubanEvents(@Query("loc") String city, @Query("type") String type, @Query("count") int count, @Query("start") int start);
}

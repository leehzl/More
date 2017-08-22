package com.lilynlee.zhihudailynews.util;

/**
 * Created by dell on 2017/2/26.
 */

public class Api {
    //消息内容获取与离线下载
    //在最新消息中获取到的i，拼接到这个news之后，可以获得对应的json格式的数据
    public static final String ZHIHU_NEWS = "http://news-at.zhihu.com/api/4/news/";

    //国王消息
    //若要查询的2016年11月8日消息，在before后面的数字应为20161108
    //知乎日报的生日为2013年5月19日，如果before后面的数字小于20130520则获取到空消息
    public static final String ZHIHU_HISTORY = "http://news.at.zhihu.com/api/4/news/before/";

    // 获取果壳精选的文章列表,通过组合相应的参数成为完整的url
    public static final String GUOKR_ARTICLES = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";

    // 获取果壳文章的具体信息 V1
    public static final String GUOKR_ARTICLE_LINK_V1 = "http://jingxuan.guokr.com/pick/";

    // 豆瓣一刻
    // 根据日期查询消息列表
    public static final String DOUBAN_MOMENT = "https://moment.douban.com/api/stream/date/";

    // 获取豆瓣文章具体内容
    public static final String DOUBAN_ARTICLE_DETAIL = "https://moment.douban.com/api/post/";
}

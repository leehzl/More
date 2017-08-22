package com.lilynlee.zhihudailynews.util;

import com.lilynlee.zhihudailynews.bean.DoubanMovieCommentBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/26
 * 邮箱：1132860085@qq.com
 */

public class AnalyHTML {

    private static final String TAG = "AnalyHTML";
    private static AnalyHTML INSTANCE;

    private AnalyHTML() {
        //no instance
    }

    public static AnalyHTML getInstance(){
        if (INSTANCE == null){
            synchronized (AnalyHTML.class){
                if (INSTANCE == null){
                    INSTANCE = new AnalyHTML();
                }
            }
        }
        return INSTANCE;
    }

    public List<String> getPhotos(String html){
        List<String> photoUrls = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        Elements coverClass = doc.getElementsByClass("cover");
        for (int i = 0;i<coverClass.size();i++){
            photoUrls.add(coverClass.get(i).select("img").attr("src"));
        }
        return photoUrls;
    }

    public List<DoubanMovieCommentBean> getComments(String html){
        List<DoubanMovieCommentBean> commentBeans = new ArrayList<DoubanMovieCommentBean>();
        Document doc = Jsoup.parse(html,"utf-8");
        Elements commentItem = doc.getElementsByClass("comment-item");
        for (int i = 0;i<commentItem.size();i++){
            Element element = commentItem.get(i);
            String name = element.select("a").attr("title");
            String head = element.select("img").attr("src");
            String time = element.select("span.comment-time").html();
            String content = element.select("p").html().replaceAll("<(\\S*?) [^>]*>.*?</\\1>|<.*? />","");
            commentBeans.add(new DoubanMovieCommentBean(head,name,time,content));
        }
        return commentBeans;
    }
}

package com.lilynlee.zhihudailynews.bean;

/**
 * 作者：Lilynlee on 2017/3/26
 * 邮箱：1132860085@qq.com
 */

public class DoubanMovieCommentBean {
    String head;
    String name;
    String time;
    String content;

    public DoubanMovieCommentBean(String head, String name, String time, String content) {
        this.head = head;
        this.name = name;
        this.time = time;
        this.content = content;
    }

    public String getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.lilynlee.zhihudailynews.bean;

import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/22
 * 邮箱：1132860085@qq.com
 */

public class DoubanMovies {

    public int count;
    public int start;
    public int total;
    public String title;
    public List<DoubanMovie> subjects;

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

    public String getTitle() {
        return title;
    }

    public List<DoubanMovie> getSubjects() {
        return subjects;
    }
}

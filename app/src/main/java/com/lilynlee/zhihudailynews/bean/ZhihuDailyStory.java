package com.lilynlee.zhihudailynews.bean;

import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/19
 * 邮箱：1132860085@qq.com
 */

public class ZhihuDailyStory {
    /**
     * images : ["http://pic4.zhimg.com/53bcd79bc1c90ae798e69d01e83a43d7.jpg"]
     * type : 0
     * id : 9122666
     * ga_prefix : 010422
     * title : 小事 · 哎你不是那谁吗
     * multipic : true
     */

    public int type;
    public int id;
    public String ga_prefix;
    public String title;
    public boolean multipic;
    public List<String> images;

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

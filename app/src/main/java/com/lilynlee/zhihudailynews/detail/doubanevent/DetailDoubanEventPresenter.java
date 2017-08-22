package com.lilynlee.zhihudailynews.detail.doubanevent;

import android.content.Context;

/**
 * 作者：Lilynlee on 2017/4/3
 * 邮箱：1132860085@qq.com
 */

public class DetailDoubanEventPresenter implements DetailDoubanEventContract.Presenter {

    /*来自intent的数据*/
    private String title;
    private String subcategoryName;
    private String ownerName;
    private String wishCount;
    private String content;
    private String timeRange;
    private String participantCount;
    private String priceRange;
    private String image;
    private String address;

    private DetailDoubanEventContract.View view;
    private Context context;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setWishCount(String wishCount) {
        this.wishCount = wishCount;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public void setParticipantCount(String participantCount) {
        this.participantCount = participantCount;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DetailDoubanEventPresenter(DetailDoubanEventContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestData() {
        view.showLoading();
        view.setToolbarTitle(title);
        view.setAddress(address);
        view.setContent(content);
        view.setImage(image);
        view.setOwenerName(ownerName);
        view.setParticipant(participantCount);
        view.setPriceRange(priceRange);
        view.setSubcategoryName(subcategoryName);
        view.setWishCount(wishCount);
        view.stopLoading();
        view.setTitle(title);
        view.setTimeRange(timeRange);
    }
}

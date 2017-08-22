package com.lilynlee.zhihudailynews.detail.doubanevent;

import com.lilynlee.zhihudailynews.base.BasePresenter;
import com.lilynlee.zhihudailynews.base.BaseView;

/**
 * 作者：Lilynlee on 2017/4/3
 * 邮箱：1132860085@qq.com
 */

public class DetailDoubanEventContract {

    public interface View extends BaseView<Presenter>{
        void showLoading();

        void stopLoading();

        void setSubcategoryName(String subcategoryName);

        void setOwenerName(String owenerName);

        void setWishCount(String wishCount);

        void setContent(String content);

        void setTimeRange(String timeRange);

        void setParticipant(String participant);

        void setPriceRange(String priceRange);

        void setImage(String image);

        void setAddress(String address);

        void setToolbarTitle(String toolbarTitle);

        void setTitle(String title);
    }

    public interface Presenter extends BasePresenter{
        void requestData();
    }
}

package com.lilynlee.zhihudailynews.detail.doubanmovie;

import com.lilynlee.zhihudailynews.base.BasePresenter;
import com.lilynlee.zhihudailynews.base.BaseView;

/**
 * 作者：Lilynlee on 2017/3/24
 * 邮箱：1132860085@qq.com
 */

public class DetailDoubanMovieContract {
    public interface View extends BaseView<Presenter> {

        void showLoading();

        void stopLoading();

        void showLoadingError();

        void showSharingError();

        void showResult(String result);

        void showResultWithoutBody(String url);

        void showCover(String url);

        void setTitle(String title);

        void setOriginalTitle(String originalTitle);

        void setGeners(String geners);

        void setStars(String stars);

        void setAverage(double average);

        void setCoverImg(String coverImg);

        void setImageMode(boolean showImage);

        void showDeletedFromBookmarks();

        void setToolbarTitle(String title);

    }

    interface Presenter extends BasePresenter {

        void requestData();

        String getId();

    }
}

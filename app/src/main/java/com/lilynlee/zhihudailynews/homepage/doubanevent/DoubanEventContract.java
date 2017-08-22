package com.lilynlee.zhihudailynews.homepage.doubanevent;

import com.lilynlee.zhihudailynews.base.BasePresenter;
import com.lilynlee.zhihudailynews.base.BaseView;
import com.lilynlee.zhihudailynews.bean.DoubanEventItem;

import java.util.ArrayList;

/**
 * 作者：Lilynlee on 2017/3/31
 * 邮箱：1132860085@qq.com
 */

public class DoubanEventContract {
    public interface View extends BaseView<Presenter>{

        void showLoading();

        void stopLoading();

        void showLoadingError();

        void showResults(ArrayList<DoubanEventItem> list);

        void showError();

        void noMoreData();

        boolean isFabOpen();

        void closeFab();
    }

    public interface Presenter extends BasePresenter{
        /**
         * 加载更多
         * @param city
         * @param type
         * @param count
         * @param start
         * @param clearing
         */
        void loadPosts(String city, String type, int count, int start, boolean clearing);

        /**
         * 刷新数据
         */
        void refresh();

        /**
         * 加载更多文章
         * @param type
         * @param city
         * @param count
         * @param start
         */
        void loadMore(String city, String type, int count, int start);

        /**
         * 显示详情
         * @param position
         */
        void startDetail(int position);

        void clearData();

        int getTotal();
    }
}

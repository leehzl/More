package com.lilynlee.zhihudailynews.homepage.zhihudaily;

import com.lilynlee.zhihudailynews.base.BasePresenter;
import com.lilynlee.zhihudailynews.base.BaseView;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStory;

import java.util.ArrayList;

/**
 * Created by dell on 2017/2/26.
 */

public interface ZhihuDailyContract {
    interface View extends BaseView<Presenter> {
        /**
         * 显示加载或其他类型的错误
         */
        void showError();

        /**
         * 显示正在加载
         */
        void showLoading();

        /**
         * 停止加载
         */
        void stopLoading();

        /**
         * 成果获取到数据之后，在界面中显示
         * @param list
         */
        void showResults(ArrayList<ZhihuDailyStory> list);

        /**
         * 显示加载指定日期的date picker dialog
         */
        void showPickDialog();
    }

    interface Presenter extends BasePresenter {
        /**
         * 请求数据
         * @param date
         * @param clearing
         */
        void loadPosts(long date, boolean clearing);

        /**
         * 刷新数据
         */
        void refresh();

        /**
         * 加载更多文章
         * @param date
         */
        void loadMore(long date);

        /**
         * 显示详情
         * @param position
         */
        void startReading(int position);

        /**
         * 随便看看
         */
        void feelLucky();

        void clearData();
    }
}

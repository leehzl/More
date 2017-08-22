package com.lilynlee.zhihudailynews.homepage.doubanmovie;

import com.lilynlee.zhihudailynews.base.BasePresenter;
import com.lilynlee.zhihudailynews.base.BaseView;
import com.lilynlee.zhihudailynews.bean.DoubanMovie;

import java.util.ArrayList;

/**
 * 作者：Lilynlee on 2017/3/23
 * 邮箱：1132860085@qq.com
 */

public interface DoubanMovieContract {
    interface View extends BaseView<Presenter>{
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
        void showResults(ArrayList<DoubanMovie> list);

        /**
         * 没有更多电影数据了
         * 弹出一个Toast告知用户
         */
        void noMoreData();

        /**
         * 暴露一个方法这个方法，在recyclerView点击事件判断fabmenu是否打开
         */
        boolean isFabmenuOpen();

        /**
         * 让recyclerView的点击事件可以处理fabmenu关闭
         */
        void fabmenuClose();
    }

    interface Presenter extends BasePresenter{
        /**
         * 请求正在热映数据
         * @param type 是InTheater还是Comingsoon
         * @param city 所在城市
         * @param count 返回movie的数量
         * @param start api中的index
         * @param clearing 是否清除原有的数据
         */
        void loadPosts(int type, String city, int count, int start, boolean clearing);

        /**
         * 刷新数据
         */
        void refresh();

        /**
         * 加载更多
         * 参数和loadPosts的参数意思一样
         * @param type
         * @param city
         * @param count
         * @param start
         */
        void loadMore(int type, String city, int count, int start);

        /**
         * 显示详情
         * @param position
         */
        void startDetail(int position);

        void clearData();
    }
}

package com.lilynlee.zhihudailynews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lilynlee.zhihudailynews.detail.doubanmovie.DetailDoubanMovieCommentsFragment;
import com.lilynlee.zhihudailynews.detail.doubanmovie.DetailDoubanMovieDescribeFragment;
import com.lilynlee.zhihudailynews.detail.doubanmovie.DetailDoubanMoviePhotosFragment;

/**
 * 作者：Lilynlee on 2017/3/24
 * 邮箱：1132860085@qq.com
 */

public class DoubanMovieDetailAdapter extends FragmentPagerAdapter {

    private DetailDoubanMovieDescribeFragment describeFragment;
    private DetailDoubanMoviePhotosFragment photosFragment;
    private DetailDoubanMovieCommentsFragment commentsFragment;

    private String[] titles;

    private final Context context;

    public DoubanMovieDetailAdapter(FragmentManager fm,
                                    Context context,
                                    DetailDoubanMovieDescribeFragment describeFragment,
                                    DetailDoubanMoviePhotosFragment photosFragment,
                                    DetailDoubanMovieCommentsFragment commentsFragment) {
        super(fm);
        this.context = context;
        this.describeFragment = describeFragment;
        this.photosFragment = photosFragment;
        this.commentsFragment = commentsFragment;
        this.titles = new String[]{
                "简介",
                "剧照",
                "评论"
        };
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return describeFragment;
        }else if (position == 1){
            return photosFragment;
        }else {
            return commentsFragment;
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

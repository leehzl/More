package com.lilynlee.zhihudailynews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lilynlee.zhihudailynews.homepage.doubanevent.DoubanEventFragment;
import com.lilynlee.zhihudailynews.homepage.doubanmovie.DoubanMovieFragment;
import com.lilynlee.zhihudailynews.homepage.zhihudaily.ZhihuDailyFragment;

/**
 * Created by dell on 2017/2/26.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private ZhihuDailyFragment zhihuDailyFragment;
    private DoubanMovieFragment doubanMovieFragment;
    private DoubanEventFragment doubanEventFragment;

    private String[] titles;

    private final Context context;

    public MainPagerAdapter(FragmentManager fm,
                            Context context,
                            ZhihuDailyFragment zhihuDailyFragment,
                            DoubanMovieFragment doubanMovieFragment,
                            DoubanEventFragment doubanEventFragment) {
        super(fm);
        this.context = context;
        this.zhihuDailyFragment = zhihuDailyFragment;
        this.doubanMovieFragment = doubanMovieFragment;
        this.doubanEventFragment = doubanEventFragment;
        this.titles = new String[]{
                "知乎日报",
                "豆瓣电影",
                "豆瓣同城"
        };
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return zhihuDailyFragment;
        }else if (position == 1){
            return doubanMovieFragment;
        }else {
            return doubanEventFragment;
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

package com.lilynlee.zhihudailynews.detail.doubanmovie;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.adapter.DoubanMovieDetailAdapter;
import com.lilynlee.zhihudailynews.detail.DetailActivity;

/**
 * 作者：Lilynlee on 2017/3/24
 * 邮箱：1132860085@qq.com
 */

public class DetailDoubanMovieFragment extends Fragment
        implements DetailDoubanMovieContract.View{

    private Context context;
    private DoubanMovieDetailAdapter adapter;
    private DetailDoubanMovieContract.Presenter presenter;

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private DetailDoubanMovieDescribeFragment describeFragment;
    private DetailDoubanMoviePhotosFragment photosFragment;
    private DetailDoubanMovieCommentsFragment commentsFragment;

    private TextView tvAverage;
    private TextView tvTitle;
    private TextView tvOriginalTitle;
    private TextView tvGeners;
    private TextView tvStars;
    private ImageView ivCover;

    public DetailDoubanMovieFragment(){}

    public DetailDoubanMovieFragment newInstance(){
        return new DetailDoubanMovieFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getChildFragmentManager();
        manager.putFragment(outState,"detailDoubanMovieDescribeFragment",describeFragment);
        manager.putFragment(outState,"detailDoubanMoviePhotosFragment",photosFragment);
        manager.putFragment(outState,"detailDoubanMovieCommentsFragment",commentsFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        if (savedInstanceState != null){
            describeFragment = (DetailDoubanMovieDescribeFragment) getChildFragmentManager().getFragment(savedInstanceState,"detailDoubanMovieDescribeFragment");
            photosFragment = (DetailDoubanMoviePhotosFragment) getChildFragmentManager().getFragment(savedInstanceState,"detailDoubanMoviePhotosFragment");
            commentsFragment = (DetailDoubanMovieCommentsFragment) getChildFragmentManager().getFragment(savedInstanceState,"detailDoubanMovieCommentsFragment");
        }else {
            describeFragment = DetailDoubanMovieDescribeFragment.newInstance(presenter.getId());
            photosFragment = DetailDoubanMoviePhotosFragment.newInstance(presenter.getId());
            commentsFragment = DetailDoubanMovieCommentsFragment.newInstance(presenter.getId());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.douban_movie,container,false);
        initViews(view);

        //显示菜单
        setHasOptionsMenu(true);

        presenter.requestData();
        return view;
    }

    @Override
    public void setPresenter(DetailDoubanMovieContract.Presenter presenter) {
        if (presenter != null){
            this.presenter = presenter;
        }
    }

    public void initViews(final View view) {
        tvAverage = (TextView) view.findViewById(R.id.average);
        tvTitle = (TextView) view.findViewById(R.id.title);
        tvOriginalTitle = (TextView) view.findViewById(R.id.original_title);
        tvGeners = (TextView) view.findViewById(R.id.geners);
        tvStars = (TextView) view.findViewById(R.id.stars);
        ivCover = (ImageView) view.findViewById(R.id.cover);

        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsintToolbarLayout);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.argb(0,0,0,0));

        DetailActivity activity = (DetailActivity) getActivity();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        //设置离线数
        viewPager.setOffscreenPageLimit(3);
        adapter = new DoubanMovieDetailAdapter(
                getChildFragmentManager(),
                context,
                describeFragment,
                photosFragment,
                commentsFragment
        );
        viewPager.setAdapter(adapter);

        /*当改变ViewPager时候，NestedScrollView滑动到最上面*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((NestedScrollView) view.findViewById(R.id.nestedScrollView)).scrollTo(0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            getActivity().onBackPressed();
        }else if (id == R.id.action_more){
        }
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void showSharingError() {

    }

    @Override
    public void showResult(String result) {

    }

    @Override
    public void showResultWithoutBody(String url) {

    }

    @Override
    public void showCover(String url) {

    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setOriginalTitle(String originalTitle) {
        tvOriginalTitle.setText(originalTitle);
    }

    @Override
    public void setGeners(String geners) {
        tvGeners.setText(geners);
    }

    @Override
    public void setStars(String stars) {
        tvStars.setText(stars+"人看过");
    }

    @Override
    public void setAverage(double average) {
        tvAverage.setText(average+"");
    }

    @Override
    public void setCoverImg(String coverImg) {
        Glide.with(context)
                .load(coverImg)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(ivCover);
    }

    @Override
    public void setImageMode(boolean showImage) {

    }

    @Override
    public void showDeletedFromBookmarks() {

    }

    @Override
    public void setToolbarTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
    }
}

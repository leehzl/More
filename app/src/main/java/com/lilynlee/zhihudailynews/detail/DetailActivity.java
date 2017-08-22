package com.lilynlee.zhihudailynews.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.base.BaseActivity;
import com.lilynlee.zhihudailynews.bean.BeanType;
import com.lilynlee.zhihudailynews.detail.doubanevent.DetailDoubanEventContract;
import com.lilynlee.zhihudailynews.detail.doubanevent.DetailDoubanEventFragment;
import com.lilynlee.zhihudailynews.detail.doubanevent.DetailDoubanEventPresenter;
import com.lilynlee.zhihudailynews.detail.doubanmovie.DetailDoubanMovieContract;
import com.lilynlee.zhihudailynews.detail.doubanmovie.DetailDoubanMovieFragment;
import com.lilynlee.zhihudailynews.detail.doubanmovie.DetailDoubanMoviePresenter;
import com.lilynlee.zhihudailynews.detail.zhihu.DetailZhihuDailyContract;
import com.lilynlee.zhihudailynews.detail.zhihu.DetailZhihuDailyFragment;
import com.lilynlee.zhihudailynews.detail.zhihu.DetailZhihuDailyPresenter;

/**
 * 作者：Lilynlee on 2017/3/20
 * 邮箱：1132860085@qq.com
 */

public class DetailActivity extends BaseActivity {

    private static final String TAG = "DetailActivity";
    private Fragment fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        if (savedInstanceState != null){
            fragment = getSupportFragmentManager().getFragment(savedInstanceState,"detailFragment");
        }else {
            Intent intent = getIntent();
            BeanType type = (BeanType) intent.getSerializableExtra("type");
            if (type == BeanType.TYPE_ZHIHU){
                //知乎日报
                fragment = new DetailZhihuDailyFragment();
                DetailZhihuDailyPresenter presenter = new DetailZhihuDailyPresenter((DetailZhihuDailyContract.View) fragment,DetailActivity.this);
                presenter.setType((BeanType) intent.getSerializableExtra("type"));
                presenter.setId(intent.getStringExtra("id"));
                presenter.setCoverUrl(intent.getStringExtra("coverUrl"));
                presenter.setTitle(intent.getStringExtra("title"));
            }else if (type == BeanType.TYPE_MOVIE){
                //豆瓣电影
                fragment = new DetailDoubanMovieFragment();
                DetailDoubanMoviePresenter presenter = new DetailDoubanMoviePresenter((DetailDoubanMovieContract.View) fragment,DetailActivity.this);
                presenter.setId(intent.getStringExtra("id"));
                presenter.setTitle(intent.getStringExtra("title"));
                presenter.setOriginal_title(intent.getStringExtra("original_title"));
                presenter.setGeners(intent.getStringExtra("genres"));
                presenter.setStars(intent.getIntExtra("stars",0)+"");
                presenter.setAverage(intent.getDoubleExtra("average",0));
                presenter.setCoverImg(intent.getStringExtra("coverImg"));
            }else if (type == BeanType.TYPE_EVENT){
                //豆瓣同城
                Log.d(TAG, "onCreate: "+intent.getStringExtra("time_range"));
                fragment = new DetailDoubanEventFragment();
                DetailDoubanEventPresenter presenter = new DetailDoubanEventPresenter((DetailDoubanEventContract.View) fragment,DetailActivity.this);
                presenter.setTitle(intent.getStringExtra("title"));
                presenter.setSubcategoryName(intent.getStringExtra("subcategory"));
                presenter.setWishCount(intent.getStringExtra("wish_count"));
                presenter.setTimeRange(intent.getStringExtra("time_range"));
                presenter.setParticipantCount(intent.getStringExtra("participant_count"));
                presenter.setPriceRange(intent.getStringExtra("price_range"));
                presenter.setImage(intent.getStringExtra("image_lmobile"));
                presenter.setAddress(intent.getStringExtra("address"));
                presenter.setOwnerName(intent.getStringExtra("owner_name"));
                presenter.setContent(intent.getStringExtra("content"));
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment.isAdded()){
            getSupportFragmentManager().putFragment(outState,"detailFragment",fragment);
        }
    }
}

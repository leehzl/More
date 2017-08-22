package com.lilynlee.zhihudailynews.about;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.base.BaseActivity;

/**
 * 作者：Lilynlee on 2017/4/9
 * 邮箱：1132860085@qq.com
 */

public class AboutActivity extends BaseActivity {

    private Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        if (savedInstanceState != null){
            fragment = getSupportFragmentManager().getFragment(savedInstanceState,"aboutFragment");
        }else {
            //fragment = new AboutFragment();
            fragment = AboutFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment.isAdded()){
            getSupportFragmentManager().putFragment(outState,"detailFragment",fragment);
        }
    }
}

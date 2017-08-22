package com.lilynlee.zhihudailynews.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lilynlee.zhihudailynews.R;

/**
 * 作者：Lilynlee on 2017/4/9
 * 邮箱：1132860085@qq.com
 */

public class AboutFragment extends Fragment {

    public AboutFragment(){}

    public static AboutFragment newInstance(){
        return new AboutFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_layout,container,false);/*
        setHasOptionsMenu(true);
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));*/
        return view;
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
}

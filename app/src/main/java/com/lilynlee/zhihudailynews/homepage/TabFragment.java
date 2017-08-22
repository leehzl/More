package com.lilynlee.zhihudailynews.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lilynlee.zhihudailynews.R;

/**
 * Created by dell on 2017/2/26.
 */

public class TabFragment extends Fragment {


    public TabFragment(){}

    public static TabFragment newInstance(){
        return new TabFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);
        return view;
    }
}

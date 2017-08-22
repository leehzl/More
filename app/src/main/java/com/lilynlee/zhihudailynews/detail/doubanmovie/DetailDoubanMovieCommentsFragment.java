package com.lilynlee.zhihudailynews.detail.doubanmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.adapter.DoubanMovieCommentsAdapter;
import com.lilynlee.zhihudailynews.bean.DoubanMovieCommentBean;
import com.lilynlee.zhihudailynews.http.DoubanMovieHttpManager;
import com.lilynlee.zhihudailynews.util.AnalyHTML;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * 作者：Lilynlee on 2017/3/25
 * 邮箱：1132860085@qq.com
 */
@SuppressLint("ValidFragment")
public class DetailDoubanMovieCommentsFragment extends Fragment {
    private String id;
    private List<DoubanMovieCommentBean> comments = new ArrayList<DoubanMovieCommentBean>();
    private RecyclerView recyclerView;
    private DoubanMovieCommentsAdapter adapter;

    public DetailDoubanMovieCommentsFragment() {
    }

    public DetailDoubanMovieCommentsFragment(String id) {
        super();
        this.id = id;
    }

    public static DetailDoubanMovieCommentsFragment newInstance(String id) {
        return new DetailDoubanMovieCommentsFragment(id);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doubanmovie_detail_comments,container,false);
        getComments();
        initViews(view);
        return view;
    }

    private void getComments() {
        Observable<String> observable = DoubanMovieHttpManager.getInstance().getDoubanMovieComments(id);
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String html) {
                comments = AnalyHTML.getInstance().getComments(html);
                adapter = new DoubanMovieCommentsAdapter(comments,getContext());
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }
}

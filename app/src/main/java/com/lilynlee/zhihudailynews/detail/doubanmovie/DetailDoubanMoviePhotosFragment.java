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
import com.lilynlee.zhihudailynews.adapter.DoubanMoviePhotosAdapter;
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
public class DetailDoubanMoviePhotosFragment extends Fragment {

    private String id;
    private List<String> photoUrls = new ArrayList<String>();
    private RecyclerView recyclerView;
    private DoubanMoviePhotosAdapter adapter;

    public DetailDoubanMoviePhotosFragment(){

    }

    public DetailDoubanMoviePhotosFragment(String id){
        super();
        this.id = id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doubanmovie_detail_photos,container,false);
        //TODO 获取urls
        getPhotoUrls();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    public static DetailDoubanMoviePhotosFragment newInstance(String id) {
        return new DetailDoubanMoviePhotosFragment(id);
    }

    public void getPhotoUrls() {
        Observable<String> observable = DoubanMovieHttpManager.getInstance().getDoubanMoviePhotos(id);
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String html) {
                photoUrls = AnalyHTML.getInstance().getPhotos(html);
                adapter = new DoubanMoviePhotosAdapter(photoUrls,getContext());
                recyclerView.setAdapter(adapter);
            }
        });
    }
}

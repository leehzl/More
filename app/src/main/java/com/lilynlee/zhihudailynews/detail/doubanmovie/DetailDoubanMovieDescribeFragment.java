package com.lilynlee.zhihudailynews.detail.doubanmovie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.bean.DoubanMovieDescribe;
import com.lilynlee.zhihudailynews.http.DoubanMovieHttpManager;
import com.lilynlee.zhihudailynews.util.NetworkState;

import rx.Observable;
import rx.Subscriber;

/**
 * 作者：Lilynlee on 2017/3/25
 * 邮箱：1132860085@qq.com
 */
@SuppressLint("ValidFragment")
public class DetailDoubanMovieDescribeFragment extends Fragment {

    private TextView tvLoading;
    private LinearLayout result;

    private TextView describe;
    private TextView tvDirector;
    private ImageView ivDirctor;
    private TextView tvCast1;
    private TextView tvCast2;
    private ImageView ivCast1;
    private ImageView ivCast2;

    private Context context;

    private String sDescribe;
    private String sDirector;
    private String sUrlDirctor;
    private String sCast1;
    private String sUrlCast1;
    private String sCast2;
    private String sUrlCast2;

    private String id;

    public DetailDoubanMovieDescribeFragment(){
    }

    public DetailDoubanMovieDescribeFragment(String id){
        this.id = id;
    }

    public static DetailDoubanMovieDescribeFragment newInstance(String id){
        return new DetailDoubanMovieDescribeFragment(id);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doubanmovie_detail_describe,container,false);
        initViews(view);

        initData();
        return view;
    }

    private void initData() {
        if (NetworkState.networkConnected(context)){
            Observable<DoubanMovieDescribe> observable = DoubanMovieHttpManager
                    .getInstance().getDoubanMovieDescribe(id);
            observable.subscribe(new Subscriber<DoubanMovieDescribe>() {
                @Override
                public void onCompleted() {
                    tvLoading.setVisibility(View.GONE);
                    result.setVisibility(View.VISIBLE);
                    setDescribe(sDescribe);
                    setDirectorName(sDirector);
                    setDirectorImg(sUrlDirctor);
                    setCast1Name(sCast1);
                    setCast1Img(sUrlCast1);
                    setCast2Name(sCast2);
                    setCast2Img(sUrlCast2);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(DoubanMovieDescribe doubanMovieDescribe) {
                    sDescribe = doubanMovieDescribe.getSummary();
                    sDirector = doubanMovieDescribe.getDirectors().get(0).getName();
                    sUrlDirctor = doubanMovieDescribe.getDirectors().get(0).getAvatars().getMedium();
                    sCast1 = doubanMovieDescribe.getCasts().get(0).getName();
                    sCast2 = doubanMovieDescribe.getCasts().get(1).getName();
                    sUrlCast1 = doubanMovieDescribe.getCasts().get(0).getAvatars().getMedium();
                    sUrlCast2 = doubanMovieDescribe.getCasts().get(1).getAvatars().getMedium();
                }
            });
        }
    }

    private void initViews(View view) {
        describe = (TextView) view.findViewById(R.id.describe);
        tvDirector = (TextView) view.findViewById(R.id.tvdirector);
        ivDirctor = (ImageView) view.findViewById(R.id.ivdirector);
        tvCast1 = (TextView) view.findViewById(R.id.tvcast1);
        tvCast2 = (TextView) view.findViewById(R.id.tvcast2);
        ivCast1 = (ImageView) view.findViewById(R.id.ivcast1);
        ivCast2 = (ImageView) view.findViewById(R.id.ivcast2);

        result = (LinearLayout) view.findViewById(R.id.result);
        tvLoading = (TextView) view.findViewById(R.id.loading);
    }

    public void setDescribe(String describe){
        this.describe.setText(describe);
    }

    public void setDirectorName(String director){
        this.tvDirector.setText(director);
    }

    public void setDirectorImg(String directorImg){
        Glide.with(context)
                .load(directorImg)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(ivDirctor);
    }

    public void setCast1Name(String name){
        tvCast1.setText(name);
    }

    public void setCast2Name(String name){
        tvCast2.setText(name);
    }

    public void setCast1Img(String url){
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(ivCast1);
    }

    public void setCast2Img(String url){
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(ivCast2);
    }
}

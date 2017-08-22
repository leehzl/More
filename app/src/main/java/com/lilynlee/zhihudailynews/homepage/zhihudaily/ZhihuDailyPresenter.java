package com.lilynlee.zhihudailynews.homepage.zhihudaily;

import android.content.Context;
import android.content.Intent;

import com.lilynlee.zhihudailynews.bean.BeanType;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStory;
import com.lilynlee.zhihudailynews.detail.DetailActivity;
import com.lilynlee.zhihudailynews.http.ZhihuDailyHttpManager;
import com.lilynlee.zhihudailynews.util.DateFormatter;
import com.lilynlee.zhihudailynews.util.NetworkState;

import java.util.ArrayList;
import java.util.Calendar;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dell on 2017/2/26.
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter{

    private ZhihuDailyContract.View view;
    private Context context;

    private DateFormatter formatter = new DateFormatter();

    private ArrayList<ZhihuDailyStory> list = new ArrayList<>();

    public ZhihuDailyPresenter(Context context,ZhihuDailyContract.View view){
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
    }
    @Override
    public void loadPosts(long date, boolean clearing) {
        if (clearing){
            view.showLoading();
        }
        if (NetworkState.networkConnected(context)){

            Observable<ZhihuDailyStory> observable = ZhihuDailyHttpManager.getInstance()
                    .getNews(formatter.ZhihuDailyDateFormat(date));
            observable.subscribe(new Subscriber<ZhihuDailyStory>() {
                @Override
                public void onCompleted() {
                    view.stopLoading();
                    view.showResults(list);
                }

                @Override
                public void onError(Throwable e) {
                    view.stopLoading();
                    view.showError();
                }

                @Override
                public void onNext(ZhihuDailyStory zhihuDailyStory) {
                    list.add(zhihuDailyStory);
                }
            });
        }
    }

    @Override
    public void refresh() {
        loadPosts(Calendar.getInstance().getTimeInMillis(),true);
    }

    @Override
    public void loadMore(long date) {
        loadPosts(date,false);
    }

    @Override
    public void startReading(int position) {
        context.startActivity(new Intent(context, DetailActivity.class)
        .putExtra("type", BeanType.TYPE_ZHIHU)
        .putExtra("id",list.get(position).getId()+"")
        .putExtra("title",list.get(position).getTitle())
        .putExtra("coverUrl",list.get(position).getImages().get(0)));
    }

    @Override
    public void feelLucky() {

    }

    @Override
    public void clearData() {
        list.clear();

    }

    @Override
    public void start() {
        loadPosts(Calendar.getInstance().getTimeInMillis(),true);
    }


}

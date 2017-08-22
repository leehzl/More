package com.lilynlee.zhihudailynews.homepage.doubanevent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lilynlee.zhihudailynews.bean.BeanType;
import com.lilynlee.zhihudailynews.bean.DoubanEventItem;
import com.lilynlee.zhihudailynews.bean.DoubanEvents;
import com.lilynlee.zhihudailynews.detail.DetailActivity;
import com.lilynlee.zhihudailynews.http.DoubanEventHttpManager;
import com.lilynlee.zhihudailynews.util.NetworkState;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * 作者：Lilynlee on 2017/3/31
 * 邮箱：1132860085@qq.com
 */

public class DoubanEventPresenter implements DoubanEventContract.Presenter {

    private DoubanEventContract.View view;
    private Context context;

    private ArrayList<DoubanEventItem> list = new ArrayList<DoubanEventItem>();

    /*保存一下请求参数数据*/
    private String mCity;
    private String mType;
    private int mCount;
    private int mStart;
    private int mTotal = 999;

    public DoubanEventPresenter(DoubanEventContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadPosts(String city, String type, int count, int start, boolean clearing) {
        Log.d(TAG, "loadPosts: start="+start+";count="+count+";type="+type);
        mCity = city;
        mStart = start;
        mCount = count;
        mType = type;
        if (clearing){
            view.showLoading();
        }
        if (!NetworkState.networkConnected(context)){
            return;
        }
        Observable<DoubanEvents> observable = DoubanEventHttpManager.getInstance().getDoubanEvents(city,type,count,start);
        observable.map(new Func1<DoubanEvents, List<DoubanEventItem>>() {
                        @Override
                        public List<DoubanEventItem> call(DoubanEvents doubanEvents) {
                            mTotal = doubanEvents.getTotal();
                            return doubanEvents.getEvents();
                        }
                    })
                    .flatMap(new Func1<List<DoubanEventItem>, Observable<DoubanEventItem>>() {
                        @Override
                        public Observable<DoubanEventItem> call(List<DoubanEventItem> doubanEventItems) {
                            return Observable.from(doubanEventItems);
                        }
                    })
                    .subscribe(new Subscriber<DoubanEventItem>() {
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
                        public void onNext(DoubanEventItem doubanEventItem) {
                            list.add(doubanEventItem);
                        }
                    });
    }

    @Override
    public void refresh() {
        loadPosts(mCity, mType, mCount, mStart,true);
    }

    @Override
    public void loadMore(String city, String type, int count, int start) {
        if (start>= mTotal){
            view.noMoreData();
            return;
        }
        loadPosts(city,type,count,start,false);
    }

    @Override
    public void startDetail(int position) {

        context.startActivity(new Intent(context, DetailActivity.class)
                .putExtra("type", BeanType.TYPE_EVENT)
                .putExtra("title",list.get(position).getTitle())
                .putExtra("subcategory",list.get(position).getSubcategory_name())
                .putExtra("wish_count",list.get(position).getWisher_count()+"")
                .putExtra("time_range",list.get(position).getTime_str()+"\n到  "+list.get(position).getEnd_time())
                .putExtra("participant_count",list.get(position).getParticipant_count()+"")
                .putExtra("price_range",list.get(position).getPrice_range())
                .putExtra("image_lmobile",list.get(position).getImage_lmobile())
                .putExtra("address",list.get(position).getAddress())
                .putExtra("owner_name",list.get(position).getOwner().getName())
                .putExtra("content",list.get(position).getContent()));
    }

    @Override
    public void clearData() {
        list.clear();
    }

    @Override
    public int getTotal(){
        return mTotal;
    }
}

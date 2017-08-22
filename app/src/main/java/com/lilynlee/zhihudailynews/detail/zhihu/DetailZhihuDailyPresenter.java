package com.lilynlee.zhihudailynews.detail.zhihu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.webkit.WebView;

import com.lilynlee.zhihudailynews.bean.BeanType;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStoryDetail;
import com.lilynlee.zhihudailynews.http.ZhihuDailyHttpManager;
import com.lilynlee.zhihudailynews.util.NetworkState;
import com.lilynlee.zhihudailynews.util.PrefUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * 作者：Lilynlee on 2017/3/20
 * 邮箱：1132860085@qq.com
 */

public class DetailZhihuDailyPresenter implements DetailZhihuDailyContract.Presenter {

    private DetailZhihuDailyContract.View view;
    private Context context;

    private ZhihuDailyStoryDetail zhihuDailyStoryDetail;

    private SharedPreferences sp;

    //来自intent的data
    private BeanType type;
    private String id;
    private String title;
    private String coverUrl;

    private PrefUtils prefUtils;

    public void setType(BeanType type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public DetailZhihuDailyPresenter(DetailZhihuDailyContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void openInBrowser() {

    }

    @Override
    public void shareAsText() {

    }

    @Override
    public void openUrl(WebView webView, String url) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
        } catch (Exception e) {
            view.showLoadingError();
        }
    }

    @Override
    public void copyText() {

    }

    @Override
    public void copyLink() {

    }

    @Override
    public void addToOrDeleteFromBookmarks() {

    }

    @Override
    public boolean queryIfIsBookmarked() {
        return false;
    }

    @Override
    public void requestData() {
        if (id.trim().equals("")||type == null){
            view.showLoadingError();
            return;
        }
        view.showLoading();
        view.setTitle(title);
        view.showCover(coverUrl);

        if (NetworkState.networkConnected(context)){
            Observable<ZhihuDailyStoryDetail> observable = ZhihuDailyHttpManager.getInstance()
                    .getStory(id);
            observable.subscribe(new Subscriber<ZhihuDailyStoryDetail>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ZhihuDailyStoryDetail detail) {
                    zhihuDailyStoryDetail = detail;
                    if (zhihuDailyStoryDetail.getBody() == null){
                        view.showResultWithoutBody(zhihuDailyStoryDetail.getShare_url());
                    }else {
                        view.showResult(convertZhihuContent(zhihuDailyStoryDetail.getBody()));
                    }
                }
            });

        }
        view.stopLoading();
    }

    private String convertZhihuContent(String preResult) {
        preResult = preResult.replace("<div class=\"img-place-holder\">", "");
        preResult = preResult.replace("<div class=\"headline\">", "");

        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
        // in fact,in api,css addresses are given as an array
        // api中还有js的部分，这里不再解析js
        // javascript is included,but here I don't use it
        // 不再选择加载网络css，而是加载本地assets文件夹中的css
        // use the css file from local assets folder,not from network
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";

        // 根据主题的不同确定不同的加载内容
        // load content judging by different theme
        String theme = "<body className=\"\" onload=\"onLoaded()\">";
        prefUtils = new PrefUtils(context);
        if (prefUtils.getSuffix().equals("night")){
            theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
        }

        return new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(theme)
                .append(preResult)
                .append("</body></html>").toString();
    }
}

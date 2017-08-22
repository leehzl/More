package com.lilynlee.zhihudailynews.detail.zhihu;

import android.webkit.WebView;

import com.lilynlee.zhihudailynews.base.BasePresenter;
import com.lilynlee.zhihudailynews.base.BaseView;

/**
 * 作者：Lilynlee on 2017/3/20
 * 邮箱：1132860085@qq.com
 */

public class DetailZhihuDailyContract {
    public interface View extends BaseView<Presenter> {

        void showLoading();

        void stopLoading();

        void showLoadingError();

        void showSharingError();

        void showResult(String result);

        void showResultWithoutBody(String url);

        void showCover(String url);

        void setTitle(String title);

        void setImageMode(boolean showImage);

        void showBrowserNotFoundError();

        void showTextCopied();

        void showCopyTextError();

        void showAddedToBookmarks();

        void showDeletedFromBookmarks();

    }

    interface Presenter extends BasePresenter {

        void openInBrowser();

        void shareAsText();

        void openUrl(WebView webView, String url);

        void copyText();

        void copyLink();

        void addToOrDeleteFromBookmarks();

        boolean queryIfIsBookmarked();

        void requestData();

    }
}

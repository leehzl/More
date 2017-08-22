package com.lilynlee.zhihudailynews.interfaze;

import com.android.volley.VolleyError;

/**
 * Created by dell on 2017/2/26.
 */
public interface OnStringListener {
    /**
     * 请求成功时回调
     * @param result
     */
    void onSuccess(String result);

    /**
     * 请求失败时回调
     * @param error
     */
    void onError(VolleyError error);
}

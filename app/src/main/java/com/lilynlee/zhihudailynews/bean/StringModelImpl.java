package com.lilynlee.zhihudailynews.bean;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lilynlee.zhihudailynews.app.VolleySingleton;
import com.lilynlee.zhihudailynews.interfaze.OnStringListener;

/**
 * Created by dell on 2017/2/26.
 */

public class StringModelImpl {
    private Context context;
    public StringModelImpl(Context context){
        this.context = context;
    }

    public void load(String url, final OnStringListener listener){
        StringRequest request = new StringRequest(url,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        VolleySingleton.getVolleySingleton(context).addToRequestQuene(request);
    }

}

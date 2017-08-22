package com.lilynlee.zhihudailynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lilynlee.zhihudailynews.R;

import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/27
 * 邮箱：1132860085@qq.com
 */

public class DoubanMoviePhotosAdapter extends RecyclerView.Adapter<DoubanMoviePhotosAdapter.ViewHolder> {

    private List<String> photoUrls;
    private Context context;

    private static int screenHalfWidth;

    public DoubanMoviePhotosAdapter(List<String> photoUrls, Context context) {
        this.photoUrls = photoUrls;
        this.context = context;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        screenHalfWidth = wm.getDefaultDisplay().getWidth()/2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.douban_movie_photo_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String photoUrl = photoUrls.get(position);
        Glide.with(context)
                .load(photoUrl)
                .asBitmap()
                .placeholder(R.drawable.cover_default_image)
                .centerCrop()
                .error(R.drawable.cover_default_image)
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return photoUrls.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPhoto;

        public ViewHolder(View view) {
            super(view);
            ivPhoto = (ImageView) view.findViewById(R.id.movie_photo);
            ViewGroup.LayoutParams params = ivPhoto.getLayoutParams();
            params.width = screenHalfWidth;
            params.height = screenHalfWidth;
            ivPhoto.setLayoutParams(params);
        }
    }
}

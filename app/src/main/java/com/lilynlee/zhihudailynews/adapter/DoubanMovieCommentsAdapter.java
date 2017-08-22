package com.lilynlee.zhihudailynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.bean.DoubanMovieCommentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/27
 * 邮箱：1132860085@qq.com
 */

public class DoubanMovieCommentsAdapter extends RecyclerView.Adapter<DoubanMovieCommentsAdapter.ViewHolder> {
    private List<DoubanMovieCommentBean> comments = new ArrayList<DoubanMovieCommentBean>();
    private Context context;

    public DoubanMovieCommentsAdapter(List<DoubanMovieCommentBean> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.douban_movie_comment_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DoubanMovieCommentBean comment = comments.get(position);

        Glide.with(context)
                .load(comment.getHead())
                .asBitmap()
                .placeholder(R.drawable.cover_default_image)
                .centerCrop()
                .error(R.drawable.cover_default_image)
                .into(holder.ivHead);
        holder.tvName.setText(comment.getName());
        holder.tvTime.setText(comment.getTime());
        holder.tvContent.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivHead;
        TextView tvName;
        TextView tvTime;
        TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            ivHead = (ImageView) view.findViewById(R.id.comment_head);
            tvName = (TextView) view.findViewById(R.id.comment_name);
            tvTime = (TextView) view.findViewById(R.id.comment_time);
            tvContent = (TextView) view.findViewById(R.id.comment_content);
        }
    }
}

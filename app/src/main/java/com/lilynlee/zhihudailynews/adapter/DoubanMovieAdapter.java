package com.lilynlee.zhihudailynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.bean.DoubanMovie;
import com.lilynlee.zhihudailynews.homepage.doubanmovie.DoubanMovieFragment;
import com.lilynlee.zhihudailynews.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/23
 * 邮箱：1132860085@qq.com
 */

public class DoubanMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final DoubanMovieFragment fragment;
    private List<DoubanMovie> list = new ArrayList<DoubanMovie>();
    private OnRecyclerViewOnClickListener mListener;

    //文字加图片
    public static final int TYPE_NORMAL = 0;

    //footer 加载更多
    public static final int TYPE_FOOTER = 1;

    public DoubanMovieAdapter(Context context, List<DoubanMovie> list, DoubanMovieFragment fragment) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL){
            return new NormalViewHolder(inflater.inflate(R.layout.douban_movie_list_item_layout,parent,false),mListener);
        }else {
            return new FooterViewHolder(inflater.inflate(R.layout.list_footer,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NormalViewHolder){
            DoubanMovie item = list.get(position);
            if (item.getImages().getSmall()==null){
                ((NormalViewHolder) holder).coverImg.setImageResource(R.drawable.cover_default_image);
            }else {
                Glide.with(context)
                        .load(item.getImages().getSmall())
                        .asBitmap()
                        .placeholder(R.drawable.cover_default_image)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.cover_default_image)
                        .centerCrop()
                        .into(((NormalViewHolder) holder).coverImg);
            }
            ((NormalViewHolder) holder).titleTextView.setText(item.getTitle());
            ((NormalViewHolder) holder).originalTitleTextView.setText(item.getOriginal_title());
            ((NormalViewHolder) holder).scoreTextView.setText(item.getRating().getAverage()+"");
            ((NormalViewHolder) holder).genresTextView.setText(getMovieGenres(item.getGenres()));
        }
    }

    /**
     * 将movie的genres字符串转为String
     * @param genres
     * @return
     */
    private String getMovieGenres(List<String> genres) {
        String genresStr = "";
        for (String genre : genres){
            genresStr += genre+"、";
        }
        return genresStr.substring(0,genresStr.length()-1);
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public int getItemViewType(int position){
        if (position == list.size()){
            return TYPE_FOOTER;
        }else {
            return TYPE_NORMAL;
        }
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView coverImg;
        private TextView titleTextView;
        private TextView originalTitleTextView;
        private TextView scoreTextView;
        private TextView genresTextView;
        private OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            coverImg = (ImageView) itemView.findViewById(R.id.douban_movie_list_item_cover);
            titleTextView = (TextView) itemView.findViewById(R.id.douban_movie_list_item_title);
            originalTitleTextView = (TextView) itemView.findViewById(R.id.douban_movie_list_item_original_title);
            scoreTextView = (TextView) itemView.findViewById(R.id.douban_movie_list_item_score);
            genresTextView = (TextView) itemView.findViewById(R.id.douban_movie_list_item_genres);

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (fragment.isFabmenuOpen()){
                fragment.fabmenuClose();
                return;
            }
            if (listener != null){
                listener.OnItemClick(view,getLayoutPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}

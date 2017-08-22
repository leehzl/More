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
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStory;
import com.lilynlee.zhihudailynews.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/2/26.
 */

public class ZhihuDailyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<ZhihuDailyStory> list = new ArrayList<ZhihuDailyStory>();
    private OnRecyclerViewOnClickListener mListener;

    //文字加图片
    public static final int TYPE_NORMAL = 0;

    //footer 加载更多
    public static final int TYPE_FOOTER = 1;

    public ZhihuDailyNewsAdapter(Context context, List<ZhihuDailyStory> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL){
            return new NormalViewHolder(inflater.inflate(R.layout.home_list_item_layout,parent,false),mListener);
        }else {
         return new FooterViewHolder(inflater.inflate(R.layout.list_footer,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder){
            ZhihuDailyStory item = list.get(position);
            if (item.getImages().get(0) == null){
                ((NormalViewHolder) holder).itemImg.setImageResource(R.drawable.cover_default_image);
            }else {
                Glide.with(context)
                        .load(item.getImages().get(0))
                        .asBitmap()
                        .placeholder(R.drawable.cover_default_image)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.cover_default_image)
                        .centerCrop()
                        .into(((NormalViewHolder) holder).itemImg);
            }
            ((NormalViewHolder) holder).tvLatesNewsTitle.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
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

        private ImageView itemImg;
        private TextView tvLatesNewsTitle;
        private OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            itemImg = (ImageView) itemView.findViewById(R.id.imageViewCover);
            tvLatesNewsTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
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

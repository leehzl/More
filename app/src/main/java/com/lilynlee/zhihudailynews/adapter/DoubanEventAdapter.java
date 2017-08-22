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
import com.lilynlee.zhihudailynews.bean.DoubanEventItem;
import com.lilynlee.zhihudailynews.homepage.doubanevent.DoubanEventFragment;
import com.lilynlee.zhihudailynews.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/31
 * 邮箱：1132860085@qq.com
 */

public class DoubanEventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private DoubanEventFragment fragment;
    private List<DoubanEventItem> list = new ArrayList<DoubanEventItem>();
    private OnRecyclerViewOnClickListener mListener;

    //文字加图片
    public static final int TYPE_NORMAL = 0;

    //footer 加载更多
    public static final int TYPE_FOOTER = 1;

    public DoubanEventAdapter(Context context, List<DoubanEventItem> list, DoubanEventFragment fragment) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL){
            return new NormalViewHolder(inflater.inflate(R.layout.douban_event_list_item_layout,parent,false),mListener);
        }else {
            return new FooterViewHolder(inflater.inflate(R.layout.list_footer,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder){
            DoubanEventItem item = list.get(position);
            if (item.getImage_lmobile()== null){
                ((NormalViewHolder) holder).ivCover.setImageResource(R.drawable.cover_default_image);
            }else {
                Glide.with(context)
                        .load(item.getImage_lmobile())
                        .asBitmap()
                        .placeholder(R.drawable.cover_default_image)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.cover_default_image)
                        .centerCrop()
                        .into(((NormalViewHolder) holder).ivCover);
            }
            ((NormalViewHolder) holder).tvTitle.setText(item.getTitle());
            if (item.getSubcategory_name() == null){
                ((NormalViewHolder) holder).tvSubCategoryName.setText("无信息");
            }else {
                ((NormalViewHolder) holder).tvSubCategoryName.setText(item.getSubcategory_name());

            }
            ((NormalViewHolder) holder).tvStartTime.setText(item.getTime_str());
            ((NormalViewHolder) holder).tvPrice.setText(item.getPrice_range());
            ((NormalViewHolder) holder).tvAddress.setText(item.getAddress());
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

        private ImageView ivCover;
        private TextView tvTitle;
        private TextView tvSubCategoryName;
        private TextView tvStartTime;
        private TextView tvPrice;
        private TextView tvAddress;
        private OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.ivCover);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
            tvSubCategoryName = (TextView) itemView.findViewById(R.id.subcategory_name);
            tvStartTime = (TextView) itemView.findViewById(R.id.time_str);
            tvPrice = (TextView) itemView.findViewById(R.id.price_range);
            tvAddress = (TextView) itemView.findViewById(R.id.address);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (fragment.isFabOpen()){
                fragment.closeFab();
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

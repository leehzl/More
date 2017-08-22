package com.lilynlee.zhihudailynews.detail.doubanevent;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.detail.DetailActivity;

/**
 * 作者：Lilynlee on 2017/4/3
 * 邮箱：1132860085@qq.com
 */

public class DetailDoubanEventFragment extends Fragment implements DetailDoubanEventContract.View {
    private DetailDoubanEventContract.Presenter presenter;
    private Context context;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private NestedScrollView scrollView;
    private SwipeRefreshLayout refresh;

    private TextView tvSubcategoryName;
    private TextView tvOwnerName;
    private TextView tvWichCount;
    private TextView tvContent;
    private TextView tvTimeRange;
    private TextView tvParticipantCount;
    private TextView tvPriceRange;
    private ImageView ivCover;
    private TextView tvAddress;
    private TextView tvTitle;

    public DetailDoubanEventFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_douban_event_detail,container,false);
        initViews(view);
        setHasOptionsMenu(true);
        presenter.requestData();
        view.findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0,0);
            }
        });
        return view;
    }

   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_more,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            getActivity().onBackPressed();
        }else if (id == R.id.action_more){
        }
        return true;
    }

    @Override
    public void setPresenter(DetailDoubanEventContract.Presenter presenter) {
        if (presenter != null){
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        DetailActivity activity = (DetailActivity) getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsintToolbarLayout);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.argb(0,0,0,0));

        scrollView = (NestedScrollView) view.findViewById(R.id.nestedScrollView);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refresh.setNestedScrollingEnabled(false);
        refresh.setEnabled(false);
        refresh.setActivated(false);
        refresh.setFocusable(false);

        tvSubcategoryName = (TextView) view.findViewById(R.id.subcategory_name);
        tvOwnerName = (TextView) view.findViewById(R.id.owner_name);
        tvWichCount = (TextView) view.findViewById(R.id.wish_count);
        tvContent = (TextView) view.findViewById(R.id.content);
        tvTimeRange = (TextView) view.findViewById(R.id.time);
        tvParticipantCount = (TextView) view.findViewById(R.id.participant_count);
        tvPriceRange = (TextView) view.findViewById(R.id.price_range);
        tvAddress = (TextView) view.findViewById(R.id.address);
        ivCover = (ImageView) view.findViewById(R.id.image_view);
        tvTitle = (TextView) view.findViewById(R.id.title);
    }

    @Override
    public void showLoading() {
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(true);
            }
        });
    }

    @Override
    public void stopLoading() {
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void setSubcategoryName(String subcategoryName) {
        if (subcategoryName == null)
            subcategoryName = "无信息";
        tvSubcategoryName.setText(subcategoryName);
    }

    @Override
    public void setOwenerName(String owenerName) {
        if (owenerName==""||owenerName=="null"){
            owenerName = "无信息";
        }else {
            owenerName = "票务:"+owenerName;
        }
        tvOwnerName.setText(owenerName);
    }

    @Override
    public void setWishCount(String wishCount) {
        if (wishCount==""||wishCount=="null")
            wishCount = "0";
        wishCount += "人喜欢";
        tvWichCount.setText(wishCount);
    }

    @Override
    public void setContent(String content) {
        if (content==""||content=="null")
            content = "无信息";
        tvContent.setText(Html.fromHtml(content));
    }

    @Override
    public void setTimeRange(String timeRange) {
        if (timeRange==""||timeRange=="null")
            timeRange = "无信息";
        tvTimeRange.setText(timeRange);
    }

    @Override
    public void setParticipant(String participant) {
        if (participant==""||participant=="null")
            participant = "无信息";
        participant+= "人参与";
        tvParticipantCount.setText(participant);
    }

    @Override
    public void setPriceRange(String priceRange) {
        if (priceRange==""||priceRange=="null")
            priceRange = "无信息";
        tvPriceRange.setText(priceRange);
    }

    @Override
    public void setImage(String image) {
        Glide.with(context)
                .load(image)
                .asBitmap()
                .placeholder(R.drawable.cover_default_image)
                .centerCrop()
                .error(R.drawable.cover_default_image)
                .into(ivCover);
    }

    @Override
    public void setAddress(String address) {
        if (address==""||address=="null")
            address = "无信息";
        tvAddress.setText(address);
    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {
        collapsingToolbarLayout.setTitle(toolbarTitle);
    }

    @Override
    public void setTitle(String title) {
        if (title==""||title=="null")
            title = "无信息";
        tvTitle.setText(title);
    }
}

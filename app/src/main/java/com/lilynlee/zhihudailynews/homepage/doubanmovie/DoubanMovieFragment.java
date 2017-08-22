package com.lilynlee.zhihudailynews.homepage.doubanmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.adapter.DoubanMovieAdapter;
import com.lilynlee.zhihudailynews.bean.DoubanMovie;
import com.lilynlee.zhihudailynews.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;

/**
 * 作者：Lilynlee on 2017/3/23
 * 邮箱：1132860085@qq.com
 */

public class DoubanMovieFragment extends Fragment implements DoubanMovieContract.View,View.OnClickListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refresh;
    private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabIntheater;
    private FloatingActionButton fabComingsoon;
    private TabLayout tabLayout;

    private DoubanMovieAdapter adapter;

    /*请求api的几个参数*/
    private String city = "武汉";
    private int count = 14;
    private int start = 0;

    /**保存当前是InTheater还是Comingsoon
     * InTheater代表0
     * Comingsoon代表1
     * */
    public static int mCurrentType = 0;

    private DoubanMovieContract.Presenter presenter;

    public DoubanMovieFragment(){

    }

    public static DoubanMovieFragment newInstance(){
        return new DoubanMovieFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_douban_movie_list,container,false);
        initViews(view);
        presenter.loadPosts(mCurrentType,city,count,start,true);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingToLast = false;
            /**
             * 当滑动到最下面的时候，调用加载更多
             * @param recyclerView
             * @param newState
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //当不滚动时候
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    //获取最后一个完全显示item position
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    //判断是否滚动到底部，并且是向下滚动的
                    //如果是，则改变start的值，调用loadMore
                    if (lastVisibleItem == (totalItemCount-1)&&isSlidingToLast){
                        start += count;
                        presenter.loadMore(mCurrentType,city,count,start);
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            /**
             * 判断是否向下滚动
             * @param recyclerView
             * @param dx
             * @param dy
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy>0;
                //TODO 这里要写判断fab是否隐藏
                if (isSlidingToLast){
                    //showFabMenu();
                }else {
                    //hideFabMenu();
                }
            }
        });

        return view;
    }

    private void hideFabMenu() {
        fabMenu.animate().scaleX(0).scaleY(0).setDuration(50);
    }

    /**
     * fabMenu显示
     */
    private void showFabMenu() {
        fabMenu.animate().scaleX(1).scaleY(1).setDuration(50).start();
    }



    @Override
    public void setPresenter(DoubanMovieContract.Presenter presenter) {
        if (presenter != null){
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refresh.setColorSchemeResources(R.color.colorPrimaryDark);

        fabMenu = (FloatingActionsMenu) getActivity().findViewById(R.id.fab_menu_root);
        fabIntheater = (FloatingActionButton) getActivity().findViewById(R.id.fab_menu_intheater);
        fabComingsoon = (FloatingActionButton) getActivity().findViewById(R.id.fab_menu_comingsoon);
        fabIntheater.setOnClickListener(this);
        fabComingsoon.setOnClickListener(this);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
    }

    @Override
    public void showError() {
        Snackbar.make(fabMenu,R.string.load_error,Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.refresh();
                    }
                }).show();
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
    public void showResults(ArrayList<DoubanMovie> list) {
        if (adapter == null){
            adapter = new DoubanMovieAdapter(getContext(),list, this);
            adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    presenter.startDetail(position);
                }
            });
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void noMoreData() {
        Toast.makeText(getContext(),"已经到底啦~",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isFabmenuOpen() {
        return fabMenu.isExpanded();
    }

    @Override
    public void fabmenuClose() {
        fabMenu.collapse();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab_menu_intheater:
                //todo 点击正在热映事件
                fabMenu.collapse();
                if (mCurrentType == 1){
                    mCurrentType = 0;
                    start = 0;
                    presenter.clearData();
                    adapter.notifyDataSetChanged();
                    presenter.loadPosts(mCurrentType,city,count,start,true);

                }
                break;
            case R.id.fab_menu_comingsoon:
                //todo 点击即将上映事件
                fabMenu.collapse();
                if (mCurrentType == 0){
                    mCurrentType = 1;
                    start = 0;
                    presenter.clearData();
                    adapter.notifyDataSetChanged();
                    presenter.loadPosts(mCurrentType,city,count,start,true);

                }
                break;
            default:
                break;
        }
    }


}

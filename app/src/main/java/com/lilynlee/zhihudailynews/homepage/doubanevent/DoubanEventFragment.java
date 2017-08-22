package com.lilynlee.zhihudailynews.homepage.doubanevent;

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
import com.lilynlee.zhihudailynews.adapter.DoubanEventAdapter;
import com.lilynlee.zhihudailynews.bean.DoubanEventItem;
import com.lilynlee.zhihudailynews.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;

/**
 * 作者：Lilynlee on 2017/3/31
 * 邮箱：1132860085@qq.com
 */

public class DoubanEventFragment extends Fragment implements DoubanEventContract.View,View.OnClickListener {

    private DoubanEventContract.Presenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refresh;

    private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabMusic;
    private FloatingActionButton fabStage;
    private FloatingActionButton fabBroaden;
    private FloatingActionButton fabParty;
    private FloatingActionButton fabOutdoors;
    private FloatingActionButton fabOthers;
    private TabLayout tabLayout;

    private DoubanEventAdapter adapter;

    /*初始化请求的几个参数*/
    /**
     * city 默认是wuhan 武汉
     * type 默认是all 所有
     * start 默认是0
     * count 默认是20
     */
    private String city = "wuhan";
    private String type = "all";
    private int start = 0;
    private int count = 20;

    public DoubanEventFragment() {
    }

    public static DoubanEventFragment newInstance(){
        return new DoubanEventFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_douban_event_list,container,false);
        initViews(view);
        presenter.loadPosts(city,type,count,start,true);
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
                        dealTypeAndStart();
                        presenter.loadMore(city,type,count,start);
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

    @Override
    public void setPresenter(DoubanEventContract.Presenter presenter) {
        if (presenter !=null){
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
        fabMusic = (FloatingActionButton) getActivity().findViewById(R.id.event_music);
        fabStage = (FloatingActionButton) getActivity().findViewById(R.id.event_stage);
        fabBroaden = (FloatingActionButton) getActivity().findViewById(R.id.event_broaden);
        fabParty = (FloatingActionButton) getActivity().findViewById(R.id.event_party);
        fabOutdoors = (FloatingActionButton) getActivity().findViewById(R.id.event_outdoors);
        fabOthers = (FloatingActionButton) getActivity().findViewById(R.id.event_others);

        fabMenu.setOnClickListener(this);
        fabMusic.setOnClickListener(this);
        fabStage.setOnClickListener(this);
        fabBroaden.setOnClickListener(this);
        fabParty.setOnClickListener(this);
        fabOutdoors.setOnClickListener(this);
        fabOthers.setOnClickListener(this);
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
    public void showLoadingError() {
        Snackbar.make(fabMenu,R.string.load_error,Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.refresh();
                    }
                }).show();
    }

    @Override
    public void showResults(ArrayList<DoubanEventItem> list) {
        if (adapter == null){
            adapter = new DoubanEventAdapter(getContext(),list,this);
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
    public void noMoreData() {
        Toast.makeText(getContext(),"已经到底啦~",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isFabOpen() {
        if (fabMenu.isExpanded())
            return true;
        return false;
    }

    @Override
    public void closeFab() {
        fabMenu.collapse();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.event_music:
                //todo music
                fabMenu.collapse();
                if (type == "music")
                    break;
                /*重置系数*/
                type = "music";
                start = 0;
                presenter.clearData();
                adapter.notifyDataSetChanged();
                presenter.loadPosts(city,type,count,start,true);
                break;
            case R.id.event_stage:
                //todo stage
                fabMenu.collapse();
                if (type == "film"||type == "drama")
                    break;
                /*重置系数*/
                type = "film";
                start = 0;
                presenter.clearData();
                adapter.notifyDataSetChanged();
                presenter.loadPosts(city,type,count,start,true);
                break;
            case R.id.event_broaden:
                //todo broaden
                fabMenu.collapse();
                if (type == "salon"||type == "exhibition")
                    break;
                /*重置系数*/
                type = "salon";
                start = 0;
                presenter.clearData();
                adapter.notifyDataSetChanged();
                presenter.loadPosts(city,type,count,start,true);
                break;
            case R.id.event_party:
                //todo party
                fabMenu.collapse();
                if (type == "party")
                    break;
                /*重置系数*/
                type = "party";
                start = 0;
                presenter.clearData();
                adapter.notifyDataSetChanged();
                presenter.loadPosts(city,type,count,start,true);
                break;
            case R.id.event_outdoors:
                //todo outdoors
                fabMenu.collapse();
                if (type == "sports")
                    break;
                /*重置系数*/
                type = "music";
                start = 0;
                presenter.clearData();
                adapter.notifyDataSetChanged();
                presenter.loadPosts(city,type,count,start,true);
                break;
            case R.id.event_others:
                //todo others
                fabMenu.collapse();
                if (type == "others"||type == "commonweal")
                    break;
                /*重置系数*/
                type = "commonweal";
                start = 0;
                presenter.clearData();
                adapter.notifyDataSetChanged();
                presenter.loadPosts(city,type,count,start,true);
                break;
            default:
                //todo default
                break;
        }
    }

    /**
     * 处理type
     * 根据当前的type类型码，返回接下来需要获取的type
     * 音乐：music
     * 舞台：film+drama
     * 延伸：salon+exhibition
     * 聚集：party
     * 户外：sports+travel
     * 其他：commonweal+others
     * @return
     */
    public void dealTypeAndStart(){
        if (presenter.getTotal()>(start+count)){
            return;
        }
        switch (type){
            case "music":
                type = "music";
            case "film":
                start = 0;
                type = "drama";
            case "drama":
                type = "drama";
            case "salon":
                start = 0;
                type = "exhibition";
            case "exhibition":
                type = "exhibition";
            case "party":
                type = "party";
            case "sports":
                start = 0;
                type = "travel";
            case "travel":
                type = "travel";
            case "commonweal":
                start = 0;
                type = "others";
            case "others":
                type = "others";
            default:
                return;
        }
    }
}

package com.lilynlee.zhihudailynews.homepage.zhihudaily;

import android.content.DialogInterface;
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

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.adapter.ZhihuDailyNewsAdapter;
import com.lilynlee.zhihudailynews.bean.ZhihuDailyStory;
import com.lilynlee.zhihudailynews.interfaze.OnRecyclerViewOnClickListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dell on 2017/2/26.
 */

public class ZhihuDailyFragment extends Fragment implements ZhihuDailyContract.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refresh;
    private TabLayout tabLayout;

    private ZhihuDailyNewsAdapter adapter;

    private int mYear = Calendar.getInstance().get(Calendar.YEAR);
    private int mMonth = Calendar.getInstance().get(Calendar.MONTH);
    private int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    private ZhihuDailyContract.Presenter presenter;

    private FloatingActionsMenu fabMenu;
    private DatePickerDialog dialog;

    public ZhihuDailyFragment(){}

    public static ZhihuDailyFragment newInstance(){
        return new ZhihuDailyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu_list,container,false);

        initViews(view);

        presenter.start();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingToLast = false;

            /**
             * 当滚动到最下面，加载更多
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
                    if (lastVisibleItem == (totalItemCount-1)&&isSlidingToLast){
                        Calendar c = Calendar.getInstance();
                        c.set(mYear,mMonth,--mDay);
                        presenter.loadMore(c.getTimeInMillis());
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            /**
             * 两个作用：
             * 1判断是否向下滚动
             * 2设置fab显示或者隐藏
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy>0;
                /*if (isSlidingToLast){
                    fab.hide();
                }else {
                    fab.show();
                }*/
            }
        });

        initDialog();

        fabMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                if (tabLayout.getSelectedTabPosition() == 0)
                    dialog.show(getActivity().getFragmentManager(),"DatePickerDialog");
            }

            @Override
            public void onMenuCollapsed() {
                if (tabLayout.getSelectedTabPosition() == 0)
                    dialog.dismiss();
            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (dialog!=null&&dialog.isVisible()){
                    dialog.dismiss();
                }
                if (fabMenu.isExpanded()){
                    fabMenu.collapse();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private void initDialog() {
        Calendar now = Calendar.getInstance();
        now.set(mYear,mMonth,mDay);
        dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                fabMenu.collapse();
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                Calendar temp = Calendar.getInstance();
                temp.set(mYear,mMonth,mDay);
                presenter.clearData();
                adapter.notifyDataSetChanged();
                presenter.loadPosts(temp.getTimeInMillis(),true);
            }
        },now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
        dialog.setMaxDate(Calendar.getInstance());
        Calendar minDate = Calendar.getInstance();
        minDate.set(2013,5,20);
        dialog.setMinDate(minDate);
        dialog.vibrate(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                fabMenu.collapse();
            }
        });
    }


    /**
     * 判断第一次加载的时候内容是否充满屏幕，如果是则返回true 不是则返回false
     */
    public boolean isFullScreen(){
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
        if (lastVisibleItem<0){
            return false;
        }
        return true;
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
    public void showResults(ArrayList<ZhihuDailyStory> list) {
        if (adapter == null){
            adapter = new ZhihuDailyNewsAdapter(getContext(),list);
            adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    presenter.startReading(position);
                }
            });
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

        if (!isFullScreen()){
            Calendar c = Calendar.getInstance();
            c.set(mYear,mMonth,--mDay);
            presenter.loadMore(c.getTimeInMillis());
        }
    }

    @Override
    public void showPickDialog() {

    }

    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {
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

        tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);

        fabMenu = (FloatingActionsMenu) getActivity().findViewById(R.id.fab_menu_root);
    }
}

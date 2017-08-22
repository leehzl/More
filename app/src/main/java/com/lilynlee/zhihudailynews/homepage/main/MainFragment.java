package com.lilynlee.zhihudailynews.homepage.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.adapter.MainPagerAdapter;
import com.lilynlee.zhihudailynews.homepage.doubanevent.DoubanEventFragment;
import com.lilynlee.zhihudailynews.homepage.doubanevent.DoubanEventPresenter;
import com.lilynlee.zhihudailynews.homepage.doubanmovie.DoubanMovieFragment;
import com.lilynlee.zhihudailynews.homepage.doubanmovie.DoubanMoviePresenter;
import com.lilynlee.zhihudailynews.homepage.zhihudaily.ZhihuDailyFragment;
import com.lilynlee.zhihudailynews.homepage.zhihudaily.ZhihuDailyPresenter;

/**
 * Created by dell on 2017/2/26.
 */

public class MainFragment extends Fragment {
    private Context context;
    private MainPagerAdapter adapter;

    private TabLayout tabLayout;

    private ZhihuDailyFragment zhihuDailyFragment;
    private DoubanMovieFragment doubanMovieFragment;
    private DoubanEventFragment doubanEventFragment;

    private ZhihuDailyPresenter zhihuDailyPresenter;
    private DoubanMoviePresenter doubanMoviePresenter;
    private DoubanEventPresenter doubanEventPresenter;

    private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabIntheater;
    private FloatingActionButton fabComingsoon;
    private FloatingActionButton fabMusic;
    private FloatingActionButton fabStage;
    private FloatingActionButton fabBroaden;
    private FloatingActionButton fabParty;
    private FloatingActionButton fabOutdoors;
    private FloatingActionButton fabOthers;

    public MainFragment(){}

    public static MainFragment newInstance() {

        return new MainFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getChildFragmentManager();
        manager.putFragment(outState,"ZhihuDailyFragment",zhihuDailyFragment);
        manager.putFragment(outState,"DoubanMovieFragment",doubanMovieFragment);
        manager.putFragment(outState,"DoubanEventFragment",doubanEventFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = getActivity();
        if (savedInstanceState != null){
            zhihuDailyFragment = (ZhihuDailyFragment) getChildFragmentManager().getFragment(savedInstanceState,"ZhihuDailyFragment");
            doubanMovieFragment = (DoubanMovieFragment) getChildFragmentManager().getFragment(savedInstanceState,"DoubanMovieFragment");
            doubanEventFragment = (DoubanEventFragment) getChildFragmentManager().getFragment(savedInstanceState,"DoubanEventFragment");
        }else {
            zhihuDailyFragment = ZhihuDailyFragment.newInstance();
            doubanMovieFragment = DoubanMovieFragment.newInstance();
            doubanEventFragment = DoubanEventFragment.newInstance();
        }
        zhihuDailyPresenter = new ZhihuDailyPresenter(context,zhihuDailyFragment);
        doubanMoviePresenter = new DoubanMoviePresenter(doubanMovieFragment,context);
        doubanEventPresenter = new DoubanEventPresenter(doubanEventFragment,context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        initViews(view);
        //显示菜单
        setHasOptionsMenu(true);

        return view;
    }

    private void initViews(View view) {
        /*floatingactionsMenu*/
        fabMenu = (FloatingActionsMenu) view.findViewById(R.id.fab_menu_root);
        fabIntheater = (FloatingActionButton) view.findViewById(R.id.fab_menu_intheater);
        fabComingsoon = (FloatingActionButton) view.findViewById(R.id.fab_menu_comingsoon);
        fabMusic = (FloatingActionButton) view.findViewById(R.id.event_music);
        fabStage = (FloatingActionButton) view.findViewById(R.id.event_stage);
        fabBroaden = (FloatingActionButton) view.findViewById(R.id.event_broaden);
        fabParty = (FloatingActionButton) view.findViewById(R.id.event_party);
        fabOutdoors = (FloatingActionButton) view.findViewById(R.id.event_outdoors);
        fabOthers = (FloatingActionButton) view.findViewById(R.id.event_others);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        //设置离线数
        viewPager.setOffscreenPageLimit(3);
        adapter = new MainPagerAdapter(
                getChildFragmentManager(),
                context,
                zhihuDailyFragment,
                doubanMovieFragment,
                doubanEventFragment
        );
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        fabComingsoon.setVisibility(View.GONE);
                        fabIntheater.setVisibility(View.GONE);
                        fabMusic.setVisibility(View.GONE);
                        fabStage.setVisibility(View.GONE);
                        fabBroaden.setVisibility(View.GONE);
                        fabParty.setVisibility(View.GONE);
                        fabOutdoors.setVisibility(View.GONE);
                        fabOthers.setVisibility(View.GONE);
                        break;
                    case 1:
                        fabComingsoon.setVisibility(View.VISIBLE);
                        fabIntheater.setVisibility(View.VISIBLE);
                        fabMusic.setVisibility(View.GONE);
                        fabStage.setVisibility(View.GONE);
                        fabBroaden.setVisibility(View.GONE);
                        fabParty.setVisibility(View.GONE);
                        fabOutdoors.setVisibility(View.GONE);
                        fabOthers.setVisibility(View.GONE);
                        break;
                    case 2:
                        fabComingsoon.setVisibility(View.GONE);
                        fabIntheater.setVisibility(View.GONE);
                        fabMusic.setVisibility(View.VISIBLE);
                        fabStage.setVisibility(View.VISIBLE);
                        fabBroaden.setVisibility(View.VISIBLE);
                        fabParty.setVisibility(View.VISIBLE);
                        fabOutdoors.setVisibility(View.VISIBLE);
                        fabOthers.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}

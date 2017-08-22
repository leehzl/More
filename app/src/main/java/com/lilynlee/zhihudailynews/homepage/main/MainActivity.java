package com.lilynlee.zhihudailynews.homepage.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lilynlee.zhihudailynews.R;
import com.lilynlee.zhihudailynews.about.AboutFragment;
import com.lilynlee.zhihudailynews.base.BaseActivity;
import com.lilynlee.zhihudailynews.skin.SkinManager;
import com.lilynlee.zhihudailynews.util.PrefUtils;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private MainFragment mainFragment;
    private AboutFragment aboutFragment;

    private PrefUtils mPrefUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        mPrefUtils = new PrefUtils(this);

        if (savedInstanceState != null){
            mainFragment = (MainFragment) getSupportFragmentManager().getFragment(savedInstanceState,"MainFragment");
            aboutFragment = (AboutFragment) getSupportFragmentManager().getFragment(savedInstanceState,"AboutFragment");
        }else {
            mainFragment = MainFragment.newInstance();
            aboutFragment = AboutFragment.newInstance();
        }
        if (!mainFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_fragment, mainFragment, "MainFragment")
                    .commit();
        }

        if (!aboutFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_fragment, aboutFragment, "AboutFragment")
                    .commit();
        }
        showMainFragment();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mainFragment.isAdded()){
            getSupportFragmentManager().putFragment(outState,"MainFragment",mainFragment);
        }
    }

    private void showMainFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(mainFragment);
        fragmentTransaction.hide(aboutFragment);
        fragmentTransaction.commit();
    }

    private void showAboutFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(aboutFragment);
        fragmentTransaction.hide(mainFragment);
        fragmentTransaction.commit();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                //首页
                showMainFragment();
                break;
            case R.id.nav_change_theme:
                //改变主题
                String theme = mPrefUtils.getSuffix();
                if (theme == "night"){
                    SkinManager.getInstance().changeSkin("day");
                }else {
                    SkinManager.getInstance().changeSkin("night");
                }
                break;
            case R.id.nav_about:
                showAboutFragment();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

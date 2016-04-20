package org.zreo.zhihureader;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    CoordinatorLayout rootLayout;


    NavigationView navigationView;

    Fragment fragment ;
    HomePageFragment homePageFragment = new HomePageFragment();
    FindFragment findFragment = new FindFragment();
    FocusFragment focusFragment = new FocusFragment();
    CirFragment cirFragment = new CirFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initInstances();
        initHomePage();
    }

    private void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
    }

    private void initHomePage(){
        fragment = new HomePageFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.theContainer,fragment).commit();
        switchContent(fragment,homePageFragment);
    }

    private void initInstances(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.openDrawercontentDescRes,R.string.closeDrawercontentDescRes);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.navHomePage:
                toolbar.setTitle("首页");
                switchContent(fragment,homePageFragment);
                break;
            case R.id.navFind:
                toolbar.setTitle("发现");
                switchContent(fragment,findFragment);
                break;
            case R.id.navFocus:
                toolbar.setTitle("关注");
                switchContent(fragment,focusFragment);
                break;
            case R.id.navCollect:
                toolbar.setTitle("收藏");
                break;
            case R.id.navCir:
                toolbar.setTitle("圆桌");
                switchContent(fragment,cirFragment);
                break;
            case R.id.navMail:
                toolbar.setTitle("私信");
                break;
            case R.id.navToggle:

                break;
            case R.id.navSetting:

                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchContent(Fragment from ,Fragment to){

        if (fragment != to) {
            fragment = to;

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (!to.isAdded()) {
                ft.add(R.id.theContainer, to).commit();

            }else {
                ft.hide(from).show(to).commit();
            }
        }
    }

}

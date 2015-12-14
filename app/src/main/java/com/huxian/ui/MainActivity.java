package com.huxian.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.huxian.R;
import com.huxian.ui.fragment.BaseFragment;
import com.huxian.ui.fragment.DrawerFragment;
import com.huxian.ui.fragment.MovieListFragment;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout dlHome;
    private ActionBarDrawerToggle drawerToggle;
    private FragmentManager fragmentManager;
    private int currentMenuPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initView();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
    }

    private void initView() {
        dlHome = (DrawerLayout) findViewById(R.id.dl_home);
        drawerToggle = new ActionBarDrawerToggle(this, dlHome, toolbar, 0, 0);
        drawerToggle.syncState();
        dlHome.setDrawerListener(drawerToggle);

        fragmentManager = getSupportFragmentManager();
        DrawerFragment drawerFragment = DrawerFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fl_drawer_menu, drawerFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.fl_drawer_container,
                MovieListFragment.newInstance(getResources().getString(R.string.in_theaters_english)),
                getResources().getString(R.string.in_theaters_chinese)).commit();
        drawerFragment.setOnItemClickListener(itemClickListener);
    }

    private DrawerFragment.OnItemClickListener itemClickListener = new DrawerFragment.OnItemClickListener() {
        @Override
        public void itemClick(int position, String text) {
            dlHome.closeDrawers();
            if (position == currentMenuPosition) {
                return;
            }
            BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(text);
            if (fragment == null) {
                switch (position) {
                    case 0:
                        fragment = MovieListFragment.newInstance(getResources().getString(R.string.in_theaters_english));
                        break;
                    case 1:
                        fragment = MovieListFragment.newInstance(getResources().getString(R.string.coming_soon_english));
                        break;
                    default:
                        break;
                }
            }
            fragmentManager.beginTransaction().replace(R.id.fl_drawer_container, fragment, text).commit();
            currentMenuPosition = position;
            toolbar.setTitle(text);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

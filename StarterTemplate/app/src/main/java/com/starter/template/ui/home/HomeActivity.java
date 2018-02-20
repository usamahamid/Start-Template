package com.starter.template.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.starter.template.R;
import com.starter.template.ui.base.BaseActivity;
import com.starter.template.ui.login.LoginActivity;
import com.starter.template.util.common.CustomAnimationUtils;
import com.starter.template.util.constants.AppConstants;

public class HomeActivity
        extends BaseActivity implements
        HomeContact.HomeView,
        NavigationView.OnNavigationItemSelectedListener {


    private HomeContact.HomePresenter presenter;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private AppCompatTextView userNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_wrapper);
        presenter = new HomePresenterImpl(this);
        initViews();
    }

    private void initViews() {
        initToolbar();
    }

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initDrawer(toolbar);
    }


    private void initDrawer(Toolbar toolbar) {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        userNameText = headerView.findViewById(R.id.nav_username_text);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (onNavItemSelection(item)) {
            drawer.closeDrawer(navigationView);
            return true;
        }
        return false;
    }

    private boolean onNavItemSelection(MenuItem item) {
        boolean isEventConsumed = true;
        switch (item.getItemId()) {
            case R.id.about_nav_menu:

                break;
            case R.id.logout_nav_menu:
                presenter.logoutRequested();
                break;
            default:
                isEventConsumed = false;
                break;
        }
        item.setChecked(isEventConsumed);
        return isEventConsumed;
    }

    @Override
    public void finishAndGoToLoginScreen() {
        content.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle options =
                        CustomAnimationUtils.getSlideInFromBottomStartBundle(HomeActivity.this);
                launchActivityScreen(LoginActivity.class, options);
                finish();
            }
        }, AppConstants.SHORT_ANIMATION_DELAY);
    }
}

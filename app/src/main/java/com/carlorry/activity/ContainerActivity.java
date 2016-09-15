package com.carlorry.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.carlorry.Utils.Constants;
import com.carlorry.fragments.SearchDetailFragment;


/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class ContainerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private int frgmtId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Construct the data source
        frgmtId = getIntent().getIntExtra(Constants.BUNDLE_FRAGMENT_ID, 0);
        replaceNewFragment();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_booking) {
            // Handle the camera action
        } else if (id == R.id.nav_location) {

        } else if (id == R.id.nav_rates) {

        } else if (id == R.id.nav_terms_conditions) {

        } else if (id == R.id.nav_how_we_works) {

        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceNewFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (frgmtId) {
            case Constants.FRAGMENT_SEARCH_DETAILS:
                ft.replace(R.id.container, new SearchDetailFragment(), Constants.KEY_SEARCH_DETAILS);
                ft.commit();
                break;
            default:
                break;
        }
    }
}
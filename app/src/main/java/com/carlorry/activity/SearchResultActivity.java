package com.carlorry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.carlorry.Utils.Constants;
import com.carlorry.adapter.SearchResultAdapter;
import com.carlorry.models.Result;

import java.util.ArrayList;

/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class SearchResultActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
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
        ArrayList<Result> results = new ArrayList<Result>();
        for (int i = 0; i < 10; i++) {
            Result result = new Result(i);
            results.add(result);
        }

        // Create the adapter to convert the array to views
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        SearchResultAdapter adapter = new SearchResultAdapter(this, results);
        // Attach the adapter to a ListView
        listView.setAdapter(adapter);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        Toast.makeText(SearchResultActivity.this, "Clicked at positon = " + position, Toast.LENGTH_SHORT).show();
        Intent containerIntent = new Intent(SearchResultActivity.this, ContainerActivity.class);
        containerIntent.putExtra(Constants.BUNDLE_FRAGMENT_ID, Constants.FRAGMENT_SEARCH_DETAILS);
        startActivity(containerIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
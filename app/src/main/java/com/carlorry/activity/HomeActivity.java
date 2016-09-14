package com.carlorry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carlorry.Utils.Constants;
import com.carlorry.fragments.CitySelectionFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeViews();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            CitySelectionFragment citySelectionFragment = CitySelectionFragment.newInstance(1, false);
            citySelectionFragment.show(getSupportFragmentManager(), Constants.KEY_CitySelection);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_rent_car:
            case R.id.card_lease_car:
            case R.id.card_rent_bike:
            case R.id.card_limousine:
                startActivity(new Intent(HomeActivity.this, TripSelectionActivity.class));
                break;
            case R.id.fab:
                startActivity(new Intent(HomeActivity.this, SearchResultActivity.class));
                break;
        }
    }

    private void initializeViews() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        View viewRentCar = findViewById(R.id.card_rent_car);
        View viewRentBike = findViewById(R.id.card_rent_bike);
        View viewLeaseCar = findViewById(R.id.card_lease_car);
        View viewLimousine = findViewById(R.id.card_limousine);

        RelativeLayout cardViewRentCar = (RelativeLayout) viewRentCar.findViewById(R.id.card_view);
        LinearLayout bottomRentCar = (LinearLayout) viewRentCar.findViewById(R.id.bottomLayout);
        cardViewRentCar.setBackgroundColor(getResources().getColor(R.color.card_rent_car_bg));
        bottomRentCar.setBackgroundColor(getResources().getColor(R.color.card_rent_car_footer));
        ((TextView) viewRentCar.findViewById(R.id.tv_footer)).setText("RENT A CAR");
        viewRentCar.setOnClickListener(this);

        RelativeLayout cardViewLeaseCar = (RelativeLayout) viewLeaseCar.findViewById(R.id.card_view);
        LinearLayout bottomLeaseCar = (LinearLayout) viewLeaseCar.findViewById(R.id.bottomLayout);
        cardViewLeaseCar.setBackgroundColor(getResources().getColor(R.color.card_lease_car_bg));
        bottomLeaseCar.setBackgroundColor(getResources().getColor(R.color.card_lease_car_footer));
        ((TextView) viewLeaseCar.findViewById(R.id.tv_footer)).setText("LEASE A CAR");
        viewLeaseCar.setOnClickListener(this);

        RelativeLayout cardViewRentBike = (RelativeLayout) viewRentBike.findViewById(R.id.card_view);
        LinearLayout bottomRentBike = (LinearLayout) viewRentBike.findViewById(R.id.bottomLayout);
        cardViewRentBike.setBackgroundColor(getResources().getColor(R.color.card_rent_bike_bg));
        bottomRentBike.setBackgroundColor(getResources().getColor(R.color.card_rent_bike_footer));
        ((TextView) viewRentBike.findViewById(R.id.tv_footer)).setText("RENT A BIKE");
        viewRentBike.setOnClickListener(this);

        RelativeLayout cardViewLimousine = (RelativeLayout) viewLimousine.findViewById(R.id.card_view);
        LinearLayout bottomLimousine = (LinearLayout) viewLimousine.findViewById(R.id.bottomLayout);
        cardViewLimousine.setBackgroundColor(getResources().getColor(R.color.card_limousine_bg));
        bottomLimousine.setBackgroundColor(getResources().getColor(R.color.card_limousine_footer));
        ((TextView) viewLimousine.findViewById(R.id.tv_footer)).setText("LIMOUSINE");
        viewLimousine.setOnClickListener(this);
    }
}

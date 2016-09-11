package com.carlorry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by muhammed.poyil on 8/20/2016.
 */
public class TripSelectionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    private TextView tvStartDate, tvEndDate, tvStartTime, tvEndTime;
//    private LinearLayout layStart, layEnd;
//    private boolean isStartSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_selection);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeUI();

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        //
        caldroidFragment.setCaldroidListener(listener);
//        tvStartDate.setText(formatter.format(cal.getTime()));
//        tvEndDate.setText(formatter.format(cal.getTime()));

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.container, caldroidFragment);
        t.commit();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeUI() {
//        tvStartDate = (TextView) findViewById(R.id.tv_start_date);
//        tvEndDate = (TextView) findViewById(R.id.tv_end_date);
//        tvStartTime = (TextView) findViewById(R.id.tv_start_time);
//        tvEndTime = (TextView) findViewById(R.id.tv_end_time);
//        layStart = (LinearLayout) findViewById(R.id.lay_start);
//        layEnd = (LinearLayout) findViewById(R.id.lay_end);

        ((Button) findViewById(R.id.btn_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isStartSelected) {
//                    isStartSelected = false;
//                    layStart.setBackgroundColor(getResources().getColor(R.color.white));
//                    layEnd.setBackgroundColor(getResources().getColor(R.color.lt_grey));
//                } else {
//                    isStartSelected = true;
//                    layStart.setBackgroundColor(getResources().getColor(R.color.lt_grey));
//                    layEnd.setBackgroundColor(getResources().getColor(R.color.white));
//                }
                Intent intent = new Intent(TripSelectionActivity.this, SearchResultActivity.class);
                startActivity(intent);
            }
        });
    }

    final CaldroidListener listener = new CaldroidListener() {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        @Override
        public void onSelectDate(Date date, View view) {
//            if (isStartSelected)
//                tvEndDate.setText(formatter.format(date));
//            else
//                tvStartDate.setText(formatter.format(date));
        }

        @Override
        public void onChangeMonth(int month, int year) {
            String text = "month: " + month + " year: " + year;
        }

        @Override
        public void onLongClickDate(Date date, View view) {
//            if (isStartSelected)
//                tvEndDate.setText(formatter.format(date));
//            else
//                tvStartDate.setText(formatter.format(date));
        }

        @Override
        public void onCaldroidViewCreated() {
        }

    };
}

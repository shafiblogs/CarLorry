package com.carlorry.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.carlorry.Utils.Constants;
import com.carlorry.fragments.TimePickerFragment;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by muhammed.poyil on 8/20/2016.
 */
public class TripSelectionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TimePickerDialog.OnTimeSetListener {
    private TextView tvStartDate, tvEndDate, tvStartTime, tvEndTime;
    //    private LinearLayout layStart, layEnd;
    private boolean isStartSelected = true;
    private CaldroidFragment startDateFragment;
    private CaldroidFragment endDateFragment;
    private SimpleDateFormat startEndDateFormatter = new SimpleDateFormat("EEE, dd MMM", Locale.getDefault());
    private SimpleDateFormat currentDateFormatter = new SimpleDateFormat("EEE, dd MMM hh aa", Locale.getDefault());
    private Date startDate;
    private Date endDate;
    private List<Date> selectedDate;

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
        showStartDateCalendar();
//        setCurrentDate();
    }

    private void setCurrentDate() {
        Calendar c = Calendar.getInstance();
        String formattedDate = currentDateFormatter.format(c.getTime());
        tvStartDate.setText(formattedDate);
        tvEndDate.setText(formattedDate);
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

    private void initializeUI() {
        tvStartDate = (TextView) findViewById(R.id.trip_start_date);
        tvEndDate = (TextView) findViewById(R.id.trip_end_date);
//        tvStartTime = (TextView) findViewById(R.id.tv_start_time);
//        tvEndTime = (TextView) findViewById(R.id.tv_end_time);
//        layStart = (LinearLayout) findViewById(R.id.lay_start);
//        layEnd = (LinearLayout) findViewById(R.id.lay_end);

        ((Button) findViewById(R.id.btn_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDate = tvStartDate.getText().toString();
                String endDate = tvEndDate.getText().toString();
                if (TextUtils.isEmpty(startDate)) {
                    Toast.makeText(TripSelectionActivity.this, "Please select start date", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(endDate)) {
                    Toast.makeText(TripSelectionActivity.this, "Please select end date", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(TripSelectionActivity.this, SearchResultActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    final CaldroidListener listener = new CaldroidListener() {


        @Override
        public void onSelectDate(final Date date, final View view) {
            if (isStartSelected) {
                startDate = date;
            } else {
                endDate = date;
            }
            if (isExpire(date)) {
                Toast.makeText(TripSelectionActivity.this, "Please select a valid date", Toast.LENGTH_LONG).show();
                return;
            }
            if (null != startDate && null != endDate) {
                if (startDate.after(endDate)) {
                    Toast.makeText(TripSelectionActivity.this, "Please select a valid end date", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            if (isStartSelected) {
                startDateFragment.clearBackgroundDrawableForDates(selectedDate);
                startDateFragment.refreshView();
                tvStartDate.setText("");
                tvEndDate.setText("");
            }
            TimePickerFragment timeSelectionFragment = new TimePickerFragment();
            timeSelectionFragment.setTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    try {
                        String _24HourTime = "" + i;
                        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH", Locale.getDefault());
                        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh aa", Locale.getDefault());
                        Date _24HourDt = _24HourSDF.parse(_24HourTime);
                        if (isStartSelected) {
                            isStartSelected = false;
                            tvStartDate.setText(startEndDateFormatter.format(date) + " " + _12HourSDF.format(_24HourDt));
                            tvStartDate.setTextColor(ActivityCompat.getColor(TripSelectionActivity.this, R.color.bpBlue));
                            startDateFragment.setBackgroundDrawableForDate(new ColorDrawable(ActivityCompat.getColor(TripSelectionActivity.this, R.color.bpBlue)), date);
                            startDateFragment.refreshView();
                        } else {
                            isStartSelected = true;
                            tvEndDate.setText(startEndDateFormatter.format(date) + " " + _12HourSDF.format(_24HourDt));
                            tvEndDate.setTextColor(ActivityCompat.getColor(TripSelectionActivity.this, R.color.red));
                            startDateFragment.setBackgroundDrawableForDates(getDates(startDate, endDate));
                            startDateFragment.refreshView();
                            startDate = null;
                            endDate = null;

                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            });
            timeSelectionFragment.show(getSupportFragmentManager(), Constants.KEY_TimeSelection);
        }

        @Override
        public void onChangeMonth(int month, int year) {

        }

        @Override
        public void onLongClickDate(Date date, View view) {
        }

        @Override
        public void onCaldroidViewCreated() {
        }

    };

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }

    private Map<Date, Drawable> getDates(Date startDate, Date endDate) {
        selectedDate = new ArrayList<>();
        Map<Date, Drawable> dates = new HashMap<>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");


        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startDate);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);

        while (!cal1.after(cal2)) {
            dates.put(cal1.getTime(), new ColorDrawable(ActivityCompat.getColor(TripSelectionActivity.this, R.color.bpBlue)));
            selectedDate.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }

    private boolean isExpire(Date d) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy", Locale.getDefault()); // Jan-20-2015 1:30:55 PM
        Date d1 = null;
        String today = getToday("MMM-dd-yyyy");
        try {
            d1 = sdf.parse(today);
            if (d1.compareTo(d) < 0) {// not expired
                return false;
            } else if (d.compareTo(d1) == 0) {// both date are same
                if (d.getTime() < d1.getTime()) {// not expired
                    return false;
                } else if (d.getTime() == d1.getTime()) {//expired
                    return false;
                } else {//expired
                    return false;
                }
            } else {//expired
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }


    public static String getToday(String format) {
        Date date = new Date();
        return new SimpleDateFormat(format).format(date);
    }

    private void showStartDateCalendar() {
        if (null != endDateFragment) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(endDateFragment);
            ft.commit();
        }
        startDateFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.THEME_RESOURCE, R.style.CaldroidDefaultDark);
        startDateFragment.setArguments(args);
        startDateFragment.setCaldroidListener(listener);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.add(R.id.container, startDateFragment);
        t.commit();

    }

    private void showEndDateCalendar() {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.remove(startDateFragment);
        ft.commit();
        endDateFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        endDateFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, endDateFragment).commit();
        endDateFragment.setCaldroidListener(listener);

    }
}

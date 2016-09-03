package com.carlorry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by muhammed.poyil on 8/20/2016.
 */
public class TripSelectionActivity extends AppCompatActivity {
    private TextView tvStartDate, tvEndDate, tvStartTime, tvEndTime;
    private LinearLayout layStart, layEnd;
    private boolean isStartSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_selection);

        initializeUI();


//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.container, CountrySelectionFragment.newInstance(0), Constants.KEY_CountrySelection);
//        ft.commit();
        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        //
        caldroidFragment.setCaldroidListener(listener);
        tvStartDate.setText(formatter.format(cal.getTime()));
        tvEndDate.setText(formatter.format(cal.getTime()));

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.container, caldroidFragment);
        t.commit();
    }

    private void initializeUI() {
        tvStartDate = (TextView) findViewById(R.id.tv_start_date);
        tvEndDate = (TextView) findViewById(R.id.tv_end_date);
        tvStartTime = (TextView) findViewById(R.id.tv_start_time);
        tvEndTime = (TextView) findViewById(R.id.tv_end_time);
        layStart = (LinearLayout) findViewById(R.id.lay_start);
        layEnd = (LinearLayout) findViewById(R.id.lay_end);

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
                Intent intent = new Intent(TripSelectionActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    final CaldroidListener listener = new CaldroidListener() {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        @Override
        public void onSelectDate(Date date, View view) {
            if (isStartSelected)
                tvEndDate.setText(formatter.format(date));
            else
                tvStartDate.setText(formatter.format(date));
        }

        @Override
        public void onChangeMonth(int month, int year) {
            String text = "month: " + month + " year: " + year;
        }

        @Override
        public void onLongClickDate(Date date, View view) {
            if (isStartSelected)
                tvEndDate.setText(formatter.format(date));
            else
                tvStartDate.setText(formatter.format(date));
        }

        @Override
        public void onCaldroidViewCreated() {
        }

    };
}

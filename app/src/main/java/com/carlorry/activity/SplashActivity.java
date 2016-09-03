package com.carlorry.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.carlorry.Utils.Constants;
import com.carlorry.fragments.CountrySelectionFragment;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showSplash();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void showSplash() {
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    FragmentManager fm = getSupportFragmentManager();
                    CountrySelectionFragment countrySelectionFragment = CountrySelectionFragment.newInstance();
                    countrySelectionFragment.show(fm, Constants.KEY_CountrySelection);
                }
            }
        };
        timerThread.start();
    }

}

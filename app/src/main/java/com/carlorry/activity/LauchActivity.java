package com.carlorry.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.carlorry.Utils.Constants;
import com.carlorry.fragments.CountrySelectionFragment;

/**
 * Created by muhammed.poyil on 8/20/2016.
 */
public class LauchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, CountrySelectionFragment.newInstance(0), Constants.KEY_CountrySelection);
        ft.commit();

    }
}
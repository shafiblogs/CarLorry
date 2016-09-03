package com.carlorry.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.carlorry.Utils.Constants;
import com.carlorry.activity.R;
import com.carlorry.activity.TripSelectionActivity;

/**
 * Created by muhammed.poyil on 8/20/2016.
 */
public class CitySelectionFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "ARG_SECTION_NUMBER";
    private int selectedCity = 0;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CitySelectionFragment newInstance(int checkedCountry) {
        CitySelectionFragment fragment = new CitySelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, checkedCountry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_selection, container, false);
        int checkedCountry = (int) getArguments().get(ARG_SECTION_NUMBER);
        RadioGroup rgIndia = (RadioGroup) rootView.findViewById(R.id.rg_india);
        RadioGroup rgUae = (RadioGroup) rootView.findViewById(R.id.rg_uae);

        if (checkedCountry == 0) {
            rgIndia.setVisibility(View.VISIBLE);
            rgUae.setVisibility(View.GONE);
        } else {
            rgUae.setVisibility(View.VISIBLE);
            rgIndia.setVisibility(View.GONE);
        }

        rgIndia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.rb_bangloor)
                    selectedCity = 0;
                else if (checkedId == R.id.rb_chennai)
                    selectedCity = 1;
            }
        });

        rgUae.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.rb_dubai)
                    selectedCity = 0;
                else if (checkedId == R.id.rb_abudhabi)
                    selectedCity = 1;
            }
        });

        ((Button) rootView.findViewById(R.id.btn_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToDateSelectionFragment();
            }
        });

        return rootView;
    }

    private void navigateToDateSelectionFragment() {
        getActivity().startActivity(new Intent(getActivity(), TripSelectionActivity.class));
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        navigateToDateSelectionFragment();
    }

}

package com.carlorry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.carlorry.Utils.Constants;
import com.carlorry.activity.R;

/**
 * Created by muhammed.poyil on 8/20/2016.
 */
public class CountrySelectionFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "ARG_SECTION_NUMBER";
    private int selectedCountry = 0;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CountrySelectionFragment newInstance(int sectionNumber) {
        CountrySelectionFragment fragment = new CountrySelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_country_selection, container, false);

        RadioGroup countryGroup = (RadioGroup) rootView.findViewById(R.id.rg_country);
        countryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.rb_india)
                    selectedCountry = 0;
                else if (checkedId == R.id.rb_uae)
                    selectedCountry = 1;
            }
        });

        ((Button) rootView.findViewById(R.id.btn_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToCitySelectionFragment();
            }
        });
        return rootView;
    }

    private void navigateToCitySelectionFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, CitySelectionFragment.newInstance(selectedCountry)).addToBackStack(Constants.KEY_DateSelection).commit();
    }
}

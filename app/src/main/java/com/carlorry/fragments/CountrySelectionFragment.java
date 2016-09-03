package com.carlorry.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.carlorry.Utils.Constants;
import com.carlorry.activity.R;

/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class CountrySelectionFragment extends DialogFragment {

    public static CountrySelectionFragment newInstance() {
        return new CountrySelectionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_country, container);
        ((Button) rootView.findViewById(R.id.btn_done)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                CitySelectionFragment citySelectionFragment = CitySelectionFragment.newInstance(0);
                citySelectionFragment.show(fm, Constants.KEY_CitySelection);
                dismiss();
            }
        });
        return rootView;
    }
}

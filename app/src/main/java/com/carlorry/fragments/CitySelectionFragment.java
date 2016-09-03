package com.carlorry.fragments;

import android.content.Intent;
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
import com.carlorry.activity.TripSelectionActivity;

/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class CitySelectionFragment extends DialogFragment {
    private static final String ARG_SECTION_Id = "section_id";
    private int selCountry;

    public static CitySelectionFragment newInstance(int SelCountry) {
        CitySelectionFragment fragment = new CitySelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_Id, SelCountry);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selCountry = getArguments().getInt(ARG_SECTION_Id);
        View rootView = inflater.inflate(R.layout.fragment_select_india_loc, container);
        if (selCountry == 0)
            rootView = inflater.inflate(R.layout.fragment_select_uaeloc, container);

        ((Button) rootView.findViewById(R.id.btn_done)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TripSelectionActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}

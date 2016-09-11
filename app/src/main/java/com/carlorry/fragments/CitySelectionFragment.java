package com.carlorry.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.carlorry.activity.Home2Activity;
import com.carlorry.activity.HomeActivity;
import com.carlorry.activity.R;

/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class CitySelectionFragment extends DialogFragment implements View.OnClickListener {
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
        View rootView;
        if (selCountry == 0) {
            rootView = inflater.inflate(R.layout.fragment_select_india_loc, container);
        } else {
            rootView = inflater.inflate(R.layout.fragment_select_uaeloc, container);
        }
        ((Button) rootView.findViewById(R.id.btn_done)).setOnClickListener(this);

        return rootView;
    }

    private void intializeViews(View rootView) {
        //Bangloor

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}

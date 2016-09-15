package com.carlorry.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

import com.carlorry.Utils.Constants;
import com.carlorry.activity.R;

/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class CountrySelectionFragment extends DialogFragment implements View.OnClickListener {
    private RadioButton rbIndia, rbUAE;
    private int selCountry = 0;

    public static CountrySelectionFragment newInstance() {
        CountrySelectionFragment dialogFragment = new CountrySelectionFragment();
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.My_Dialog);
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_country, container);
        ((Button) rootView.findViewById(R.id.btn_done)).setOnClickListener(this);

        rbIndia = (RadioButton) rootView.findViewById(R.id.rb_india);
        rbUAE = (RadioButton) rootView.findViewById(R.id.rb_uae);
        rbIndia.setOnClickListener(this);
        rbUAE.setOnClickListener(this);


        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                CitySelectionFragment citySelectionFragment = CitySelectionFragment.newInstance(selCountry, true);
                citySelectionFragment.show(fm, Constants.KEY_CitySelection);
                dismiss();
                break;
            case R.id.rb_india:
                selCountry = 0;
                rbUAE.setChecked(false);
//                rbUAE.setButtonDrawable(R.drawable.radio_blank);
                break;
            case R.id.rb_uae:
                selCountry = 1;
                rbIndia.setChecked(false);
//                rbIndia.setButtonDrawable(R.drawable.radio_blank);
                break;
        }
    }
}

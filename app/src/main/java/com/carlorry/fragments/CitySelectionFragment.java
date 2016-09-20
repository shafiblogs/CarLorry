package com.carlorry.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;

import com.carlorry.Utils.AppUtils;
import com.carlorry.Utils.Constants;
import com.carlorry.activity.HomeActivity;
import com.carlorry.activity.R;

import java.util.ArrayList;

/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class CitySelectionFragment extends DialogFragment implements View.OnClickListener {
    private static final String ARG_SECTION_Id = "section_id";
    private static final String ARG_SECTION_TAG = "section_tag";
    private String selCountry, selCity = "Bangloor";
    private boolean isFromSplash = false;

    private RadioButton rbBangloor, rbChennai, rbCoimbathoor, rbCochin;
    private RadioButton rbAbudabi, rbDubai, rbSharjah, rbRAK, rbUMM, rbAjman, rbFujairah;
    private ArrayList<Integer> rbCountryIDs;

    public static CitySelectionFragment newInstance(String SelCountry, boolean isFromSplash) {
        CitySelectionFragment fragment = new CitySelectionFragment();
        fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.My_Dialog);
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_Id, SelCountry);
        args.putBoolean(ARG_SECTION_TAG, isFromSplash);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selCountry = getArguments().getString(ARG_SECTION_Id);
        isFromSplash = getArguments().getBoolean(ARG_SECTION_TAG);
        View rootView;
        rbCountryIDs = new ArrayList<>();
        if (selCountry.equalsIgnoreCase("India")) {
            rbCountryIDs.add(R.id.rbBangalore);
            rbCountryIDs.add(R.id.rbChennai);
            rbCountryIDs.add(R.id.rbCoimbatore);
            rbCountryIDs.add(R.id.rbCochin);
            rootView = inflater.inflate(R.layout.fragment_select_india_loc, container);
        } else {
            rbCountryIDs.add(R.id.rbAbuDhabi);
            rbCountryIDs.add(R.id.rbDubai);
            rbCountryIDs.add(R.id.rbSharjah);
            rbCountryIDs.add(R.id.rbAjman);
            rbCountryIDs.add(R.id.rbUmmAlQaiwain);
            rbCountryIDs.add(R.id.rbRasAlKhaimah);
            rbCountryIDs.add(R.id.rbFujairah);
            rootView = inflater.inflate(R.layout.city_selected_view, container);
        }

        intializeViews(rootView);

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private void intializeViews(View rootView) {
        ((Button) rootView.findViewById(R.id.btn_done)).setOnClickListener(this);

        if (selCountry.equalsIgnoreCase("India")) {
            rbBangloor = (RadioButton) rootView.findViewById(R.id.rbBangalore);
            rbBangloor.setOnClickListener(this);
            rbChennai = (RadioButton) rootView.findViewById(R.id.rbChennai);
            rbChennai.setOnClickListener(this);
            rbCoimbathoor = (RadioButton) rootView.findViewById(R.id.rbCoimbatore);
            rbCoimbathoor.setOnClickListener(this);
            rbCochin = (RadioButton) rootView.findViewById(R.id.rbCochin);
            rbCochin.setOnClickListener(this);
        } else {
            rbAbudabi = (RadioButton) rootView.findViewById(R.id.rbAbuDhabi);
            rbAbudabi.setOnClickListener(this);
            rbAjman = (RadioButton) rootView.findViewById(R.id.rbAjman);
            rbAjman.setOnClickListener(this);
            rbDubai = (RadioButton) rootView.findViewById(R.id.rbDubai);
            rbDubai.setOnClickListener(this);
            rbSharjah = (RadioButton) rootView.findViewById(R.id.rbSharjah);
            rbSharjah.setOnClickListener(this);
            rbUMM = (RadioButton) rootView.findViewById(R.id.rbUmmAlQaiwain);
            rbUMM.setOnClickListener(this);
            rbRAK = (RadioButton) rootView.findViewById(R.id.rbRasAlKhaimah);
            rbRAK.setOnClickListener(this);
            rbFujairah = (RadioButton) rootView.findViewById(R.id.rbFujairah);
            rbFujairah.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                AppUtils.savePref(getActivity(), Constants.PREF_CITY, selCity);
                if (isFromSplash) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                dismiss();
                break;
            case R.id.rbBangalore:
                selCity = "Bangalore";
                break;
            case R.id.rbCoimbatore:
                selCity = "Coimbatore";
                break;
            case R.id.rbChennai:
                selCity = "Chennai";
                break;
            case R.id.rbCochin:
                selCity = "Cochin";
                break;
            case R.id.rbAbuDhabi:
                selCity = "Abu Dhabi";
                break;
            case R.id.rbDubai:
                selCity = "Dubai";
                break;
            case R.id.rbSharjah:
                selCity = "Sharjah";
                break;
            case R.id.rbAjman:
                selCity = "Ajman";
                break;
            case R.id.rbRasAlKhaimah:
                selCity = "Ras Al-Khaimah";
                break;
            case R.id.rbFujairah:
                selCity = "Fujairah";
                break;
            case R.id.rbUmmAlQaiwain:
                selCity = "Umm Al-Qaiwain";
                break;
        }
    }

    private void setChecked(int rbId) {
        for (int id : rbCountryIDs) {

        }
    }
}

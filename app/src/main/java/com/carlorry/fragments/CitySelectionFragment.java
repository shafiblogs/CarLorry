package com.carlorry.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.carlorry.activity.HomeActivity;
import com.carlorry.activity.R;

/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class CitySelectionFragment extends DialogFragment implements View.OnClickListener {
    private static final String ARG_SECTION_Id = "section_id";
    private static final String ARG_SECTION_TAG = "section_tag";
    private int selCountry;
    private boolean isFromSplash = false;

    public static CitySelectionFragment newInstance(int SelCountry, boolean isFromSplash) {
        CitySelectionFragment fragment = new CitySelectionFragment();
        fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.My_Dialog);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_Id, SelCountry);
        args.putBoolean(ARG_SECTION_TAG, isFromSplash);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selCountry = getArguments().getInt(ARG_SECTION_Id);
        isFromSplash = getArguments().getBoolean(ARG_SECTION_TAG);
        View rootView;
        if (selCountry == 0) {
            rootView = inflater.inflate(R.layout.fragment_select_india_loc, container);
        } else {
            rootView = inflater.inflate(R.layout.fragment_select_uaeloc, container);
        }
        ((Button) rootView.findViewById(R.id.btn_done)).setOnClickListener(this);

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
        //Bangloor

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                if (isFromSplash) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                dismiss();
                break;
        }
    }
}

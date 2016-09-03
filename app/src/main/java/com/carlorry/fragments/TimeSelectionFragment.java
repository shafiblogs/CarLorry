package com.carlorry.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carlorry.activity.R;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.codetroopers.betterpickers.timepicker.TimePickerBuilder;

/**
 * Created by muhammed.poyil on 8/20/2016.
 */
public class TimeSelectionFragment extends Fragment implements RadialTimePickerDialogFragment.OnTimeSetListener {
    private static final String ARG_SECTION_NUMBER = "ARG_SECTION_NUMBER";
    private static final String FRAG_TAG_TIME_PICKER = "timePickerDialogFragment";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TimeSelectionFragment newInstance(int sectionNumber) {
        TimeSelectionFragment fragment = new TimeSelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time_selection, container, false);

        RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(TimeSelectionFragment.this);
        rtpd.show(getActivity().getSupportFragmentManager(), FRAG_TAG_TIME_PICKER);
        return rootView;
    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
//        mResultTextView.setText(getString(R.string.radial_time_picker_result_value, hourOfDay, minute));
    }

}

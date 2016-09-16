package com.carlorry.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.carlorry.Utils.Constants;
import com.carlorry.activity.ContainerActivity;
import com.carlorry.activity.R;

/**
 * Created by muhammed.poyil on 9/15/2016.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        ((Button) rootView.findViewById(R.id.btn_sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent containerIntent = new Intent(getActivity(), ContainerActivity.class);
                containerIntent.putExtra(Constants.BUNDLE_FRAGMENT_ID, Constants.FRAGMENT_SIGN_UP);
                startActivity(containerIntent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                getActivity().finish();
            }
        });


        return rootView;
    }
}

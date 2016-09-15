package com.carlorry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.carlorry.activity.R;
import com.carlorry.models.Result;

import java.util.ArrayList;

/**
 * Created by muhammed.poyil on 9/15/2016.
 */
public class SearchResultAdapter extends ArrayAdapter<Result> {
    public SearchResultAdapter(Context context, ArrayList<Result> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Result result = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_search_result, parent, false);
        }
        // Return the completed view to render on screen
        return convertView;
    }

}

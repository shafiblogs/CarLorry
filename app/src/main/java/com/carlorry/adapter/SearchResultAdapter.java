package com.carlorry.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.carlorry.Utils.Constants;
import com.carlorry.activity.ContainerActivity;
import com.carlorry.activity.R;
import com.carlorry.models.Result;

import java.util.ArrayList;

/**
 * Created by muhammed.poyil on 9/15/2016.
 */
public class SearchResultAdapter extends ArrayAdapter<Result> {
    Context context;

    public SearchResultAdapter(Context context, ArrayList<Result> users) {
        super(context, 0, users);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Result result = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_search_result, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.btnBook = (Button) convertView.findViewById(R.id.btn_book);
        viewHolder.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent containerIntent = new Intent((Activity)context, ContainerActivity.class);
                containerIntent.putExtra(Constants.BUNDLE_FRAGMENT_ID, Constants.FRAGMENT_LOGIN);
                context.startActivity(containerIntent);
                ((Activity)context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }

    private class ViewHolder {
        Button btnBook;
    }

}

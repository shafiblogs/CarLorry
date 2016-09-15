package com.carlorry.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.carlorry.Utils.Constants;
import com.carlorry.fragments.BookFragment;
import com.carlorry.fragments.LoginFragment;
import com.carlorry.fragments.SearchDetailFragment;
import com.carlorry.fragments.SignUpFragment;


/**
 * Created by muhammed.poyil on 9/3/2016.
 */
public class ContainerActivity extends AppCompatActivity {
    private int frgmtId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame);

        // Construct the data source
        frgmtId = getIntent().getIntExtra(Constants.BUNDLE_FRAGMENT_ID, 0);
        replaceNewFragment();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }


    public void replaceNewFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (frgmtId) {
            case Constants.FRAGMENT_SEARCH_DETAILS:
                ft.replace(R.id.container, new SearchDetailFragment(), Constants.KEY_SEARCH_DETAILS);
                ft.commit();
                break;
//            case Constants.FRAGMENT_BOOK:
//                ft.replace(R.id.container, new BookFragment(), Constants.KEY_BOOK);
//                ft.commit();
//                break;
            case Constants.FRAGMENT_BOOK:
                ft.replace(R.id.container, new SignUpFragment(), Constants.KEY_BOOK);
                ft.commit();
                break;
            case Constants.FRAGMENT_LOGIN:
                ft.replace(R.id.container, new LoginFragment(), Constants.KEY_LOGIN);
                ft.commit();
                break;
            default:
                break;
        }
    }
}
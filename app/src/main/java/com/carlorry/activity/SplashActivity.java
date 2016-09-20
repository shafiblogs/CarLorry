package com.carlorry.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.carlorry.Utils.AppUtils;
import com.carlorry.Utils.Constants;

public class SplashActivity extends AppCompatActivity {
    private String selCountry = "India";
    private String selCity = "Bangalore";
    private String[] selCityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showSplash();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void showSplash() {
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    showCountryDialog();
//                    FragmentManager fm = getSupportFragmentManager();
//                    CountrySelectionFragment countrySelectionFragment = CountrySelectionFragment.newInstance();
//                    countrySelectionFragment.show(fm, Constants.KEY_CountrySelection);
                }
            }
        };
        timerThread.start();
    }

    private void showCountryDialog() {
        runOnUiThread(new Runnable() {
            public void run() {
                final RadioButton india, uae;
                final Dialog dialog = new Dialog(SplashActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Rect displayRectangle = new Rect();
                Window window = SplashActivity.this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                LayoutInflater inflater = (LayoutInflater) SplashActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.fragment_select_country, null);
                layout.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
                dialog.setContentView(layout);
                dialog.show();
                dialog.findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppUtils.savePref(SplashActivity.this, Constants.PREF_COUNTRY, selCountry);
                        dialog.dismiss();
                        showCityDialog();
                    }
                });
                india = (RadioButton) dialog.findViewById(R.id.rb_india);
                uae = (RadioButton) dialog.findViewById(R.id.rb_uae);
                india.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        selCountry = Constants.INDIA;
                        india.setChecked(true);
                        uae.setChecked(false);
                    }
                });

                uae.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        selCountry = Constants.UAE;
                        india.setChecked(false);
                        uae.setChecked(true);
                    }
                });
                dialog.setCancelable(false);
            }
        });

    }

    private void showCityDialog() {
        runOnUiThread(new Runnable() {
            public void run() {
                final Dialog dialog = new Dialog(SplashActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Rect displayRectangle = new Rect();
                Window window = SplashActivity.this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                LayoutInflater inflater = (LayoutInflater) SplashActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.city_selected_view, null);
                layout.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
                dialog.setContentView(layout);
                dialog.show();
                ((TextView) dialog.findViewById(R.id.textView)).setText(selCountry);
                final ListView lv = (ListView) dialog.findViewById(R.id.city_list);
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                if (selCountry == "India") {
                    selCityList = Constants.indiaCities;
                } else {
                    selCityList = Constants.uaeCities;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SplashActivity.this, R.layout.city_item_list, selCityList);

                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        selCity = (String) lv.getItemAtPosition(position);
                    }
                });
                dialog.findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        AppUtils.savePref(SplashActivity.this, Constants.PREF_CITY, selCity);
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
                dialog.setCancelable(false);
            }
        });

    }
}

package com.carlorry.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by muhammed.poyil on 9/16/2016.
 */
public class AppUtils {

    public static void savePref(Context context, String key, String value) {
        SharedPreferences appSharedPrefs = getCarLorryPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(key, value.trim());
        prefsEditor.commit();
    }

    public static String getPref(Context context, String key) {
        SharedPreferences appSharedPrefs = getCarLorryPreferences(context);
        return appSharedPrefs.getString(key, "");//"" is default value
    }

    public static SharedPreferences getCarLorryPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
    }
}

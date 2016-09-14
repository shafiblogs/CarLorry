package com.carlorry.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by muhammed.poyil on 9/11/2016.
 */
public class StringUtils {
    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px
                / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
}

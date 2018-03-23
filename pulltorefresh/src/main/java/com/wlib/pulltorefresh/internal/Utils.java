package com.wlib.pulltorefresh.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.Log;

public class Utils {

    static final String LOG_TAG = "PullToRefresh";

    public static void warnDeprecation(String depreacted, String replacement) {
        Log.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    static public Drawable getDrawable(Context context, int drawablRes) {
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(drawablRes);
        } else {
            return context.getResources().getDrawable(drawablRes);
        }
    }

}

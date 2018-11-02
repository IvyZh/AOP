package com.ivyzh.aop;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Ivy on 2018/11/2.
 */

public class Utils {
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                Log.e("net", "网络可用");
                return mNetworkInfo.isAvailable();
            }
        }
        Log.e("net", "网络不用");
        return false;
    }
}

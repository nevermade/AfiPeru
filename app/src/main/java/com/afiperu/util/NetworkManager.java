package com.afiperu.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Usuario on 31/10/2015.
 */
public class NetworkManager {

    private NetworkManager(){}

    public static boolean isNetworkConnected(Context con){
        ConnectivityManager connMgr = (ConnectivityManager)
                con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        System.out.println("Wifi connected: " + isWifiConn);
        System.out.println("Mobile connected: " + isMobileConn);
        return isWifiConn || isMobileConn;

    }



}

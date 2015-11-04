package com.example.dp2.afiperu.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.TypedValue;
import android.widget.Toast;

/**
 * Created by Usuario on 04/11/2015.
 */


public class NetworkReceiver extends BroadcastReceiver {

    public String WIFI = "Wi-Fi";
    public String ANY = "Any";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();


        if (networkInfo != null && (networkInfo.getType() == ConnectivityManager.TYPE_WIFI || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)) {
            //Aca poner el codigo de sincronizacion




            Toast.makeText(context, "Sincronizando...", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(context, "Modo offline activado.", Toast.LENGTH_SHORT).show();
        }
    }

}
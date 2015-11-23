package com.example.dp2.afiperu.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.presenter.MainActivityPresenter;
import com.example.dp2.afiperu.ui.activity.DetailActivity;

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
            if(Constants.loggedUser != null) {
                MainActivityPresenter presenter = ((DetailActivity) context).getPresenter();
                presenter.validateUser(context, Constants.loggedUser.getUsername(), Constants.loggedUser.getPassword());
                presenter.setPushSettings(context, Constants.loggedUser);
            }
        } else {
            Toast.makeText(context, context.getString(R.string.offline), Toast.LENGTH_SHORT).show();
        }
    }

}
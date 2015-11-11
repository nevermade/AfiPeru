package com.example.dp2.afiperu.common;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.example.dp2.afiperu.R;

/**
 * Created by Nevermade on 12/10/2015.
 */
public abstract class BasePresenter {

    public abstract void onStart();
    public abstract void onStop();

    public void onNoInternet(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(onNoInternetString()).setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public int onNoInternetString(){
        return R.string.no_internet;
    }
}

package com.example.dp2.afiperu.util;

import android.content.SharedPreferences;

import com.example.dp2.afiperu.ui.activity.DetailActivity;

/**
 * Created by Nevermade on 10/11/2015.
 */
public class UtilClass {
    public static void clearApplicationData(DetailActivity app){
        SharedPreferences.Editor editor = app.getSharedPreferences().edit();
        editor.clear();
        editor.commit();
    }
}

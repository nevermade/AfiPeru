package com.example.dp2.afiperu.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.dp2.afiperu.ui.activity.DetailActivity;

/**
 * Created by Nevermade on 10/11/2015.
 */
public class UtilClass {
    public static void clearApplicationData(DetailActivity app){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(app).edit();
        editor.clear();
        editor.commit();
    }
    public static void makeTestSleep(int timeInMilis){
        try {
            Thread.sleep(timeInMilis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

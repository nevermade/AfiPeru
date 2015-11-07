package com.example.dp2.afiperu.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dp2.afiperu.AfiApp;
import com.example.dp2.afiperu.AfiAppComponent;

/**
 * Created by Nevermade on 12/10/2015.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //injectDependencies();
    }


    private void injectDependencies() {
        setUpComponent(AfiApp.getApp(this).getComponent());
    }

    public abstract void setUpComponent(AfiAppComponent appComponent);
}

package com.example.dp2.afiperu.common;

import android.support.v7.app.AppCompatActivity;

import com.example.dp2.afiperu.AfiApp;
import com.example.dp2.afiperu.AfiAppComponent;

/**
 * Created by Nevermade on 12/10/2015.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private void injectDependencies() {
        setUpComponent(AfiApp.getApp(this).getComponent());
    }

    public abstract void setUpComponent(AfiAppComponent appComponent);
}

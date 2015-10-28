package com.example.dp2.afiperu;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import com.example.dp2.afiperu.util.Constants;

/**
 * Created by Nevermade on 11/10/2015.
 */
public class AfiApp extends Application {
    private AfiAppComponent component;

    @Override
    public void onCreate(){
        super.onCreate();
        setupGraph();

    }

    private void setupGraph(){
        component=DaggerAfiAppComponent.builder()
                .afiAppModule(new AfiAppModule(this))
                .build();
    }

    public AfiAppComponent getComponent(){
        return component;
    }

    public static AfiApp getApp(Context context){
        return (AfiApp)context.getApplicationContext();
    }

}

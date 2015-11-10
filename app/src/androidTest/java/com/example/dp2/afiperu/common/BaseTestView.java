package com.example.dp2.afiperu.common;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.example.dp2.afiperu.ui.activity.DetailActivity;

/**
 * Created by DABARCA on 05/11/2015.
 */
public class BaseTestView extends ActivityInstrumentationTestCase2<DetailActivity> {
    public DetailActivity activity;
    public BaseTestView() {
        super(DetailActivity.class);
        init();
    }

    public void init(){
        try {
            super.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity=getActivity();
    }

    @Override
    public DetailActivity getActivity() {
        return activity;
    }




}

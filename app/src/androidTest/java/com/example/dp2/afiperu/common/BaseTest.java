package com.example.dp2.afiperu.common;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.example.dp2.afiperu.ui.activity.DetailActivity;

/**
 * Created by DABARCA on 05/11/2015.
 */
public class BaseTest extends ActivityInstrumentationTestCase2<DetailActivity> {
    DetailActivity activity;
    public BaseTest() {
        super(DetailActivity.class);

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception{
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Override
    public DetailActivity getActivity() {
        return activity;
    }




}

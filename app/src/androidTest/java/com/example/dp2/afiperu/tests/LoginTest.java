package com.example.dp2.afiperu.tests;

import android.support.test.rule.ActivityTestRule;

import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.util.UtilClass;
import com.example.dp2.afiperu.views.LoginView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by DABARCA on 05/11/2015.
 */

public class LoginTest {

    @Rule
    public ActivityTestRule<DetailActivity> activityTestRule =
            new ActivityTestRule<>(DetailActivity.class);

    LoginView view;

    @Before
    public void beforeTest() {
        view = new LoginView();
     }

    @Test
    public void loginFailure() {
        view.login("00000000","1213");
        view.assertLoginFailure();
    }


    @Test
    public void loginSuccesful() {

        view.login("00000000", "afi_password");
        view.assertLoginSuccesful();
    }

    @After
    public void afterTest(){
        UtilClass.clearApplicationData(activityTestRule.getActivity());
    }


}
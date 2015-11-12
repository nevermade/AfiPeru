package com.example.dp2.afiperu.tests;

import android.support.test.rule.ActivityTestRule;

import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.util.UtilClass;
import com.example.dp2.afiperu.views.BaseView;
import com.example.dp2.afiperu.views.LoginView;
import com.example.dp2.afiperu.views.SessionView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Nevermade on 10/11/2015.
 */
public class MakeCommentTest {
    @Rule
    public ActivityTestRule<DetailActivity> activityTestRule =
            new ActivityTestRule<>(DetailActivity.class);

    LoginView loginView;
    BaseView baseView;
    SessionView sessionView;
    @Before
    public void beforeTest() {
        loginView = new LoginView();
        baseView= new BaseView();
        sessionView=new SessionView();
    }

    @Test
    public void makeCommentTest(){
        loginView.login("00000000", "afi_password");
        baseView.openDrawer();
        baseView.clickOnSessionMenu();
        sessionView.clickOnSessionListMenu();
    }

    @After
    public void afterTest(){
        UtilClass.clearApplicationData(activityTestRule.getActivity());
    }

}

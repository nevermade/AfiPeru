package com.afiperu.tests;

import android.support.test.rule.ActivityTestRule;

import com.afiperu.ui.activity.DetailActivity;
import com.afiperu.util.UtilClass;
import com.afiperu.views.BaseView;
import com.afiperu.views.LoginView;
import com.afiperu.views.MakeCommentView;
import com.afiperu.views.SessionView;

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
    MakeCommentView makeCommentView;
    @Before
    public void beforeTest() {
        loginView = new LoginView();
        baseView= new BaseView();
        sessionView=new SessionView();
        makeCommentView=new MakeCommentView();

        loginView.login("00000000", "afi_password");
        baseView.openDrawer();
        baseView.clickOnSessionMenu();
        sessionView.clickOnSessionListMenu();
        sessionView.clickOnMakeCommentItemMenu();
    }

    @Test
    public void makeCommentTest(){
        makeCommentView.clickOnChildItem();
        makeCommentView.makeComment("Buen comportamiento");
        makeCommentView.clickOnCommentBtn();
        UtilClass.makeTestSleep(2000);
    }

    @After
    public void afterTest(){
        UtilClass.clearApplicationData(activityTestRule.getActivity());
    }

}

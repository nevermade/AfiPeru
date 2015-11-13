package com.example.dp2.afiperu.tests;

import android.support.test.rule.ActivityTestRule;

import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.util.UtilClass;
import com.example.dp2.afiperu.views.LoginView;
import com.example.dp2.afiperu.views.BaseView;
import com.example.dp2.afiperu.views.PaymentView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Nevermade on 12/11/2015.
 */
public class PaymentTest {
    @Rule
    public ActivityTestRule<DetailActivity> activityTestRule =
            new ActivityTestRule<>(DetailActivity.class);

    LoginView loginView;
    BaseView baseView;
    PaymentView paymentView;

    @Before
    public void beforeTest(){
        loginView=new LoginView();
        baseView=new BaseView();
        paymentView=new PaymentView();


        loginView.login("00000001","afi_password");
        baseView.openDrawer();
        baseView.clickOnPaymentMenu();
        paymentView.clickOnPayButton(8);
        paymentView.clickOnDepositButton();
    }

    @Test
    public void paymentTest(){

    }

    @After
    public void afterTest(){
        UtilClass.clearApplicationData(activityTestRule.getActivity());
    }


}

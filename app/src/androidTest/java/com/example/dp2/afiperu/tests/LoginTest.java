package com.example.dp2.afiperu.tests;

import com.example.dp2.afiperu.views.LoginView;

import org.junit.Before;
import org.junit.Test;
/**
 * Created by DABARCA on 05/11/2015.
 */
public class LoginTest {

    LoginView view;


    @Before
    public void beforeTest(){
        view=new LoginView();
    }

    @Test
    public void loginFailure(){
        view.sendUsername("00000000");
        view.sendPassword("afi_password");
        view.clickLoginButton();
        view.assertLoginSuccesful();
    }

    @Test
    public void loginSuccesful(){

    }

}

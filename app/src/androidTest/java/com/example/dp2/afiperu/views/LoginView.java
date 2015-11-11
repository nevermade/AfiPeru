package com.example.dp2.afiperu.views;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;

import com.example.dp2.afiperu.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
/**
 * Created by DABARCA on 05/11/2015.
 */
public class LoginView{
    ViewInteraction txtUsername;
    ViewInteraction txtPassword;
    ViewInteraction btnLogin;


    public LoginView(){

        txtUsername=onView(withId(R.id.login_username));
        txtPassword=onView(withId(R.id.login_password));
        btnLogin=onView(withId(R.id.login_btn));
    }

    public void sendUsername(String username){
        txtUsername.perform(typeText(username), ViewActions.closeSoftKeyboard());
    }

    public void sendPassword(String password){
        txtPassword.perform(typeText(password), ViewActions.closeSoftKeyboard());
    }

    public void clickLoginButton(){
        btnLogin.perform(click());
    }

    public void login(String username, String password){
        sendUsername(username);
        sendPassword(password);
        clickLoginButton();
    }

    public void assertLoginSuccesful(){
        btnLogin.check(doesNotExist());
    }

    public void assertLoginFailure(){
        btnLogin.check(matches(isDisplayed()));
    }

}

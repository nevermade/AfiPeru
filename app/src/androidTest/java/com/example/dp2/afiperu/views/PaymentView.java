package com.example.dp2.afiperu.views;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.widget.AppCompatButton;

import com.example.dp2.afiperu.R;
import com.paypal.android.sdk.cl;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by DABARCA on 12/11/2015.
 */
public class PaymentView {
    DataInteraction btnPay;
    ViewInteraction depositButon;

    public PaymentView(){
        btnPay=onData(anything()).inAdapterView(withId(R.id.payment_list)).onChildView(allOf(withText("Pagar"), isDisplayed()));
        depositButon=onView(withId(android.R.id.button1)).inRoot(isDialog());
    }

    public void clickOnPayButton(int index){
        btnPay.atPosition(index).perform(click());
    }

    public void clickOnDepositButton(){
        depositButon.perform(click());
    }

}

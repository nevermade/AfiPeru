package com.example.dp2.afiperu.views;

import android.support.test.espresso.DataInteraction;

import com.example.dp2.afiperu.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * Created by DABARCA on 12/11/2015.
 */
public class PaymentView {
    DataInteraction btnPay;

    void PaymentView(){
        btnPay=onData(anything()).inAdapterView(withId(R.id.payment_list)).atPosition(0).onChildView(withId(R.id.payment_item_action));
    }

    public void clickOnPayButton(){
        btnPay.perform(click());
    }

}

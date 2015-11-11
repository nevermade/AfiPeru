package com.example.dp2.afiperu.views;

import android.support.test.espresso.DataInteraction;

import com.example.dp2.afiperu.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Nevermade on 10/11/2015.
 */
public class SessionView {

    DataInteraction sessionListMenu;

    public SessionView(){
        sessionListMenu=onData(anything()).inAdapterView(withId(R.id.sessions_list)).atPosition(0).onChildView(withId(R.id.sessions_item_menu));
    }

    public void clickOnSessionListMenu(){
        sessionListMenu.perform(click());
    }


}

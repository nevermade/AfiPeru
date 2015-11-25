package com.afiperu.views;

import android.support.test.espresso.DataInteraction;

import com.afiperu.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Nevermade on 10/11/2015.
 */
public class SessionView {

    DataInteraction sessionListMenu;
    DataInteraction makeCommentItemMenu;

    public SessionView(){
        sessionListMenu=onData(anything()).inAdapterView(withId(R.id.sessions_list)).atPosition(0).onChildView(withId(R.id.sessions_item_menu));
        makeCommentItemMenu=onData(anything()).inRoot(isPlatformPopup()).atPosition(3); //.inAdapterView(withText("Registrar comentarios"));//.inAdapterView(withId(R.id.sessions_list)).onChildView(withId(R.id.sessions_menu_comment));
    }

    public void clickOnSessionListMenu(){
        sessionListMenu.perform(click());
    }

    public void clickOnMakeCommentItemMenu(){
        makeCommentItemMenu.perform(click());
    }
}

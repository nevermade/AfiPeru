package com.afiperu.views;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;

import com.afiperu.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Nevermade on 11/11/2015.
 */
public class MakeCommentView {

    DataInteraction childItem;
    ViewInteraction commentText;
    ViewInteraction btnComment;

    public MakeCommentView(){
        childItem=onData(anything()).inAdapterView(withId(R.id.kids_list)).atPosition(0);
        commentText=onView(withId(R.id.comment_dialog_text)).inRoot(isDialog());//.onChildView(withId(R.id.comment_dialog_message));
        btnComment=onView(withId(android.R.id.button1)).inRoot(isDialog());
    }
    public void clickOnChildItem(){
        childItem.perform(click());
    }

    public void clearCommentText(){
        commentText.perform(clearText());
    }

    public void makeComment(String comment){
        clearCommentText();
        commentText.perform(typeText(comment),closeSoftKeyboard());
    }

    public void clickOnCommentBtn(){
        btnComment.perform(click());
    }


}

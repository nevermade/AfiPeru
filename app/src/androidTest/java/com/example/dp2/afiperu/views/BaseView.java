package com.example.dp2.afiperu.views;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;


import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.domain.Drawer;

import org.hamcrest.Matchers;

import java.util.regex.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by Nevermade on 10/11/2015.
 */
public class BaseView {
    ViewInteraction btnSessionMenu;
    ViewInteraction btnPaymentMenu;
    ViewInteraction drawer;

    public BaseView(){
        btnSessionMenu=onView(Matchers.allOf(withId(R.id.drawer_list_item_name),hasSibling(withText("Sesiones"))));
        btnPaymentMenu=onView(Matchers.allOf(withId(R.id.drawer_list_item_name),hasSibling(withText("Pagos"))));
        drawer=onView(withId(R.id.drawer_layout));
    }
    public void openDrawer(){
        drawer.perform(DrawerActions.open());
    }

    public void clickOnSessionMenu(){
        btnSessionMenu.perform(click());
    }

    public void clickOnPaymentMenu(){btnPaymentMenu.perform(click());}

}

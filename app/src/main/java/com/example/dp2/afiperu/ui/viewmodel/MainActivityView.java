package com.example.dp2.afiperu.ui.viewmodel;

/**
 * Created by DABARCA on 03/11/2015.
 */
public interface MainActivityView{

    void displayApplySuccessMessage();
    void displayApplyFailureMessage();
    void removeApplyOption();
    void saveUserToSharedPreferences();
    void loadUserFromSharedPreferences();
    void selectItem(int fragmentId);
    void logOff();
    void synchronize();

}

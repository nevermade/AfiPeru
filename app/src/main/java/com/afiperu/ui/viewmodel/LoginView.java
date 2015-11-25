package com.afiperu.ui.viewmodel;

/**
 * Created by Nevermade on 23/10/2015.
 */
public interface LoginView {
    void showApp(String name);
    void displayLoginError(String message);
    void displayRecoverPassSuccess();
    void displayRecoverPassFailure();

}

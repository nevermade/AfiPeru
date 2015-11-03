package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.presenter.LoginPresenter;

/**
 * Created by Nevermade on 23/10/2015.
 */
public interface LoginInteractor {
    void login(String username, String password, LoginPresenter presenter);
    void recoverPass(String email, LoginPresenter presenter);
}

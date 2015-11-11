package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.LoginPresenter;

/**
 * Created by Nevermade on 23/10/2015.
 */
public interface LoginInteractor {
    void login(Context context, String username, String password, LoginPresenter presenter);
    void recoverPass(Context context, String email, LoginPresenter presenter);
}

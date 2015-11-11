package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.interactor.LoginInteractor;
import com.example.dp2.afiperu.ui.viewmodel.LoginView;
import com.example.dp2.afiperu.util.Constants;

/**
 * Created by Nevermade on 23/10/2015.
 */
public class LoginPresenter extends BasePresenter {
    LoginView view;
    LoginInteractor interactor;

    public LoginPresenter(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void login(Context context, String username, String password){
        interactor.login(context, username, password, this);
    }
    public void onLoginSuccess(User user, String username, String password){
        Constants.loggedUser = user;
        Constants.loggedUser.setUsername(username);
        Constants.loggedUser.setPassword(password);
        view.showApp(user.getName());
    }

    public void onLoginFailure(){
        view.displayLoginError();
    }

    @Override
    public int onNoInternetString(){
        return R.string.cant_verify_user;
    }

    public void recoverPass(Context context, String email){
        interactor.recoverPass(context, email, this);
    }

    public void onRecoverPassSuccess(){
        view.displayRecoverPassSuccess();
    }

    public void onRecoverPassFailure(){
        view.displayRecoverPassFailure();
    }
}

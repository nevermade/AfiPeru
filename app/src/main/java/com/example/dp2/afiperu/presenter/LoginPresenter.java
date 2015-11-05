package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.LoginInteractor;
import com.example.dp2.afiperu.ui.viewmodel.LoginView;

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

    public void login(String username, String password){
        interactor.login(username,password,this);
    }
    public void onLoginSuccess(String name){
        view.showApp(name);
    }

    public void onLoginFailure(){
        view.displayLoginError();
    }

    public void recoverPass(String email){
        interactor.recoverPass(email, this);
    }

    public void onRecoverPassSuccess(){
        view.displayRecoverPassSuccess();
    }

    public void onRecoverPassFailure(){
        view.displayRecoverPassFailure();
    }
}

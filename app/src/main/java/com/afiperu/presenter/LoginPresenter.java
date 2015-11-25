package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.domain.User;
import com.afiperu.interactor.LoginInteractor;
import com.afiperu.ui.viewmodel.LoginView;
import com.afiperu.util.Constants;

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

        if(Constants.loggedUser.getPushEvents() == null){
            Constants.loggedUser.setPushEvents(1);
        }
        if(Constants.loggedUser.getPushDocuments() == null){
            Constants.loggedUser.setPushDocuments(1);
        }
        if(Constants.loggedUser.getPushReports() == null){
            Constants.loggedUser.setPushReports(1);
        }
        if(Constants.loggedUser.getPushFees() == null){
            Constants.loggedUser.setPushFees(1);
        }

        view.showApp(user.getName());
    }

    public void onLoginFailure(String message){
        view.displayLoginError(message);
    }

    public void onLoginFailure(){
        onLoginFailure("Ocurrió un error");
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

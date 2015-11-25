package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.ChangePasswordInteractor;
import com.afiperu.ui.viewmodel.ChangePasswordView;

/**
 * Created by Nevermade on 12/10/2015.
 */
public class ChangePasswordPresenter extends BasePresenter{
    ChangePasswordView view;
    ChangePasswordInteractor interactor;

    public ChangePasswordPresenter(ChangePasswordView view, ChangePasswordInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    @Override
    public void onStart() {
        view.setupAdapter();
        //view.setupList();
    }

    @Override
    public void onStop() {

    }

    public void onPasswordChangedSuccess(){
        view.displayPasswordChangedSuccess();
    }

    public void changePassword(Context context, String currentPw,String newPw){
        interactor.changePassword(context, currentPw, newPw, this);
    }

    public void onPasswordChangedError(String message){
        view.displayPasswordChangedError(message);
    }

    public void onPasswordChangedFailure(){
        view.displayPasswordChangedFailure();
    }

/*


    public void getAllArtists(){

        view.displayFoundBlogs(interactor.getAllBlogs());
    }*/
}

package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractor;
import com.example.dp2.afiperu.ui.viewmodel.ChangePasswordView;

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

    public void onChangePassword(){
        view.displayPasswordChanged();
    }

    public void changePassword(String currentPw,String newPw){
        interactor.changePassword(currentPw,newPw,this);
    }
/*


    public void getAllArtists(){

        view.displayFoundBlogs(interactor.getAllBlogs());
    }*/
}

package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.interactor.UserInteractor;
import com.example.dp2.afiperu.rest.model.LocationsBody;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.ui.viewmodel.UserView;

import java.util.ArrayList;

/**
 * Created by Nevermade on 23/10/2015.
 */
public class UserPresenter extends BasePresenter {
    UserView view;
    UserInteractor interactor;

    public UserPresenter(UserView view, UserInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
/*
    public void login(String username, String password){
        interactor.login(username,password,this);
    }

    */
    public void onGetAllUserSuccess(ArrayList<SyncUser> users){
        view.showUsers(users);
    }
    public void onUsersFound(ArrayList<SyncUser> users){
        view.showUsers(users);
    }

    public void onUsersErrorOrFailure(){
        view.displayErrorOrFailure();
    }

    public ArrayList<User> getAllUsers(Context context){

        return interactor.getAllUsers(this,context);
    }

    public void onLocationsFound(LocationsBody locations){
        view.showLocations(locations);
    }

    public LocationsBody getLocations(){
        return interactor.getLocations(this);
    }
/*
    public void onLoginFailure(){
        view.displayLoginError();
    }

    */
}

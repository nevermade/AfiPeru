package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.interactor.UserInteractor;
import com.example.dp2.afiperu.syncmodel.SyncSchoolAddress;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.ui.viewmodel.UserView;

import java.util.List;

/**
 * Created by Nevermade on 23/10/2015.
 */
public class UserPresenter {
    UserView view;
    UserInteractor interactor;

    public UserPresenter(UserView view, UserInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void onUsersFound(List<SyncUser> users){
        view.showUsers(users);
    }

    public void onUsersErrorOrFailure(){
        view.displayErrorOrFailure();
    }

    public void getAllUsers(Context context){
        interactor.getAllUsers(context, this);
    }

    public void onLocationsFound(List<SyncSchoolAddress> locations){
        view.showLocations(locations);
    }

    public void onLocationsFailure(){
        view.onLocationsFailure();
    }

    public void getLocations(Context context){
        interactor.getLocations(context, this);
    }
}

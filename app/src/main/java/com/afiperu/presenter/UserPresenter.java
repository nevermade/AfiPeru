package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.interactor.UserInteractor;
import com.afiperu.syncmodel.SyncSchoolAddress;
import com.afiperu.syncmodel.SyncUser;
import com.afiperu.ui.viewmodel.UserView;

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

    public void queryUsers(Context context,String query){
        interactor.queryUsers(context, this, query);
    }

    public void queryAdvancedUsers(Context context,String name, String nrodoc, String perfil){
        interactor.queryAdvancedUsers(context, this , name,nrodoc,perfil);
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

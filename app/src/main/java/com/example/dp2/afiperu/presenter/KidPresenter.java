package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.interactor.KidInteractor;
import com.example.dp2.afiperu.interactor.UserInteractor;
import com.example.dp2.afiperu.syncmodel.SyncKid;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.ui.viewmodel.KidView;
import com.example.dp2.afiperu.ui.viewmodel.UserView;

import java.util.ArrayList;

/**
 * Created by Nevermade on 23/10/2015.
 */
public class KidPresenter extends BasePresenter {
    KidView view;
    KidInteractor interactor;

    public KidPresenter(KidView view, KidInteractor interactor) {
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
    public void onGetAllKidSuccess(ArrayList<SyncKid> kids){
        view.showKids(kids);
    }
    public void onUsersFound(ArrayList<SyncKid> kids){
        //System.out.println("Estoy aca 5");
        view.showKids(kids);
    }

    public ArrayList<Kid> getAllKids(Context context){

        //System.out.println("Estoy aca 3");
        return interactor.getAllKids(this, context);
    }
/*
    public void onLoginFailure(){
        view.displayLoginError();
    }

    */
}

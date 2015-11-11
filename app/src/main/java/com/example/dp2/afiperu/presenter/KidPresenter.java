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
import java.util.List;

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
    public void onUsersFound(List<SyncKid> kids){
        view.showKids(kids);
    }

    public void onFailure(){
        view.onFailure();
    }

    public void getAllKids(Context context){
        interactor.getAllKids(this, context);
    }

    public void queryKids(Context context,String query){
        interactor.queryKids(this, context,query);
    }
}

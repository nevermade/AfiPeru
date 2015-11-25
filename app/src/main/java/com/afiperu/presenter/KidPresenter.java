package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.KidInteractor;
import com.afiperu.syncmodel.SyncKid;
import com.afiperu.ui.viewmodel.KidView;

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
        interactor.queryKids(this, context, query);
    }


    public void queryAdvancedKids(Context context,String name, String edadini,String edadfin, String genero){
        interactor.queryAdvancedKid(context,this,name,edadini,edadfin,genero);
    }
}

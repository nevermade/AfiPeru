package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.interactor.MainActivityInteractor;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.viewmodel.MainActivityView;
import com.example.dp2.afiperu.util.AppEnum;
import com.example.dp2.afiperu.util.Constants;

/**
 * Created by DABARCA on 03/11/2015.
 */
public class MainActivityPresenter extends BasePresenter {
    MainActivityView view;
    MainActivityInteractor interactor;

    public MainActivityPresenter(MainActivityView view, MainActivityInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public MainActivityView getView() {
        return view;
    }

    public void setView(MainActivityView view) {
        this.view = view;
    }

    public void apply(int idPeriod){
        interactor.applyForPeriod(idPeriod,this);
    }

    public void onApplied(int idResponse){
        if(idResponse== AppEnum.ResponseStatus.SUCCESS.ordinal()){
            view.displayApplySuccessMessage();
            view.removeApplyOption();
        }else {
            view.displayApplyFailureMessage();
        }
    }

    public void validateUser(String username, String password){
        interactor.validateUser(username,password,this);
    }

    public void onUserValidateSuccess(User user){
        Constants.loggedUser= user;
        view.saveUserToSharedPreferences();
        view.selectItem(DetailActivity.FRAGMENT_NOTICIAS);
    }

    public void onUserValidateFailure(){
        view.loadUserFromSharedPreferences();
        view.selectItem(DetailActivity.FRAGMENT_NOTICIAS);
    }
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}

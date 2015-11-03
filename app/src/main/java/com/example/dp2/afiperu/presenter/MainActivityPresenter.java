package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.MainActivityInteractor;
import com.example.dp2.afiperu.ui.viewmodel.MainActivityView;
import com.example.dp2.afiperu.util.AppEnum;

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


    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}

package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.SessionInteractor;
import com.example.dp2.afiperu.ui.viewmodel.SessionView;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class SessionPresenter extends BasePresenter{

    SessionInteractor interactor;
    SessionView view;

    public SessionPresenter(SessionInteractor interactor, SessionView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void getAllSessions(){

    }
}

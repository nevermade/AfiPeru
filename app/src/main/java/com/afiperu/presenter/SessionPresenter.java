package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.SessionInteractor;
import com.afiperu.syncmodel.SyncSession;
import com.afiperu.ui.viewmodel.SessionView;

import java.util.List;

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

    public void getAllSessions(Context context){
        interactor.getAllSessions(context, this);
    }
    public void getAllSessionsCalendar(Context context){
        interactor.getAllSessionsCalendar(context, this);
    }

    public void onSessionFound(List<SyncSession> sessions){
        if(sessions!=null)
            view.displaySessions(sessions);
    }


    public void onSessionFoundCalendar(List<SyncSession> sessions){
        if(sessions!=null)
            view.displaySessionsCalendar(sessions);
    }


    public void onSessionErrorOrFailure(){
        view.displaySessionsErrorOrFailure();
    }
}

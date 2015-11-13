package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.SessionPresenter;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface SessionInteractor {
    void getAllSessions(Context context, SessionPresenter presenter);
    void getAllSessionsCalendar(Context context, SessionPresenter presenter);
}

package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.SessionPresenter;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface SessionInteractor {
    void getAllSessions(Context context, SessionPresenter presenter);
    void getAllSessionsCalendar(Context context, SessionPresenter presenter);
}

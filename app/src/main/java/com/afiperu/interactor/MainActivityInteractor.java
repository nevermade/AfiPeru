package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.MainActivityPresenter;

/**
 * Created by DABARCA on 03/11/2015.
 */
public interface MainActivityInteractor {
    void applyForPeriod(int idPeriod, MainActivityPresenter presenter);
    void validateUser(Context context, String username,String password,MainActivityPresenter presenter);
    void setGCMToken(Context context, String GCMToken);
    void clearGCMToken(Context context);
    void getCurrencyRate(Context context);
}

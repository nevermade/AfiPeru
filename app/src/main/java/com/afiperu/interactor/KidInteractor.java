package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.KidPresenter;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface KidInteractor {

    void getAllKids(KidPresenter kidPresenter, Context context);
    void queryKids(KidPresenter kidPresenter, Context context,String query);
    void queryAdvancedKid(Context context, KidPresenter presenter,String name,String edadini, String edadfin, String genero);
}

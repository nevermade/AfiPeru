package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.KidPresenter;
import com.example.dp2.afiperu.presenter.UserPresenter;

import java.util.ArrayList;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface KidInteractor {

    void getAllKids(KidPresenter kidPresenter, Context context);
    void queryKids(KidPresenter kidPresenter, Context context,String query);
    void queryAdvancedKid(Context context, KidPresenter presenter,String name,String edadini, String edadfin, String genero);
}

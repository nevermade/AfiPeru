package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.UserPresenter;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface UserInteractor {

    void getAllUsers(Context context, UserPresenter presenter);

    void getLocations(Context context, UserPresenter presenter);

    void queryUsers(Context context, UserPresenter presenter,String query);
}

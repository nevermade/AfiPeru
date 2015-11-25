package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.UserPresenter;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface UserInteractor {

    void getAllUsers(Context context, UserPresenter presenter);

    void getLocations(Context context, UserPresenter presenter);

    void queryUsers(Context context, UserPresenter presenter,String query);

    void queryAdvancedUsers(Context context, UserPresenter presenter,String name,String nrodoc, String Perfil);
}

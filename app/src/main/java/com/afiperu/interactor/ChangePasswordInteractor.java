package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.ChangePasswordPresenter;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface ChangePasswordInteractor {

    /*
    ArrayList<Blog> getAllBlogs();*/

    void changePassword(Context context, String currentPw, String newPw,ChangePasswordPresenter presenter);
}

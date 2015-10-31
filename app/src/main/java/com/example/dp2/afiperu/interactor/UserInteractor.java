package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.UserPresenter;

import java.util.ArrayList;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface UserInteractor {

    ArrayList<User> getAllUsers(UserPresenter userPresenter, Context context);
}

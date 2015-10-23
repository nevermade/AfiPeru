package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.presenter.ChangePasswordPresenter;

import java.util.ArrayList;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface ChangePasswordInteractor {

    /*
    ArrayList<Blog> getAllBlogs();*/

    void changePassword(String currentPw, String newPw,ChangePasswordPresenter presenter);
}

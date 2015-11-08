package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.CommentPresenter;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface CommentInteractor {
    void getKidAndComment(final CommentPresenter presenter, Context context, Integer kidId);
}

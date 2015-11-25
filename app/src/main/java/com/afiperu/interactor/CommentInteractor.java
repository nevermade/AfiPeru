package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.CommentPresenter;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface CommentInteractor {
    void getKidAndComment(final CommentPresenter presenter, Context context, Integer kidId);
    void queryKidAndComment(final CommentPresenter presenter, Context context,Integer kidId, String autorcomment,long datefrom, long dateto,String orderBy);
}

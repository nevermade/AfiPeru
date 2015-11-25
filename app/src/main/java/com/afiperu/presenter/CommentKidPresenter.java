package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.CommentKidInteractor;
import com.afiperu.syncmodel.SyncAttendanceChild;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.ui.viewmodel.CommentKidView;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class CommentKidPresenter extends BasePresenter{

    CommentKidInteractor interactor;
    CommentKidView view;

    public CommentKidPresenter(CommentKidInteractor interactor, CommentKidView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void makeComment(Context context, SyncAttendanceChild child, SyncComment comment){
        interactor.makeComment(context, child, comment);
    }
}

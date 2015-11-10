package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.CommentKidInteractor;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.ui.viewmodel.CommentKidView;

import java.util.List;

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

package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.CommentInteractor;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.ui.viewmodel.CommentView;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class CommentPresenter extends BasePresenter{

    CommentInteractor interactor;
    CommentView view;

    public CommentPresenter(CommentInteractor interactor, CommentView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void getAllComments(Context context, Integer kidId){
        interactor.getKidAndComment(this, context, kidId);
    }

    public void showComments(List<SyncComment> comments){
        view.showComments(comments);
    }

    public void onFailure(){
        view.onFailure();
    }
}

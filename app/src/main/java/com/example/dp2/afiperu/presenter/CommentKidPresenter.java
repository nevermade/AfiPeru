package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.CommentKidInteractor;
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

    public void makeComment(Context context, Integer sessionId, Integer kidId, Integer face, String message){
        interactor.makeComment(context, sessionId, kidId, face, message);
    }
}

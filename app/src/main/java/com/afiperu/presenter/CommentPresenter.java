package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.CommentInteractor;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.ui.viewmodel.CommentView;

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

    public void queryKidAndComment(Context context,Integer kidId, String autorcomment,long datefrom, long dateto,String orderBy){
        interactor.queryKidAndComment(this,context,kidId,autorcomment,datefrom,dateto,orderBy);
    }
}

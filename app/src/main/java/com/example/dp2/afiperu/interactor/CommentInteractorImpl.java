package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.CommentPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.util.Constants;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.Collections;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class CommentInteractorImpl implements CommentInteractor {
    AfiApiServiceEndPoints service;

    public CommentInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void getKidAndComment(final CommentPresenter presenter, Context context, final Integer kidId){
        if(NetworkManager.isNetworkConnected(context)) {
            Call<Kid> call = service.getKidAndComments(kidId);
            call.enqueue(new Callback<Kid>() {
                @Override
                public void onResponse(Response<Kid> response, Retrofit retrofit) {
                    Kid result = response.body();
                    if(result != null) {
                        List<SyncComment> ownComments = SyncComment.find(SyncComment.class,
                                "attendance_child != ? and kid_id = ?", "0", String.valueOf(kidId));
                        SyncComment.deleteAll(SyncComment.class, "kid_id = ?", String.valueOf(kidId));

                        List<Comment> list = result.getComments();
                        for(Comment c : list){
                            SyncComment comment = SyncComment.fromComment(c);
                            comment.setSession(c.getSessionId());
                            comment.setKidId(kidId);
                            comment.setNeedsync(0);
                            //El comentario no debe perder la referencia a AttendanceChild en caso sea del mismo usuario
                            for(SyncComment ownComment : ownComments){
                                if(ownComment.getSession().equals(c.getSessionId())){
                                    comment.setAttendanceChild(ownComment.getAttendanceChild());
                                    break;
                                }
                            }
                            comment.save();
                        }

                        List<SyncComment> comments = SyncComment.find(SyncComment.class, "kid_id = ?", String.valueOf(kidId));
                        Collections.sort(comments);
                        presenter.showComments(comments);
                    }else{
                        presenter.onFailure();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.onFailure();
                }
            });
        }else{
            List<SyncComment> comments = SyncComment.find(SyncComment.class, "kid_id = ?", String.valueOf(kidId));
            Collections.sort(comments);
            presenter.showComments(comments);
        }
    }

}

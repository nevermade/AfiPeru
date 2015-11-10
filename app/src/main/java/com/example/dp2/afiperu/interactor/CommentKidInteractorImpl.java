package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.util.Constants;
import com.example.dp2.afiperu.util.NetworkManager;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class CommentKidInteractorImpl implements CommentKidInteractor {
    AfiApiServiceEndPoints service;

    public CommentKidInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void makeComment(Context context, final SyncAttendanceChild child, final SyncComment comment){
        SyncComment.deleteAll(SyncComment.class, "attendance_child = ?", String.valueOf(child.getAttendanceChildId()));

        comment.setSession(child.getSession());
        comment.setAttendanceChild(child.getAttendanceChildId());
        comment.setKidId(child.getKid().getKidId());

        if(NetworkManager.isNetworkConnected(context)) {
            Call<Void> call = service.makeComment(comment.getAttendanceChild(), comment.getMessage(), comment.getFace());
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Response<Void> response, Retrofit retrofit) {
                    comment.setNeedsync(0);
                    comment.save();
                    child.setComment(comment);
                    child.save();
                }

                @Override
                public void onFailure(Throwable t) {
                    comment.setNeedsync(1);
                    comment.save();
                    child.setComment(comment);
                    child.save();
                }
            });
        }else{
            comment.setNeedsync(1);
            comment.save();
            child.setComment(comment);
            child.save();
        }
    }

}

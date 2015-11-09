package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.CommentKidPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
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
    public void makeComment(Context context, Integer sessionId, Integer kidId, Integer face, String message){
        //Grabar localmente
        if(NetworkManager.isNetworkConnected(context)) {
            Call<Void> call = service.makeComment(kidId, message, face);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Response<Void> response, Retrofit retrofit) {
                    int a = 0;
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

}

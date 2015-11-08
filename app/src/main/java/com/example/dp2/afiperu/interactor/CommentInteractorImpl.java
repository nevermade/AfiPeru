package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.presenter.CommentPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.util.NetworkManager;

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
    public void getKidAndComment(final CommentPresenter presenter, Context context, Integer kidId){
        //Grabar localmente
        if(NetworkManager.isNetworkConnected(context)) {
            Call<Kid> call = service.getKidAndComments(kidId);
            call.enqueue(new Callback<Kid>() {
                @Override
                public void onResponse(Response<Kid> response, Retrofit retrofit) {
                    Kid result = response.body();
                    presenter.showComments(result.getComments());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }else{

        }
    }

}

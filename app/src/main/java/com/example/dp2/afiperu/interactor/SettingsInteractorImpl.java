package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.PushBody;
import com.example.dp2.afiperu.rest.model.SuccessBody;
import com.example.dp2.afiperu.util.NetworkManager;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Fernando on 22/11/2015.
 */
public class SettingsInteractorImpl implements SettingsInteractor {
    AfiApiServiceEndPoints service;

    public SettingsInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void setPushSettings(Context context, User user){
        if(NetworkManager.isNetworkConnected(context)){
            Integer pushEvents = user.getPushEvents();
            Integer pushFees = user.getPushFees();
            Integer pushDocuments = user.getPushDocuments();
            Integer pushReports = user.getPushReports();
            Call<SuccessBody> call = service.pushSettings(new PushBody(pushEvents, pushFees, pushDocuments, pushReports));
            call.enqueue(new Callback<SuccessBody>() {
                @Override
                public void onResponse(Response<SuccessBody> response, Retrofit retrofit) {
                    if(response.body() != null && response.body().getError() == null){

                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }
}

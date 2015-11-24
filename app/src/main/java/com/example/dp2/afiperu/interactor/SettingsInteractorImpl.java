package com.example.dp2.afiperu.interactor;

import android.content.Context;
import android.util.Log;

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
            final Integer pushEvents = user.getPushEvents();
            final Integer pushFees = user.getPushFees();
            final Integer pushDocuments = user.getPushDocuments();
            final Integer pushReports = user.getPushReports();
            Call<SuccessBody> call = service.pushSettings(new PushBody(pushEvents, pushFees, pushDocuments, pushReports));
            call.enqueue(new Callback<SuccessBody>() {
                @Override
                public void onResponse(Response<SuccessBody> response, Retrofit retrofit) {
                    if(response.body() != null && response.body().getError() == null){
                        Log.d("push", String.format("events = %d, fees = %d, documents = %d, reports = %d",
                                pushEvents, pushFees, pushDocuments, pushReports));
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }
}

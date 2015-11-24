package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.UploadPhotoPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.SuccessBody;
import com.example.dp2.afiperu.util.NetworkManager;
import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Fernando on 13/11/2015.
 */
public class UploadPhotoInteractorImpl implements UploadPhotoInteractor {
    AfiApiServiceEndPoints service;

    public UploadPhotoInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void uploadPhoto(final UploadPhotoPresenter presenter, final Context context, RequestBody photo){
        if(NetworkManager.isNetworkConnected(context)){
            Call<SuccessBody> call = service.uploadPhoto(photo);
            call.enqueue(new Callback<SuccessBody>() {
                @Override
                public void onResponse(Response<SuccessBody> response, Retrofit retrofit) {
                    if(response.body() != null && response.body().getError() == null){
                        presenter.photoUploadSuccess();
                    }else{
                        presenter.photoUploadFailure(context);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.photoUploadFailure(context);
                }
            });
        }else{
            presenter.onNoInternet(context);
        }
    }
}

package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.presenter.MainActivityPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.util.AppEnum;
import com.squareup.okhttp.Response;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;


/**
 * Created by DABARCA on 03/11/2015.
 */
public class MainActivityInteractorImpl implements MainActivityInteractor{
    AfiApiServiceEndPoints service;

    public MainActivityInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void applyForPeriod(int idPeriod, final MainActivityPresenter presenter) {
        Call<Response> call=service.applyForPeriod(idPeriod);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(retrofit.Response<Response> response, Retrofit retrofit) {
                if(response.body()!=null){
                    presenter.onApplied(AppEnum.ResponseStatus.SUCCESS.ordinal());
                }else {
                    presenter.onApplied(AppEnum.ResponseStatus.ERROR.ordinal());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.onApplied(AppEnum.ResponseStatus.FAILURE.ordinal());
            }
        });

    }
}

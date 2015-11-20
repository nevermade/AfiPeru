package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.MainActivityPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.SuccessBody;
import com.example.dp2.afiperu.util.AppEnum;
import com.example.dp2.afiperu.util.Constants;
import com.example.dp2.afiperu.util.NetworkManager;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
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
        Call<Void> call=service.applyForPeriod(idPeriod);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit.Response<Void> response, Retrofit retrofit) {
                if(response.body()==null){
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

    @Override
    public void validateUser(final Context context, final String username, final String password, final MainActivityPresenter presenter) {
        if(NetworkManager.isNetworkConnected(context)) {
            Constants.PROGRESS.show();
            Call<User> call = service.login(username, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Response<User> response, Retrofit retrofit) {
                    User loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getName() != null) {
                        Constants.TOKEN = loginResponse.getAuthToken();
                        presenter.onUserValidateSuccess(loginResponse, username, password);
                    } else {
                        presenter.onUserValidateFailure(context);
                    }
                    Constants.PROGRESS.dismiss();
                }

                @Override
                public void onFailure(Throwable t) {
                    Constants.PROGRESS.dismiss();
                    presenter.onUserCantValidate();
                }
            });
        }else{
            presenter.onUserCantValidate();
        }
    }

    @Override
    public void clearGCMToken(Context context){
        if(NetworkManager.isNetworkConnected(context)){
            Call<SuccessBody> call = service.clearGCM();
            call.enqueue(new Callback<SuccessBody>() {
                @Override
                public void onResponse(Response<SuccessBody> response, Retrofit retrofit) {

                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    @Override
    public void getCurrencyRate(Context context) {
        if(NetworkManager.isNetworkConnected(context)){
            Call<Double> call = service.checkCurrencyRate();
            call.enqueue(new Callback<Double>() {
                @Override
                public void onResponse(Response<Double> response, Retrofit retrofit) {
                    if(response.errorBody()==null){
                        Constants.FROM_USD_TO_PEN=response.body();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }
}

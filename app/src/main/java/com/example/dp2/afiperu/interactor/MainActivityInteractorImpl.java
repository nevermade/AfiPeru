package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.MainActivityPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.util.AppEnum;
import com.example.dp2.afiperu.util.Constants;

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
    public void validateUser(final String username, final String password, final MainActivityPresenter presenter) {
        Constants.PROGRESS.show();
        Call<User> call= service.login(username,password);
        
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                Constants.PROGRESS.dismiss();
                if(response.body().getName()!=null) {
                    User loginResponse = response.body();
                    Constants.TOKEN=loginResponse.getAuthToken();
                    Constants.loggedUser=loginResponse;
                    Constants.loggedUser.setUsername(username);
                    Constants.loggedUser.setPassword(password);

                    presenter.onUserValidateSuccess(loginResponse);
                }else{
                    presenter.onUserValidateFailure();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                presenter.onUserValidateFailure();
            }
        });
    }
}

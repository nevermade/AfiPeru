package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.restModel.LoginResponse;
import com.example.dp2.afiperu.util.Constants;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nevermade on 23/10/2015.
 */
public class LoginInteractorImpl implements LoginInteractor {
    AfiApiServiceEndPoints service;

    public LoginInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void login(String username, String password, final LoginPresenter presenter) {
        Call<LoginResponse> call= service.login(username,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                if(response.body().getFirstName()!=null) {
                    LoginResponse loginResponse = response.body();
                    Constants.TOKEN=loginResponse.getAuthToken();
                    presenter.onLoginSuccess(loginResponse.getFirstName());
                }else{
                    presenter.onLoginFailure();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}

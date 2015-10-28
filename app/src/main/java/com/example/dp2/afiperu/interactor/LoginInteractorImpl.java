package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
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
        Call<User> call= service.login(username,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if(response.body().getName()!=null) {
                    User loginResponse = response.body();
                    Constants.TOKEN=loginResponse.getAuthToken();
                    presenter.onLoginSuccess(loginResponse.getName());
                }else{
                    presenter.onLoginFailure();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.onLoginSuccess("Invitado");
            }
        });
    }
}

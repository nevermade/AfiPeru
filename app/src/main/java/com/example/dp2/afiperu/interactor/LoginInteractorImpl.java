package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.LoginUser;
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
        Constants.PROGRESS.show();
        Call<LoginUser> call= service.login(username,password);

        call.enqueue(new Callback<LoginUser>() {

            @Override
            public void onResponse(Response<LoginUser> response, Retrofit retrofit) {
                Constants.PROGRESS.dismiss();
                if(response.body().getName()!=null) {
                    LoginUser loginResponse = response.body();
                    Constants.TOKEN=loginResponse.getAuthToken();
                    Constants.loggedUser=loginResponse;

                    presenter.onLoginSuccess(loginResponse.getName());
                }else{
                    presenter.onLoginFailure();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Constants.PROGRESS.dismiss();
            }
        });
    }

    @Override
    public void recoverPass(String email, final LoginPresenter presenter) {
        Constants.PROGRESS.show();
        Call<Void> call= service.recoverPass(email);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit.Response<Void> response, Retrofit retrofit) {
                Constants.PROGRESS.dismiss();
                if(response.errorBody()==null){
                    presenter.onRecoverPassSuccess();
                }else{
                    presenter.onRecoverPassFailure();
                }


            }

            @Override
            public void onFailure(Throwable t) {
                Constants.PROGRESS.dismiss();
                presenter.onRecoverPassFailure();

            }
        });

    }
}

package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.util.Constants;
import com.example.dp2.afiperu.util.NetworkManager;

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
    public void login(final Context context, final String username, final String password, final LoginPresenter presenter) {
        if(NetworkManager.isNetworkConnected(context)) {
            Constants.PROGRESS.show();
            Call<User> call = service.login(username, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Response<User> response, Retrofit retrofit) {
                    User loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getName() != null) {
                        Constants.TOKEN = loginResponse.getAuthToken();
                        presenter.onLoginSuccess(loginResponse, username, password);
                    } else {
                        presenter.onLoginFailure();
                    }
                    Constants.PROGRESS.dismiss();
                }

                @Override
                public void onFailure(Throwable t) {
                    Constants.PROGRESS.dismiss();
                }
            });
        }else{
            presenter.onNoInternet(context);
        }
    }

    @Override
    public void recoverPass(Context context, String email, final LoginPresenter presenter) {
        if (NetworkManager.isNetworkConnected(context)) {
            Constants.PROGRESS.show();
            Call<Void> call = service.recoverPass(email);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(retrofit.Response<Void> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        presenter.onRecoverPassSuccess();
                    } else {
                        presenter.onRecoverPassFailure();
                    }
                    Constants.PROGRESS.dismiss();
                }

                @Override
                public void onFailure(Throwable t) {
                    Constants.PROGRESS.dismiss();
                    presenter.onRecoverPassFailure();
                }
            });
        }else{
            presenter.onNoInternet(context);
        }
    }
}

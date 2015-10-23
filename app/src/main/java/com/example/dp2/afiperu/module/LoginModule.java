package com.example.dp2.afiperu.module;

import com.example.dp2.afiperu.interactor.LoginInteractor;
import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.ui.viewmodel.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 23/10/2015.
 */
@Module
public class LoginModule {
    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    public LoginView provideView(){
        return view;
    }

    @Provides
    public LoginPresenter providePresenter(LoginView view, LoginInteractor interactor){
        return new LoginPresenter(view,interactor);
    }

}

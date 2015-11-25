package com.afiperu.module;

import com.afiperu.interactor.ChangePasswordInteractor;
import com.afiperu.presenter.ChangePasswordPresenter;
import com.afiperu.ui.viewmodel.ChangePasswordView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 12/10/2015.
 */
@Module
public class ChangePasswordModule {
    private ChangePasswordView view;

    public ChangePasswordModule(ChangePasswordView view) {
        this.view = view;
    }

    @Provides
    public ChangePasswordView provideView(){
        return view;
    }

    @Provides
    public ChangePasswordPresenter providePresenter(ChangePasswordView view, ChangePasswordInteractor interactor){
        return new ChangePasswordPresenter(view, interactor);
    }

}

package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.LoginModule;
import com.afiperu.presenter.LoginPresenter;
import com.afiperu.ui.fragment.LoginFragment;

import dagger.Component;

/**
 * Created by Nevermade on 23/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = LoginModule.class
)
public interface LoginComponent {
    void inject(LoginFragment fragment);
    LoginPresenter getPresenter();

}

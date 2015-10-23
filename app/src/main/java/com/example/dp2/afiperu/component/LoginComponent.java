package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.LoginModule;
import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.ui.fragment.LoginFragment;

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

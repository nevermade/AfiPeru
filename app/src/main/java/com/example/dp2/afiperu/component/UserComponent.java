package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.LoginModule;
import com.example.dp2.afiperu.module.UserModule;
import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.ui.adapter.UsersAdapter;
import com.example.dp2.afiperu.ui.fragment.LoginFragment;
import com.example.dp2.afiperu.ui.fragment.UsersFragment;

import dagger.Component;

/**
 * Created by Nevermade on 23/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = UserModule.class
)
public interface UserComponent {
    void inject(UsersFragment fragment);
    UserPresenter getPresenter();
    UsersAdapter getAdapter();
}

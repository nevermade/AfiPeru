package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.UserModule;
import com.afiperu.presenter.UserPresenter;
import com.afiperu.ui.adapter.UsersAdapter;
import com.afiperu.ui.fragment.UsersFragment;

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

package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.ChangePasswordModule;
import com.afiperu.presenter.ChangePasswordPresenter;
import com.afiperu.ui.fragment.ChangePasswordFragment;

import dagger.Component;

/**
 * Created by Nevermade on 12/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = ChangePasswordModule.class
)
public interface ChangePasswordComponent {
    void inject(ChangePasswordFragment changePasswordFragment);
    ChangePasswordPresenter getPresenter();
}

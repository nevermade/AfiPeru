package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.ChangePasswordModule;
import com.example.dp2.afiperu.presenter.ChangePasswordPresenter;
import com.example.dp2.afiperu.ui.adapter.ChangePasswordAdapter;
import com.example.dp2.afiperu.ui.fragment.ChangePasswordFragment;

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
    ChangePasswordAdapter getAdapter();
}

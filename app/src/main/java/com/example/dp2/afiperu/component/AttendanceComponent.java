package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.AttendanceModule;
import com.example.dp2.afiperu.module.UserModule;
import com.example.dp2.afiperu.presenter.AttendancePresenter;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.ui.adapter.AttendanceAdapter;
import com.example.dp2.afiperu.ui.adapter.UsersAdapter;
import com.example.dp2.afiperu.ui.fragment.AttendanceFragment;
import com.example.dp2.afiperu.ui.fragment.UsersFragment;

import dagger.Component;

/**
 * Created by Nevermade on 23/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = AttendanceModule.class
)
public interface AttendanceComponent {
    void inject(AttendanceFragment fragment);
    AttendancePresenter getPresenter();
    AttendanceAdapter getAdapter();
}

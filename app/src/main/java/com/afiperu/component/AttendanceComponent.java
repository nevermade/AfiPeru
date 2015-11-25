package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.AttendanceModule;
import com.afiperu.presenter.AttendancePresenter;
import com.afiperu.ui.adapter.AttendanceAdapter;
import com.afiperu.ui.fragment.AttendanceFragment;

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

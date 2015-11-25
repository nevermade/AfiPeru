package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.SessionModule;
import com.afiperu.presenter.SessionPresenter;
import com.afiperu.ui.adapter.SessionAdapter;
import com.afiperu.ui.fragment.SessionFragment;

import dagger.Component;

/**
 * Created by DABARCA on 21/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = SessionModule.class
)
public interface SessionComponent {
    void inject(SessionFragment fragment);
    SessionAdapter getAdapter();
    SessionPresenter getPresenter();
}

package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.SessionModule;
import com.example.dp2.afiperu.presenter.SessionPresenter;
import com.example.dp2.afiperu.ui.adapter.SessionAdapter;
import com.example.dp2.afiperu.ui.fragment.SessionFragment;

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

package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.KidModule;
import com.example.dp2.afiperu.presenter.KidPresenter;
import com.example.dp2.afiperu.ui.adapter.PeopleKidsAdapter;
import com.example.dp2.afiperu.ui.fragment.PeopleKidsFragment;

import dagger.Component;

/**
 * Created by Nevermade on 23/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = KidModule.class
)
public interface KidComponent {
    void inject(PeopleKidsFragment fragment);
    KidPresenter getPresenter();
    PeopleKidsAdapter getAdapter();
}

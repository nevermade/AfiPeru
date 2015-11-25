package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.KidModule;
import com.afiperu.presenter.KidPresenter;
import com.afiperu.ui.adapter.PeopleKidsAdapter;
import com.afiperu.ui.fragment.PeopleKidsFragment;

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

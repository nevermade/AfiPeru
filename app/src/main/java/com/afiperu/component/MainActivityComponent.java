package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.MainActivityModule;
import com.afiperu.presenter.MainActivityPresenter;
import com.afiperu.ui.activity.DetailActivity;

import dagger.Component;

/**
 * Created by DABARCA on 03/11/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = MainActivityModule.class
)
public interface MainActivityComponent {

    void inject(DetailActivity view);
    MainActivityPresenter getPresenter();

}

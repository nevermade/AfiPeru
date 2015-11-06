package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.MainActivityModule;
import com.example.dp2.afiperu.presenter.MainActivityPresenter;
import com.example.dp2.afiperu.ui.activity.DetailActivity;

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

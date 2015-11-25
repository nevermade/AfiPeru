package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.PointsOfReunionModule;
import com.afiperu.presenter.PointsOfReunionPresenter;
import com.afiperu.ui.fragment.MapEditFragment;

import dagger.Component;

/**
 * Created by DABARCA on 21/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = PointsOfReunionModule.class
)
public interface PointsOfReunionComponent {
    void inject(MapEditFragment fragment);
    PointsOfReunionPresenter getPresenter();
}

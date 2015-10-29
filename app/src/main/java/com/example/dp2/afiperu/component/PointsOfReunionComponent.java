package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.PointsOfReunionModule;
import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.ui.fragment.MapEditFragment;

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

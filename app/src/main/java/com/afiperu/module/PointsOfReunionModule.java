package com.afiperu.module;

import com.afiperu.interactor.PointsOfReunionInteractor;
import com.afiperu.presenter.PointsOfReunionPresenter;
import com.afiperu.ui.viewmodel.PointsOfReunionView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Fernando on 28/10/2015.
 */
@Module
public class PointsOfReunionModule {
    private PointsOfReunionView view;

    public PointsOfReunionModule(PointsOfReunionView view) {
        this.view = view;
    }

    @Provides
    public PointsOfReunionView provideView(){
        return view;
    }

    @Provides
    public PointsOfReunionPresenter providePresenter(PointsOfReunionView view, PointsOfReunionInteractor interactor){
        return new PointsOfReunionPresenter(view, interactor);
    }
}

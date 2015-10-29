package com.example.dp2.afiperu.module;

import com.example.dp2.afiperu.interactor.PointsOfReunionInteractor;
import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.ui.viewmodel.PointsOfReunionView;

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

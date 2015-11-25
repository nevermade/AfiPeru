package com.afiperu.module;

import com.afiperu.interactor.AttendanceInteractor;
import com.afiperu.interactor.CommentKidInteractor;
import com.afiperu.interactor.MainActivityInteractor;
import com.afiperu.interactor.SettingsInteractor;
import com.afiperu.presenter.MainActivityPresenter;
import com.afiperu.ui.viewmodel.MainActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 03/11/2015.
 */
@Module
public class MainActivityModule {
    private MainActivityView view;

    public MainActivityModule(MainActivityView view) {
        this.view = view;
    }

    @Provides
    public MainActivityView provideView(){
        return view;
    }

    @Provides
    public MainActivityPresenter providePresenter(MainActivityView view, MainActivityInteractor interactor,
                                                  CommentKidInteractor commentInteractor, AttendanceInteractor attendanceInteractor,
                                                  SettingsInteractor settingsInteractor){
        return new MainActivityPresenter(view, interactor, commentInteractor, attendanceInteractor, settingsInteractor);
    }

}

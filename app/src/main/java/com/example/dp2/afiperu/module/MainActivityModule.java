package com.example.dp2.afiperu.module;

import com.example.dp2.afiperu.interactor.AttendanceInteractor;
import com.example.dp2.afiperu.interactor.CommentKidInteractor;
import com.example.dp2.afiperu.interactor.MainActivityInteractor;
import com.example.dp2.afiperu.interactor.SettingsInteractor;
import com.example.dp2.afiperu.presenter.MainActivityPresenter;
import com.example.dp2.afiperu.ui.viewmodel.MainActivityView;

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

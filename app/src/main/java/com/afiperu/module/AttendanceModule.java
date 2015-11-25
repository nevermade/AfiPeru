package com.afiperu.module;

import android.content.Context;

import com.afiperu.common.BaseFragment;
import com.afiperu.interactor.AttendanceInteractor;
import com.afiperu.presenter.AttendancePresenter;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.afiperu.ui.adapter.AttendanceAdapter;
import com.afiperu.ui.viewmodel.AttendanceView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Fernando on 28/10/2015.
 */
@Module
public class AttendanceModule {
    private AttendanceView view;

    public AttendanceModule(AttendanceView view) {
        this.view = view;
    }

    @Provides
    public AttendanceView provideView(){
        return view;
    }

    @Provides
    public AttendancePresenter providePresenter(AttendanceView view, AttendanceInteractor interactor){
        return new AttendancePresenter(view, interactor);
    }
    @Provides
    public AttendanceAdapter provideAdapter(Context context, AttendanceView view) {
        return new AttendanceAdapter(((BaseFragment)view).getActivity(), (BaseFragment) view, new ArrayList<SyncAttendanceVolunteer>());
    }
}

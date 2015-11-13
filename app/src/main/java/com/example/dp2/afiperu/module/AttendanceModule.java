package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.interactor.AttendanceInteractor;
import com.example.dp2.afiperu.presenter.AttendancePresenter;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.example.dp2.afiperu.ui.adapter.AttendanceAdapter;
import com.example.dp2.afiperu.ui.viewmodel.AttendanceView;

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

package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.AttendanceInteractor;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.afiperu.ui.viewmodel.AttendanceView;

import java.util.List;

/**
 * Created by Fernando on 28/10/2015.
 */
public class AttendancePresenter extends BasePresenter {

    AttendanceInteractor interactor;
    AttendanceView view;

    public AttendancePresenter(AttendanceView view, AttendanceInteractor interactor){
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void editAttendance(Context context, int sessionId, List<SyncAttendanceVolunteer> attendanceVolunteers){
        interactor.editAttendance(context, this, sessionId, attendanceVolunteers);
    }

    public void saveSuccessful(){
        view.saveSuccessful();
    }

    public void saveFailed(){
        view.saveFailed();
    }
}

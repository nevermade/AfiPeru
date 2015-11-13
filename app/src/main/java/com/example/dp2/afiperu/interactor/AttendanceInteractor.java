package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.AttendancePresenter;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceVolunteer;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface AttendanceInteractor {
    void editAttendance(Context context, AttendancePresenter presenter, int sessionId, List<SyncAttendanceVolunteer> body);
}

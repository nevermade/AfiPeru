package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.AttendancePresenter;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface AttendanceInteractor {
    void editAttendance(Context context, AttendancePresenter presenter, int sessionId, List<SyncAttendanceVolunteer> body);
}

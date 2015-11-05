package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.presenter.AttendancePresenter;
import com.example.dp2.afiperu.rest.model.AttendanceBody;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface AttendanceInteractor {
    void editAttendance(AttendancePresenter presenter, AttendanceBody body);
                            
}

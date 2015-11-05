package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.NewPointOfReunion;
import com.example.dp2.afiperu.domain.PointOfReunion;
import com.example.dp2.afiperu.interactor.AttendanceInteractor;
import com.example.dp2.afiperu.interactor.PointsOfReunionInteractor;
import com.example.dp2.afiperu.rest.model.AttendanceBody;
import com.example.dp2.afiperu.rest.model.AttendanceVolunteer;
import com.example.dp2.afiperu.rest.model.MeetingPointsBody;
import com.example.dp2.afiperu.rest.model.RestVolunteer;
import com.example.dp2.afiperu.ui.viewmodel.AttendanceView;
import com.example.dp2.afiperu.ui.viewmodel.PointsOfReunionView;

import java.util.ArrayList;
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

    public void editAttendance(int sessionId, List<AttendanceVolunteer> attendanceVolunteers){
        AttendanceBody body = new AttendanceBody();
        body.setSessionId(sessionId);
        List<RestVolunteer> rv = new ArrayList<>();
        for (AttendanceVolunteer av : attendanceVolunteers){
            RestVolunteer r = new RestVolunteer();
            r.setId(av.getVolunteer().getId());
            if (av.getAttended()) r.setAttended(1); else r.setAttended(0);
            r.setComment(av.getComment());
            r.setRating(av.getRating());
            rv.add(r);
        }
        body.setVolunteers(rv);
        interactor.editAttendance(this, body);
    }

    public void saveSuccessful(){
        view.saveSuccessful();
    }

    public void saveFailed(){
        view.saveFailed();
    }
}

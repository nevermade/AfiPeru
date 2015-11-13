package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.AttendancePresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.AttendanceBody;
import com.example.dp2.afiperu.rest.model.RestVolunteer;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by Fernando on 28/10/2015.
 */
public class AttendanceInteractorImpl implements AttendanceInteractor {
    AfiApiServiceEndPoints service;

    public AttendanceInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void editAttendance(Context context, final AttendancePresenter presenter, int sessionId,
                               final List<SyncAttendanceVolunteer> volunteers) {
        SyncAttendanceVolunteer.deleteAll(SyncAttendanceVolunteer.class, "session = ?", String.valueOf(sessionId));

        if (NetworkManager.isNetworkConnected(context)) {
            AttendanceBody body = new AttendanceBody();
            body.setSessionId(sessionId);
            List<RestVolunteer> rv = new ArrayList<>();
            for(SyncAttendanceVolunteer volunteer : volunteers){
                RestVolunteer r = new RestVolunteer();
                r.setId(volunteer.getVolunteerId());
                r.setAttended(volunteer.getAttended());
                r.setRating(volunteer.getRating());
                r.setComment(volunteer.getComment());
                rv.add(r);
            }
            body.setVolunteers(rv);
            Call<Void> result = service.editAttendance(body);
            result.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(retrofit.Response<Void> response, Retrofit retrofit) {
                    if (response.raw().code() == 200) {
                        for(SyncAttendanceVolunteer volunteer : volunteers){
                            volunteer.setNeedsync(0);
                            volunteer.save();
                        }
                        if(presenter != null) presenter.saveSuccessful();
                    } else {
                        if(presenter != null) presenter.saveFailed();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    if(presenter != null) presenter.saveFailed();
                }
            });
        } else {
            for(SyncAttendanceVolunteer volunteer : volunteers){
                volunteer.setNeedsync(1);
                volunteer.save();
            }
        }
    }

}

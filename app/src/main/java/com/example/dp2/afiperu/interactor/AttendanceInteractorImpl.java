package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.presenter.AttendancePresenter;
import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.AttendanceBody;
import com.example.dp2.afiperu.rest.model.MeetingPointsBody;

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
    public void editAttendance(final AttendancePresenter presenter, AttendanceBody body) {
        Call<Void> result = service.editAttendance(body);

        result.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(retrofit.Response<Void> response, Retrofit retrofit) {
                if(response.body() != null) {
                    presenter.saveSuccessful();
                }else{
                    presenter.saveFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.saveFailed();
            }
        });
    }

}

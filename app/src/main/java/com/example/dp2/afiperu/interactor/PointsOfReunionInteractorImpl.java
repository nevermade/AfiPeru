package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.NewPointOfReunion;
import com.example.dp2.afiperu.domain.PointOfReunion;
import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.MeetingPointsBody;
import retrofit.Response;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by Fernando on 28/10/2015.
 */
public class PointsOfReunionInteractorImpl implements PointsOfReunionInteractor {
    AfiApiServiceEndPoints service;

    public PointsOfReunionInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void editPointsOfReunion(final PointsOfReunionPresenter presenter, MeetingPointsBody body) {
        Call<Response> result = service.editMeetingPoints(body);

        result.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(retrofit.Response<Response> response, Retrofit retrofit) {
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

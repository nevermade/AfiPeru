package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.PointOfReunion;
import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.presenter.SessionPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.squareup.okhttp.Response;

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
    public void editPointsOfReunion(final PointsOfReunionPresenter presenter, int sessionId,
                                    List<PointOfReunion> uneditedPoints,
                                    //List<LatLngPoint> createdPoints,
                                    List<PointOfReunion> deletedPoints) {
        Call<Response> result = service.editMeetingPoints(sessionId, uneditedPoints,
                //createdPoints,
                deletedPoints);
        result.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(retrofit.Response<Response> response, Retrofit retrofit) {
                presenter.saveSuccessful();
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.saveFailed();
            }
        });
    }

}

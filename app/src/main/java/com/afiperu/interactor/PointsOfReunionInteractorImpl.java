package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.PointsOfReunionPresenter;
import com.afiperu.rest.AfiApiServiceEndPoints;
import com.afiperu.rest.model.MeetingPointsBody;
import com.afiperu.util.NetworkManager;

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
    public void editPointsOfReunion(Context context, final PointsOfReunionPresenter presenter, MeetingPointsBody body) {
        if(NetworkManager.isNetworkConnected(context)) {
            Call<Void> result = service.editMeetingPoints(body);

            result.enqueue(new Callback<Void>() {

                @Override
                public void onResponse(retrofit.Response<Void> response, Retrofit retrofit) {
                    if (response.raw().code() == 200) {
                        presenter.saveSuccessful();
                    } else {
                        presenter.saveFailed();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.saveFailed();
                }
            });
        }else{
            presenter.onNoInternet(context);
        }
    }

}

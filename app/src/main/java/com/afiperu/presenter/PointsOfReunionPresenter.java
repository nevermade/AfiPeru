package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.domain.NewPointOfReunion;
import com.afiperu.domain.PointOfReunion;
import com.afiperu.interactor.PointsOfReunionInteractor;
import com.afiperu.rest.model.MeetingPointsBody;
import com.afiperu.ui.viewmodel.PointsOfReunionView;

import java.util.List;

/**
 * Created by Fernando on 28/10/2015.
 */
public class PointsOfReunionPresenter extends BasePresenter {

    PointsOfReunionInteractor interactor;
    PointsOfReunionView view;

    public PointsOfReunionPresenter(PointsOfReunionView view, PointsOfReunionInteractor interactor){
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void editMeetingPoints(Context context, int sessionId, List<PointOfReunion> previousPoints, List<NewPointOfReunion> newPoints){
        MeetingPointsBody body = new MeetingPointsBody();
        body.setSessionId(sessionId);
        body.setPreviousPoints(previousPoints);
        body.setNewPoints(newPoints);
        interactor.editPointsOfReunion(context, this, body);
    }

    public void saveSuccessful(){
        view.saveSuccessful();
    }

    public void saveFailed(){
        view.saveFailed();
    }
}

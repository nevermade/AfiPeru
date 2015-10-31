package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.NewPointOfReunion;
import com.example.dp2.afiperu.domain.PointOfReunion;
import com.example.dp2.afiperu.interactor.PointsOfReunionInteractor;
import com.example.dp2.afiperu.rest.model.MeetingPointsBody;
import com.example.dp2.afiperu.ui.viewmodel.PointsOfReunionView;

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

    public void editMeetingPoints(int sessionId, List<PointOfReunion> previousPoints, List<NewPointOfReunion> newPoints){
        MeetingPointsBody body = new MeetingPointsBody();
        body.setSessionId(sessionId);
        body.setPreviousPoints(previousPoints);
        body.setNewPoints(newPoints);
        interactor.editPointsOfReunion(this, body);
    }

    public void saveSuccessful(){
        view.saveSuccessful();
    }

    public void saveFailed(){
        view.saveFailed();
    }
}

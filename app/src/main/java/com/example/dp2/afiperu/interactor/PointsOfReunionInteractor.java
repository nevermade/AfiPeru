package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.rest.model.MeetingPointsBody;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface PointsOfReunionInteractor {
    void editPointsOfReunion(PointsOfReunionPresenter presenter, MeetingPointsBody body);
                            
}

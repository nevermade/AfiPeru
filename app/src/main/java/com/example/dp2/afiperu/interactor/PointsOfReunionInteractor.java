package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.PointOfReunion;
import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface PointsOfReunionInteractor {
    void editPointsOfReunion(PointsOfReunionPresenter presenter, int sessionId,
                             List<PointOfReunion> uneditedPoints,
                             //List<LatLng> createdPoints,
                             List<PointOfReunion> deletedPoints);

}

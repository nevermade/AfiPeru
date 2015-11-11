package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.rest.model.MeetingPointsBody;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface PointsOfReunionInteractor {
    void editPointsOfReunion(Context context, PointsOfReunionPresenter presenter, MeetingPointsBody body);
                            
}

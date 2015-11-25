package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.PointsOfReunionPresenter;
import com.afiperu.rest.model.MeetingPointsBody;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface PointsOfReunionInteractor {
    void editPointsOfReunion(Context context, PointsOfReunionPresenter presenter, MeetingPointsBody body);
                            
}

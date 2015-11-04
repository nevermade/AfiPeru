package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.Location;
import com.example.dp2.afiperu.domain.PointOfReunion;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.presenter.SessionPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class SessionInteractorImpl implements SessionInteractor {
    AfiApiServiceEndPoints service;

    public SessionInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void getAllSessions(final SessionPresenter presenter) {
        /*ArrayList<Session> sessions = new ArrayList<>();
        Calendar calendar = new GregorianCalendar(2015, 8, 16, 16, 0);
        Session session = new Session("Cerro el Pino", (int)calendar.getTime().getTime());
        Location location = new Location();
        location.setLatitude(-12.0731492);
        location.setLongitude(-77.0819083);
        session.setLocation(location);
        ArrayList<PointOfReunion> pointsOfReunion = new ArrayList<>();
        PointOfReunion pointOfReunion = new PointOfReunion();
        pointOfReunion.setId(1);
        pointOfReunion.setLatitude(-12.0767993);
        pointOfReunion.setLongitude(-77.0811531);
        pointOfReunion.setSelected(1);
        pointsOfReunion.add(pointOfReunion);
        pointOfReunion = new PointOfReunion();
        pointOfReunion.setId(2);
        pointOfReunion.setLatitude(-12.0587955);
        pointOfReunion.setLongitude(-77.0815501);
        pointOfReunion.setSelected(1);
        pointsOfReunion.add(pointOfReunion);
        pointOfReunion = new PointOfReunion();
        pointOfReunion.setId(3);
        pointOfReunion.setLatitude(-12.067451);
        pointOfReunion.setLongitude(-77.061305);
        pointOfReunion.setSelected(1);
        pointsOfReunion.add(pointOfReunion);
        session.setId(-1);
        session.setPointsOfReunion(pointsOfReunion);
        sessions.add(session);
        presenter.onSessionFound(sessions);*/

        Call<List<Session>> call = service.getAllSessions();
        call.enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Response<List<Session>> response, Retrofit retrofit) {
                ArrayList<Session> sessions=(ArrayList<Session>)response.body();

                if(sessions!=null)
                    presenter.onSessionFound(sessions);
            }

            @Override
            public void onFailure(Throwable t){
            }
        });


     }
}

package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.MarkerInfo;
import com.example.dp2.afiperu.domain.PointsOfReunion;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.presenter.SessionPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;


import java.util.ArrayList;
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

       /* ArrayList<MarkerInfo> markers = new ArrayList<>();
        markers.add(new MarkerInfo(-12.0731492, -77.0819083, MarkerInfo.MARKER_KIND_SESSION_ADDRESS, null));
        markers.add(new MarkerInfo(-12.0767993, -77.0811531, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
        markers.add(new MarkerInfo(-12.0587955, -77.0815501, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
        markers.add(new MarkerInfo(-12.067451, -77.061305, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));

        ArrayList<SessionResponse> sessions = new ArrayList<>();
        Calendar calendar;
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new SessionResponse("Cerro el Pino", calendar.getTime().getTime(), markers));
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new SessionResponse("Cerro el Pino", calendar.getTime().getTime(), markers));
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new SessionResponse("Cerro el Pino", calendar.getTime().getTime(), markers));
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new SessionResponse("Cerro el Pino", calendar.getTime().getTime(), markers));
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new SessionResponse("Cerro el Pino", calendar.getTime().getTime(), markers));
        Collections.sort(sessions);
        return null;*/

        Call<List<Session>> call = service.getAllSessions();
        call.enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Response<List<Session>> response, Retrofit retrofit) {
                ArrayList<Session> sessions=(ArrayList<Session>)response.body();

                if(sessions!=null)
                    presenter.onSessionFound(sessions);
            }

            @Override
            public void onFailure(Throwable t) {
                if(true);
            }
        });


    }
}

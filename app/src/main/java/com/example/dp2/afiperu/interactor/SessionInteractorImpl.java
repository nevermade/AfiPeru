package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.MarkerInfo;
import com.example.dp2.afiperu.domain.Session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class SessionInteractorImpl implements SessionInteractor {
    @Override
    public ArrayList<Session> getAllSessions() {

        ArrayList<MarkerInfo> markers = new ArrayList<>();
        markers.add(new MarkerInfo(-12.0731492, -77.0819083, MarkerInfo.MARKER_KIND_SESSION_ADDRESS, null));
        markers.add(new MarkerInfo(-12.0767993, -77.0811531, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
        markers.add(new MarkerInfo(-12.0587955, -77.0815501, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
        markers.add(new MarkerInfo(-12.067451, -77.061305, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));

        ArrayList<Session> sessions = new ArrayList<>();
        Calendar calendar;
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
        calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
        sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
        Collections.sort(sessions);
        return null;
    }
}

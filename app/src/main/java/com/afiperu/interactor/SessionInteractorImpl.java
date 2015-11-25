package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.domain.Comment;
import com.afiperu.domain.Document;
import com.afiperu.domain.DocumentUser;
import com.afiperu.domain.Kid;
import com.afiperu.domain.PointOfReunion;
import com.afiperu.domain.Session;
import com.afiperu.presenter.SessionPresenter;
import com.afiperu.rest.AfiApiServiceEndPoints;
import com.afiperu.rest.model.AttendanceChild;
import com.afiperu.rest.model.AttendanceVolunteer;
import com.afiperu.syncmodel.SyncAttendanceChild;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.syncmodel.SyncDocument;
import com.afiperu.syncmodel.SyncDocumentUser;
import com.afiperu.syncmodel.SyncKid;
import com.afiperu.syncmodel.SyncLocation;
import com.afiperu.syncmodel.SyncPointOfReunion;
import com.afiperu.syncmodel.SyncSession;
import com.afiperu.util.NetworkManager;

import java.util.Collections;
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
    public void getAllSessions(Context context, final SessionPresenter presenter) {
        if(NetworkManager.isNetworkConnected(context)){
            Call<List<Session>> call = service.getAllSessions();
            call.enqueue(new Callback<List<Session>>() {
                @Override
                public void onResponse(Response<List<Session>> response, Retrofit retrofit) {
                    List<Session> result = response.body();
                    if(result != null) {
                        SyncSession.deleteAll(SyncSession.class);
                        SyncDocument.deleteAll(SyncDocument.class, "session != ?", "0");
                        SyncDocumentUser.deleteAll(SyncDocumentUser.class, "session != ?", "0");
                        SyncPointOfReunion.deleteAll(SyncPointOfReunion.class);
                        SyncLocation.deleteAll(SyncLocation.class, "session != ?", "0");
                        SyncAttendanceChild.deleteAll(SyncAttendanceChild.class);
                        SyncKid.deleteAll(SyncKid.class, "attendance_child != ?", "0");
                        SyncComment.deleteAll(SyncComment.class, "attendance_child != ?", "0");
                        SyncAttendanceVolunteer.deleteAll(SyncAttendanceVolunteer.class);

                        for(Session session : result){
                            SyncSession ses = SyncSession.fromSession(session);
                            SyncLocation location = SyncLocation.fromLocation(session.getLocation(), session.getId());
                            location.save();
                            ses.setLocation(location);
                            ses.save();
                            for(PointOfReunion pointOfReunion : session.getPointsOfReunion()){
                                SyncPointOfReunion point = SyncPointOfReunion.fromPointOfReunion(pointOfReunion);
                                point.setSession(ses.getSessionId());
                                point.save();
                            }
                            for(Document document : session.getDocuments()){
                                SyncDocument doc = SyncDocument.fromDocument(document, 0);
                                doc.setSession(ses.getSessionId());
                                doc.save();
                                for(DocumentUser user : document.getUsers()){
                                    SyncDocumentUser docUser = SyncDocumentUser.fromDocumentUser(user);
                                    docUser.setSession(ses.getSessionId());
                                    docUser.setDocument(doc);
                                    docUser.save();
                                }
                            }
                            for(AttendanceChild attendanceChild : session.getAttendanceChildren()){
                                Kid k = attendanceChild.getChild();
                                SyncKid kid = SyncKid.fromKid(k);
                                kid.setAttendanceChild(attendanceChild.getId());
                                kid.save();
                                Comment c = attendanceChild.getComment();
                                SyncComment comment = SyncComment.fromComment(c);
                                if(comment != null) {
                                    comment.setSession(ses.getSessionId());
                                    comment.setAttendanceChild(attendanceChild.getId());
                                    comment.setKidId(k.getId());
                                    comment.save();
                                }
                                SyncAttendanceChild child = new SyncAttendanceChild(attendanceChild.getId(), kid, comment);
                                child.setSession(ses.getSessionId());
                                child.save();
                            }
                            for(AttendanceVolunteer attendanceVolunteer : session.getAttendanceVolunteers()){
                                SyncAttendanceVolunteer volunteer = SyncAttendanceVolunteer.fromAttendanceVolunteer(attendanceVolunteer);
                                volunteer.setSession(ses.getSessionId());
                                volunteer.save();
                            }
                        }

                        List<SyncSession> sessions = SyncSession.listAll(SyncSession.class);
                        Collections.sort(sessions);
                        presenter.onSessionFound(sessions);
                    }else{
                        presenter.onSessionErrorOrFailure();
                    }
                }

                @Override
                public void onFailure(Throwable t){
                    presenter.onSessionErrorOrFailure();
                }
            });
        }else{
            List<SyncSession> sessions = SyncSession.listAll(SyncSession.class);
            Collections.sort(sessions);
            presenter.onSessionFound(sessions);
        }
     }

    @Override
    public void getAllSessionsCalendar(Context context, SessionPresenter presenter) {
        List<SyncSession> sessions = SyncSession.listAll(SyncSession.class);
        Collections.sort(sessions);
        presenter.onSessionFoundCalendar(sessions);
    }
}

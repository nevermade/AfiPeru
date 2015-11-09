package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.domain.DocumentUser;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.domain.PointOfReunion;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.presenter.SessionPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.AttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.syncmodel.SyncDocument;
import com.example.dp2.afiperu.syncmodel.SyncDocumentUser;
import com.example.dp2.afiperu.syncmodel.SyncKid;
import com.example.dp2.afiperu.syncmodel.SyncLocation;
import com.example.dp2.afiperu.syncmodel.SyncPointOfReunion;
import com.example.dp2.afiperu.syncmodel.SyncSession;
import com.example.dp2.afiperu.util.Constants;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.ArrayList;
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
                        //Adjust date
                        for(Session session : result){
                            session.setDate(session.getDate()*1000);
                        }

                        SyncSession.deleteAll(SyncSession.class);
                        SyncDocument.deleteAll(SyncDocument.class, "session != ?", "0");
                        SyncDocumentUser.deleteAll(SyncDocumentUser.class, "session != ?", "0");
                        SyncPointOfReunion.deleteAll(SyncPointOfReunion.class);
                        SyncLocation.deleteAll(SyncLocation.class, "session != ?", "0");
                        SyncAttendanceChild.deleteAll(SyncAttendanceChild.class);
                        SyncKid.deleteAll(SyncKid.class, "attendance_child != ?", "0");
                        SyncComment.deleteAll(SyncComment.class, "author_names = ?",
                                Constants.loggedUser.getName() + " " + Constants.loggedUser.getLastName());
                        //SyncAttendanceVolunteer.deleteAll(SyncAttendanceVolunteer.class);

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
                                SyncDocument doc = SyncDocument.fromDocument(document);
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
                                    comment.save();
                                }
                                SyncAttendanceChild child = new SyncAttendanceChild(attendanceChild.getId(), kid, comment);
                                child.setSession(ses.getSessionId());
                                child.save();
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
        }
     }
}

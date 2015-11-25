package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.domain.Comment;
import com.afiperu.domain.Kid;
import com.afiperu.presenter.CommentPresenter;
import com.afiperu.rest.AfiApiServiceEndPoints;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.syncmodel.SyncSession;
import com.afiperu.util.NetworkManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class CommentInteractorImpl implements CommentInteractor {
    AfiApiServiceEndPoints service;

    public CommentInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void getKidAndComment(final CommentPresenter presenter, Context context, final Integer kidId){
        if(NetworkManager.isNetworkConnected(context)) {
            Call<Kid> call = service.getKidAndComments(kidId);
            call.enqueue(new Callback<Kid>() {
                @Override
                public void onResponse(Response<Kid> response, Retrofit retrofit) {
                    Kid result = response.body();
                    if(result != null) {
                        List<SyncComment> ownComments = SyncComment.find(SyncComment.class,
                                "attendance_child != ? and kid_id = ?", "0", String.valueOf(kidId));
                        SyncComment.deleteAll(SyncComment.class, "kid_id = ?", String.valueOf(kidId));

                        List<Comment> list = result.getComments();
                        for(Comment c : list){
                            SyncComment comment = SyncComment.fromComment(c);
                            comment.setSession(c.getSessionId());
                            comment.setKidId(kidId);
                            comment.setNeedsync(0);
                            //El comentario no debe perder la referencia a AttendanceChild en caso sea del mismo usuario
                            for(SyncComment ownComment : ownComments){
                                if(ownComment.getSession().equals(c.getSessionId())
                                        && ownComment.getAuthorId().equals(c.getAuthor().getCod())){
                                    comment.setAttendanceChild(ownComment.getAttendanceChild());
                                    break;
                                }
                            }
                            comment.save();
                        }

                        List<SyncComment> comments = SyncComment.find(SyncComment.class, "kid_id = ?", String.valueOf(kidId));
                        Collections.sort(comments);
                        presenter.showComments(comments);
                    }else{
                        presenter.onFailure();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.onFailure();
                }
            });
        }else{
            List<SyncComment> comments = SyncComment.find(SyncComment.class, "kid_id = ?", String.valueOf(kidId));
            Collections.sort(comments);
            presenter.showComments(comments);
        }
    }

    @Override
    public void queryKidAndComment(CommentPresenter presenter, Context context, Integer kidId, String autorcomment, long datefrom, long dateto, String orderBy) {
        List<SyncComment> comments = SyncComment.find(SyncComment.class, "kid_id = ?", String.valueOf(kidId));
        List<SyncComment> listafinal = new ArrayList<SyncComment>();

        for (SyncComment item : comments){
            String authorOrComment = item.getAuthorNames()+ " "+item.getMessage();
            boolean bName = authorOrComment.toLowerCase().contains(autorcomment.toLowerCase());
            boolean bDateini = true;
            boolean bDatefin = true;

            List<SyncSession> se = SyncSession.find(SyncSession.class, "session_id = ?", String.valueOf(item.getSession()));

            long date = se.get(0).getDate();

            if (datefrom==-1){
                bDateini=true;
            }else if (date>=datefrom)
                bDateini=true;
            else
                bDateini=false;

            if (dateto==-1){
                bDatefin=true;
            }else
                if (date<=dateto) bDatefin=true;
            else
                    bDatefin=false;

            if (bName&&bDateini &&bDatefin ){
                listafinal.add(item);
            }
        }
        try {
            if (orderBy.contentEquals("Más actual"))
                Collections.sort(listafinal, new CustomComparator1());
            else
                Collections.sort(listafinal, new CustomComparator2());
        }catch  (Exception e){
            Collections.sort(listafinal);
        }
        presenter.showComments(listafinal);
    }

    public class CustomComparator1 implements Comparator<SyncComment> {
        @Override
        public int compare(SyncComment o1, SyncComment o2) {
            List<SyncSession> se1 = SyncSession.find(SyncSession.class, "session_id = ?", String.valueOf(o1.getSession()));
            List<SyncSession> se2 = SyncSession.find(SyncSession.class, "session_id = ?", String.valueOf(o2.getSession()));
            return se1.get(0).getDate().compareTo(se2.get(0).getDate());
        }
    }


    public class CustomComparator2 implements Comparator<SyncComment> {
        @Override
        public int compare(SyncComment o1, SyncComment o2) {
            List<SyncSession> se1 = SyncSession.find(SyncSession.class, "session_id = ?", String.valueOf(o1.getSession()));
            List<SyncSession> se2 = SyncSession.find(SyncSession.class, "session_id = ?", String.valueOf(o2.getSession()));
            return se2.get(0).getDate().compareTo(se1.get(0).getDate());
        }
    }

}

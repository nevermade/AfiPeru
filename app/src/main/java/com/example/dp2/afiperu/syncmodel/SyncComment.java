package com.example.dp2.afiperu.syncmodel;

import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.util.Constants;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 08/11/2015.
 */
public class SyncComment extends SugarRecord<SyncComment> implements Serializable, Comparable<SyncComment> {

    private Integer session;

    private String message;
    private Integer face;
    private String authorNames;

    public SyncComment(){}
    public SyncComment(String message, Integer face, String authorNames) {
        this.message = message;
        this.face = face;
        this.authorNames = authorNames;
        this.session = 0;
    }

    public String getMessage() {
        return message;
    }

    public Integer getFace() {
        return face;
    }

    public String getAuthorNames() {
        return authorNames;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public int compareTo(SyncComment o2){
        int result = session.compareTo(o2.session);
        if(result == 0){
            return authorNames.compareTo(o2.authorNames);
        }else{
            return result;
        }
    }

    public static SyncComment fromComment(Comment comment){
        if(comment == null){
            return null;
        }else {
            User user = comment.getAuthor();
            if(user == null){
                user = Constants.loggedUser;
            }
            return new SyncComment(comment.getMessage(), comment.getFace(), user.getName() + " " + user.getLastName());
        }
    }

    public static List<SyncComment> fromComment(List<Comment> comments){
        ArrayList<SyncComment> result = new ArrayList<>();
        for(Comment comment : comments){
            result.add(SyncComment.fromComment(comment));
        }
        return result;
    }
}

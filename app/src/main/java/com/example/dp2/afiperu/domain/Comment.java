package com.example.dp2.afiperu.domain;

import java.io.Serializable;

/**
 * Created by Nevermade on 30/09/2015.
 */
public class Comment implements Serializable, Comparable<Comment>{
    private int iconId;
    private String name;
    private String comment;
    //private String gender;


    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
        //  this.gender = gender;
//        this.iconId = iconId;
    }

    public String getName() { return name; }

    public String getComment() {
        return comment;
    }

    @Override
    public int compareTo(Comment another) {
        return Long.valueOf(another.name).compareTo(Long.valueOf(name));
    }
/**/
}

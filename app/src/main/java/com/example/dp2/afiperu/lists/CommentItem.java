package com.example.dp2.afiperu.lists;

import java.io.Serializable;

/**
 * Created by Nevermade on 30/09/2015.
 */
public class CommentItem implements Serializable, Comparable<CommentItem>{
    private int iconId;
    private String name;
    private String comment;
    //private String gender;


    public CommentItem(String name, String comment) {
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
    public int compareTo(CommentItem another) {
        return Long.valueOf(another.name).compareTo(Long.valueOf(name));
    }
/**/
}

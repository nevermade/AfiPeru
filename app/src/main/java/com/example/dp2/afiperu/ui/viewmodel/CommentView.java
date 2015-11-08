package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.domain.Comment;

import java.util.List;

/**
 * Created by Fernando on 07/11/2015.
 */
public interface CommentView {
    void showComments(List<Comment> comments);
}

package com.afiperu.ui.viewmodel;

import com.afiperu.syncmodel.SyncComment;

import java.util.List;

/**
 * Created by Fernando on 07/11/2015.
 */
public interface CommentView {
    void showComments(List<SyncComment> comments);
    void onFailure();
}

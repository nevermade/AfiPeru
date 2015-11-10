package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.syncmodel.SyncAttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncComment;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface CommentKidInteractor {
    void makeComment(Context context, SyncAttendanceChild child, SyncComment comment);
}

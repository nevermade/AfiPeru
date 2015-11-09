package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.syncmodel.SyncSession;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface SessionView {

    void displaySessions(List<SyncSession> sessions);
    void displaySessionsErrorOrFailure();
}

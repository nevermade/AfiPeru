package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.syncmodel.SyncDocument;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface DocumentView {

    void displayDocuments(List<SyncDocument> sessions);
    void onFailure();
}

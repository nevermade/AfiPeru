package com.afiperu.ui.viewmodel;

import com.afiperu.syncmodel.SyncDocumentUser;

import java.util.ArrayList;

/**
 * Created by Nevermade on 20/10/2015.
 */
public interface DownloadedUserView {

    void displayDownloadedUsers(ArrayList<SyncDocumentUser> users);
}

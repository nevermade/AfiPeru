package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.syncmodel.SyncSchoolAddress;
import com.example.dp2.afiperu.syncmodel.SyncUser;

import java.util.List;

/**
 * Created by Nevermade on 23/10/2015.
 */
public interface UserView {
    void showUsers(List<SyncUser> users);
    void showLocations(List<SyncSchoolAddress> locations);
    void displayErrorOrFailure();
    void onLocationsFailure();
}

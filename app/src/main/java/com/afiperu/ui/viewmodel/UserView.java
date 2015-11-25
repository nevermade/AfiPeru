package com.afiperu.ui.viewmodel;

import com.afiperu.syncmodel.SyncSchoolAddress;
import com.afiperu.syncmodel.SyncUser;

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

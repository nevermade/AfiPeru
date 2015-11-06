package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.rest.model.LocationsBody;
import com.example.dp2.afiperu.syncmodel.SyncUser;

import java.util.List;

/**
 * Created by Nevermade on 23/10/2015.
 */
public interface UserView {
    //void showApp(String name);
    void showUsers(List<SyncUser> users);

    void showLocations(LocationsBody locations);

    void displayErrorOrFailure();
}

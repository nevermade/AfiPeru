package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.User;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class DownloadedUserInteractorImpl implements DownloadedUserInteractor {
    @Override
    public ArrayList<User> getAllDownloadedUsers() {
        ArrayList<User> users= new ArrayList<>();
        users.add(new User("dabarca","20101147","Daekef","Abarca","Cusimayta",3.5,true));
        users.add(new User("fbanda","20107845","Fernando","Banda","Cardenas",4.8,true));
        users.add(new User("lbarcena","20101019","Luis","Barcena","Navarro",1.0,false));
        Collections.sort(users);
        return users;
    }
}

package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.syncmodel.SyncKid;
import com.example.dp2.afiperu.syncmodel.SyncUser;

import java.util.ArrayList;

/**
 * Created by Nevermade on 23/10/2015.
 */
public interface KidView {
    //void showApp(String name);
    void showKids(ArrayList<SyncKid> kids);
}

package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.syncmodel.SyncKid;
import java.util.List;

/**
 * Created by Nevermade on 23/10/2015.
 */
public interface KidView {
    void showKids(List<SyncKid> kids);
    void onFailure();
}

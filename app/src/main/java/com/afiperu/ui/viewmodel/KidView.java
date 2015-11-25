package com.afiperu.ui.viewmodel;

import com.afiperu.syncmodel.SyncKid;
import java.util.List;

/**
 * Created by Nevermade on 23/10/2015.
 */
public interface KidView {
    void showKids(List<SyncKid> kids);
    void onFailure();
}

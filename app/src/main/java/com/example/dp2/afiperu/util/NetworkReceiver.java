package com.example.dp2.afiperu.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.presenter.MainActivityPresenter;
import com.example.dp2.afiperu.rest.model.AttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.syncmodel.SyncDocument;
import com.example.dp2.afiperu.ui.activity.DetailActivity;

import java.util.List;

/**
 * Created by Usuario on 04/11/2015.
 */

public class NetworkReceiver extends BroadcastReceiver {

    public String WIFI = "Wi-Fi";
    public String ANY = "Any";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        if (networkInfo != null && (networkInfo.getType() == ConnectivityManager.TYPE_WIFI || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)) {
            if(Constants.loggedUser != null) {
                MainActivityPresenter presenter = ((DetailActivity) context).getPresenter();

                //Sincronizar
                List<SyncComment> comments = SyncComment.find(SyncComment.class, "needsync = ?", "1");
                if (!comments.isEmpty()) {
                    Toast.makeText(context, context.getString(R.string.sync_comments), Toast.LENGTH_SHORT).show();
                    for (SyncComment comment : comments) {
                        List<SyncAttendanceChild> child = SyncAttendanceChild.find(SyncAttendanceChild.class, "attendance_child_id = ?",
                                String.valueOf(comment.getAttendanceChild()));
                        presenter.makeComment(context, child.get(0), comment);
                    }
                }
            }
        } else {
            Toast.makeText(context, context.getString(R.string.offline), Toast.LENGTH_SHORT).show();
        }
    }

}
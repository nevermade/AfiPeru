package com.afiperu.util;

/**
 * Created by Usuario on 18/11/2015.
 */
import com.afiperu.R;
import com.afiperu.ui.activity.DetailActivity;
import com.google.android.gms.gcm.GcmListenerService;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class GcmMessageHandler extends GcmListenerService {
    public static final int MESSAGE_NOTIFICATION_ID = 435345;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.d("GCM","GCMMessage Received");
        String title = data.getString("title");
        String message = data.getString("message");
        String type = data.getString("type");
        createNotification(title + type, message);

    }

    // Creates notification based on title and body received
    private void createNotification(String title, String body) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.afi_white)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());





        /*



        Context context = getBaseContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.afi_white).setContentTitle(title)
                .setContentText(body);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, DetailActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);


        mBuilder.setContentIntent(contentIntent);


        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
        */
    }

}
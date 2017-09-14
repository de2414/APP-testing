package com.example.frei.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Set;

/**
 * Created by frei on 2017/9/6.
 */

public class MyFirebaseMsgService extends FirebaseMessagingService {
    //專門接收訊息
    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //Log.d(TAG,"onMessageReceived 收到訊息");
        
        Log.e(TAG,remoteMessage.getNotification().getBody());


        //透過key value取值
        Set<String> keys = remoteMessage.getData().keySet();
        for (String s :keys){

            Log.e("MCF data",remoteMessage.getData().get(s));

        }


    }



    private void sendNotification(String msgBody){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSondUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_buble)
                .setContentTitle("CokeMan推播通知")
                .setContentText(msgBody)
                .setAutoCancel(true)
                .setSound(defaultSondUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

    }
}

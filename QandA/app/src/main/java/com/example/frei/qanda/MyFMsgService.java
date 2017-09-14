package com.example.frei.qanda;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by frei on 2017/9/8.
 */

public class MyFMsgService extends FirebaseMessagingService {

    private static final String TAG = "MyFMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String msg = remoteMessage.getNotification().getBody();
        Log.d(TAG,"msg received:"+msg);

    }
}

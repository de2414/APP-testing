package com.example.frei.fcm;

import android.nfc.Tag;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by frei on 2017/9/6.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    //專門接收id
    private static final String TAG = "MyFirebaseIIdService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG,"refreshToken刷新："+refreshToken);
    }
}

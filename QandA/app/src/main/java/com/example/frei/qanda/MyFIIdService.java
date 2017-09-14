package com.example.frei.qanda;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by frei on 2017/9/8.
 */

public class MyFIIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFIIdService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"refreshToken:"+refreshToken);
    }
}

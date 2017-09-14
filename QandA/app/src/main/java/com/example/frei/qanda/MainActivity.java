package com.example.frei.qanda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView wellcome;
    private FirebaseAuth mAuth;
    private AdView mAdview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        wellcome = (TextView) findViewById(R.id.tv_wellcome);

        mAdview = (AdView)findViewById(R.id.mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

    }

    @Override
    public void onClick(View view) {
        //Firebase sign out
        mAuth.signOut();

        Intent intent = new Intent();
        intent.setClass(this,HomeActivity.class);
        startActivity(intent);
        finish();

    }
}

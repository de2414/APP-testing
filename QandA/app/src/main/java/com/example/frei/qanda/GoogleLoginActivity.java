package com.example.frei.qanda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class GoogleLoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;
    private TextView acct;
    private Button signOutButton;
    private static final int CODE_SIGNIN = 7878;
    private static final String TAG = "GoogleActivity";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);
        init();
    }

    private void init() {
        signInButton = (SignInButton) findViewById(R.id.sib_google);
        signInButton.setOnClickListener(this);
        signOutButton = (Button) findViewById(R.id.btn_googleOut);
        signOutButton.setOnClickListener(this);
        acct = (TextView) findViewById(R.id.tv_acct);
        mAuth = FirebaseAuth.getInstance();

        //google 登入選項
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this/* Activity */, this/*  OnConnectionFailedListener*/)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sib_google:
                signIn();
                break;
            case R.id.btn_googleOut:
                signOut();
                break;
        }

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,CODE_SIGNIN);
    }
    private void signOut(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                acct.setText("Signed out");

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CODE_SIGNIN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG,"handleSignInResult:"+result.isSuccess());
        if (result.isSuccess()){
            //成功
            GoogleSignInAccount account = result.getSignInAccount();
            //顯示名稱
            acct.setText("已登入");

            Intent intent = new Intent();
            intent.setClass(GoogleLoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }else{
            //失敗
            Toast.makeText(GoogleLoginActivity.this,"失敗",Toast.LENGTH_SHORT).show();
        }
    }
}

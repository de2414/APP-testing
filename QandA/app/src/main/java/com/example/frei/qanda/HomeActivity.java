package com.example.frei.qanda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button google;
    private Button login;
    private Button signUp;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = (Button) findViewById(R.id.btn_login);
        signUp = (Button) findViewById(R.id.btn_signUp);
        google = (Button)findViewById(R.id.btn_google);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_google:
                googleSignIn();
                break;
            case R.id.btn_login:
                emailLogin();
                break;
            case R.id.btn_signUp:
                emailSignUp();
                break;
            default:
                normal();
                break;
        }
    }

    private void googleSignIn() {
        Intent intent = new Intent(this,GoogleLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    private void emailLogin(){
        Intent intent = new Intent(this,EmailLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void emailSignUp(){
        Intent intent = new Intent(this,SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
    private void normal(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}





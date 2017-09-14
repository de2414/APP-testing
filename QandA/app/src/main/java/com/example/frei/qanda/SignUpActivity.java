package com.example.frei.qanda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "監聽器AuthListener";
    private String id;
    private String pwd;
    private EditText accountEdit;
    private EditText pwdEdit;
    private TextInputLayout accountLayout;
    private TextInputLayout pwdLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...

            }
        };

    }

    private void init() {
        accountEdit = (EditText) findViewById(R.id.et_account);
        pwdEdit = (EditText) findViewById(R.id.et_password);
        accountLayout = (TextInputLayout) findViewById(R.id.til_acc);
        pwdLayout = (TextInputLayout) findViewById(R.id.til_pwd);

        //pwdLayout.setCounterMaxLength(12);
        //pwdLayout.setCounterEnabled(true);


    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }


    @Override
    public void onClick(View view) {
        id = accountEdit.getText().toString();
        pwd = pwdEdit.getText().toString();

        //如果輸入欄為空 設定錯誤提示

        if (TextUtils.isEmpty(id)) {
            accountLayout.setError(getString(R.string.plz_enter_id));
            return;

        }

        if (TextUtils.isEmpty(pwd)) {
            pwdLayout.setError("請輸入密碼");
            return;
        }

        accountLayout.setError("");
        pwdLayout.setError("");

        mAuth.createUserWithEmailAndPassword(id, pwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, R.string.register_success, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(SignUpActivity.this, EmailLoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}

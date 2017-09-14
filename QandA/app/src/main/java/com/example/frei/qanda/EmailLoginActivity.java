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

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmailLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String id;
    private String pwd;
    private EditText accountEdit;
    private EditText pwdEdit;
    private TextInputLayout accountLayout;
    private TextInputLayout pwdLayout;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailLoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        init();
    }

    private void init() {
        accountEdit = (EditText) findViewById(R.id.et_id);
        pwdEdit = (EditText) findViewById(R.id.et_pwd);
        accountLayout = (TextInputLayout) findViewById(R.id.til_acc);
        pwdLayout = (TextInputLayout) findViewById(R.id.til_pwd);
        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onClick(View view) {
        id = accountEdit.getText().toString();
        pwd = pwdEdit.getText().toString();


        if (TextUtils.isEmpty(id)) {
            accountLayout.setError(getString(R.string.plz_enter_id));
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            pwdLayout.setError(getString(R.string.plz_enter_pwd));
            return;
        }

        accountLayout.setError("");
        pwdLayout.setError("");

        mAuth.signInWithEmailAndPassword(id, pwd).addOnCompleteListener(EmailLoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Log.d(TAG, "emailSignInResult:" + task.isSuccessful());
                    Toast.makeText(EmailLoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(EmailLoginActivity.this, MainActivity.class);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(EmailLoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}

package com.atrumksar.testingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText etEmail, etPassword;
    private Button btnRegistration, btnSignIn;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_password);

        Toast.makeText(this, "Zashel", Toast.LENGTH_LONG).show();
        etEmail = (EditText)findViewById(R.id.email);
        etPassword = (EditText)findViewById(R.id.password);
        btnRegistration = (Button)findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(this);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                //startActivity(new Intent(LoginPasswordActivity.this, MainActivity.class));
                }
                else {

                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegistration:
               // startActivity(new Intent(this, RegistrartionActivity.class));
                break;
            case R.id.btnSignIn:
                if (!TextUtils.isEmpty(etEmail.getText().toString()) ||
                        !TextUtils.isEmpty(etPassword.getText().toString())) {
                    signing(etEmail.getText().toString(), etPassword.getText().toString());
                    break;
                }
                else {
                    Toast.makeText(this, "Введите email и пароль!",
                            Toast.LENGTH_LONG).show();
                }
        }

    }

    public void signing(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginPasswordActivity.this, "Успешно!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPasswordActivity.this,
                            MainActivity.class);
                    intent.putExtra("succes", true);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginPasswordActivity.this, "Провал",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void registration(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginPasswordActivity.this, "Успешно!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginPasswordActivity.this, "Провал",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
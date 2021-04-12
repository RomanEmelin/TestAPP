package com.atrumksar.testingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvInfo;
    private EditText etInfo;
    private FloatingActionButton fab;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        tvInfo = (TextView)findViewById(R.id.tv_info);
        etInfo = (EditText)findViewById(R.id.user_name);
        fab = (FloatingActionButton)findViewById(R.id.btn_ok);
        fab.setOnClickListener(this);
        if (getIntent().getStringExtra("flag").equals("name")) {
            tvInfo.setText(R.string.edit_user_name);
        }
    }

    @Override
    public void onClick(View view) {
        intent = new Intent(this, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (TextUtils.isEmpty(etInfo.getText())) {
           startActivity(intent);
        }
        else {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                user.updateProfile(new UserProfileChangeRequest.Builder().
                        setDisplayName(etInfo.getText().toString()).build());
                startActivity(intent);
            }

        }
    }
}
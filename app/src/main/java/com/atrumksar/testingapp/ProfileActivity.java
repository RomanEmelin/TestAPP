package com.atrumksar.testingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnEditEmail, btnEditName, btnEditPhone;
    private Button btnSignOut;
    private ImageView ivUser;
    private TextView userName;
    private TextView userEmail;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        btnSignOut = (Button)findViewById(R.id.sign_out);
        btnSignOut.setOnClickListener(this);
        btnEditName = (ImageButton)findViewById(R.id.btn_name_edit);
        btnEditName.setOnClickListener(this);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        userName = (TextView) findViewById(R.id.userName);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userEmail.setText(user.getEmail());
        if (user.getPhotoUrl() != null) {
            ivUser.setImageURI(user.getPhotoUrl());
        }
        if (user.getDisplayName() != null) {
            userName.setText(user.getDisplayName());
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(this, LoginPasswordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.btn_name_edit:
                intent = new Intent(this, EditProfileActivity.class);
                intent.putExtra("flag", "name");
                startActivity(intent);
                break;
        }
    }
}
package com.atrumksar.testingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView tvUser;
    private Button btnBegin, btnAbout, btnMap, btnProfile;
    private Intent intent;
    private Animation animation;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginPasswordActivity.class));
            finish();
            return;
        } else {
            setContentView(R.layout.activity_main);
            btnBegin = (Button) findViewById(R.id.btnBegin);
            btnAbout = (Button) findViewById(R.id.btnAbout);
            btnProfile = (Button) findViewById(R.id.btnProfile);
            btnMap = (Button) findViewById(R.id.btnMap);
            tvUser = (TextView)findViewById(R.id.tvUser);
            tvUser.setText("Welcome " + user.getDisplayName() + " !");
        }
    }

    public void playClick(View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate_l);
        view.startAnimation(animation);
        intent = new Intent(MainActivity.this, PlayerList.class);
        startActivity(intent);
    }

    public void aboutClick(View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate_l);
        view.startAnimation(animation);
        intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void mapClick(View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate_r);
        view.startAnimation(animation);
        intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void onProfile(View view) {
        intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);

    }
}
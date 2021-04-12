package com.atrumksar.testingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

public class RouletteGame extends AppCompatActivity implements View.OnClickListener {

    private TextView playerNames;
    private MyHandler handler;
    private final String LOG_TAG = "State";
    private Button btnNewRound;
    private Random rndm;
    private ArrayList<String> names;

    private static class MyHandler extends Handler {

        WeakReference<RouletteGame> wrActivity;

        public MyHandler(RouletteGame activity) {
            wrActivity = new WeakReference<RouletteGame>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RouletteGame activity = wrActivity.get();
            if(activity != null) {
                activity.sndMessage(msg.what);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_game);
        playerNames = (TextView) findViewById(R.id.playerNames);
        btnNewRound = (Button) findViewById(R.id.btnNewRound);
        btnNewRound.setOnClickListener(this);
        names = getIntent().getStringArrayListExtra("names");
        rndm = new Random();
        handler = new MyHandler(this);
    }

    private void sndMessage(int what) {
        if (what != -1) playerNames.setText(names.get(what));
        else btnNewRound.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        btnNewRound.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 3000; i += 100) {
                    handler.sendEmptyMessage(rndm.nextInt(names.size()));
                    Log.i("Info", "info: " + i);
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            handler.sendEmptyMessage(-1);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        if(handler != null) handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
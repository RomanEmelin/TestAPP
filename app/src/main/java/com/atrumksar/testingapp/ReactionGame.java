package com.atrumksar.testingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ReactionGame extends AppCompatActivity implements View.OnClickListener {

    private Button btnSwitcher;
    private TextView playerNameReaction;
    private ArrayList<String> names;
    private boolean flag = false;
    private MyHandler handler;
    private Thread onBtn;

    private static class MyHandler extends Handler {

        WeakReference<ReactionGame> wrActivity;

        public MyHandler(ReactionGame activity) {
            wrActivity = new WeakReference<ReactionGame>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ReactionGame activity = wrActivity.get();
            if (activity != null)
                activity.sndMessage(msg.what);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        btnSwitcher = (Button)findViewById(R.id.btnSwitcher);
        playerNameReaction = (TextView) findViewById(R.id.playerNameReaction);
        names = getIntent().getStringArrayListExtra("names");
        btnSwitcher.setOnClickListener(this);
        handler = new MyHandler(this);
    }

    private void sndMessage(int what) {
        if (flag) playerNameReaction.setText((names.get(what)));
    }

    @Override
    public synchronized void onClick(View view) {
        if (!flag) {
            btnSwitcher.setText("Stop!");
            flag = true;
            onBtn = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(onBtn.isAlive()) {
                        for (int i = 0; i < names.size(); i++) {
                            Log.i("TAG!!!!", "InfoTAG" + i);
                            handler.sendEmptyMessage(i);
                            try {
                                onBtn.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!flag) break;
                        }
                    }
                }
            });
            onBtn.start();
        }
        else {
            flag = false;
            btnSwitcher.setText("Start!");
            onBtn.interrupt();
        }
    }

    @Override
    protected void onDestroy() {
        if (handler != null) handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
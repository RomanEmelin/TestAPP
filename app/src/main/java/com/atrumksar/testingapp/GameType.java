package com.atrumksar.testingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameType extends AppCompatActivity implements View.OnClickListener {

    //private Button btnRoulette;
    //private Button btnReaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);
        Button btnRoulette = (Button)findViewById(R.id.btnRoulette);
        Button btnReaction = (Button)findViewById(R.id.btnReaction);
        btnRoulette.setOnClickListener(this);
        btnReaction.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, PlayerList.class);
        switch (view.getId()) {
            case R.id.btnRoulette:
                intent.putExtra("choice", "roulette");
                startActivity(intent);
                break;
            case R.id.btnReaction:
                intent.putExtra("choice", "reaction");
                startActivity(intent);
                break;
        }
    }
}
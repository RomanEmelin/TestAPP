package com.atrumksar.testingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlayerList extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddPlayer;
    private EditText editText;
    private Button btnRoulette, btnReaction;
    private Toast toastAdd, toastStart;
    private LinearLayout lnLayout;
    private ArrayList<String> names;
    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        lnLayout = (LinearLayout) findViewById(R.id.playerListActivity);
        btnAddPlayer = (Button)findViewById(R.id.btnAddPlayer);
        editText = (EditText)findViewById(R.id.addPlayerName);
        names = new ArrayList<>();
        btnRoulette = (Button)findViewById(R.id.btnRoulette);
        btnReaction = (Button)findViewById(R.id.btnReaction);
        toastAdd = Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT);
        toastAdd.setGravity(Gravity.CENTER, 0 ,0);
        toastStart = Toast.makeText(this, R.string.minimum_players,
                Toast.LENGTH_LONG);
        btnAddPlayer.setOnClickListener(this);
        btnRoulette.setOnClickListener(this);
        btnReaction.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddPlayer:
                if (editText.getText().toString().equals("")) {
                    toastAdd.show();
                    break;
                } else {
                    final View item = getLayoutInflater().inflate(R.layout.player_list, lnLayout,
                            false);
                    TextView playerName = (TextView) item.findViewById(R.id.playerList);
                    names.add(editText.getText().toString());
                    playerName.setText(editText.getText().toString());
                    Button rmv = (Button) item.findViewById(R.id.btnRmv);
                    final String name = playerName.getText().toString();
                    rmv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            names.remove(name);
                            lnLayout.removeView(item);
                        }
                    });
                    lnLayout.addView(item);
                    editText.setText("");
                    break;
                }
            case R.id.btnRoulette:
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate_l);
                view.startAnimation(animation);
                if (names.size() < 2) {
                    toastStart.show();
                    break;
                } else {
                    Intent intent = new Intent(this, RouletteGame.class);
                    intent.putExtra("names", names);
                    startActivity(intent);
                    break;
                }
            case R.id.btnReaction:
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate_r);
                view.startAnimation(animation);
                if (names.size() < 2) {
                    toastStart.show();
                    break;
                }
                else {
                    Intent intent = new Intent(this, ReactionGame.class);
                    intent.putExtra("names", names);
                    startActivity(intent);
                    break;
                }
        }
    }
}

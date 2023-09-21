package com.example.yahtzee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import pl.droidsonroids.gif.GifImageButton;

public class MainActivity4 extends AppCompatActivity {

    private SharedPreferences play;
    GifImageButton startgame;
    Button rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        rule = (Button)findViewById(R.id.rule);

        play = getSharedPreferences("POINT", MODE_PRIVATE);
        SharedPreferences.Editor editor=play.edit();

        startgame = (GifImageButton)findViewById(R.id.startgame);

        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity4.this, MainActivity.class);
                editor.clear().commit();
                startActivity(intent);
            }
        });
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog =
                        new AlertDialog.Builder(MainActivity4.this);
                alertDialog.setTitle("規則");
                alertDialog.setMessage("遊戲使用投擲五個骰子判斷得分，第一次投擲後可以兩次選擇重新投擲部分的骰子。最後由骰子數字的組合來判斷得分。");
                alertDialog.setNegativeButton("確定",((dialog, which) -> {}));
                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        });
    }
}
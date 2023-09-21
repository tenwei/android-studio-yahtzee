package com.example.yahtzee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    Button a;
    private SharedPreferences play;
    int p1, p2;
    ImageView win, pace;
    TextView p1t, p2t;
    int winner[] = {R.drawable.player1, R.drawable.player2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        play=getSharedPreferences("POINT",MODE_MULTI_PROCESS);
        p1 = play.getInt("p1total",0);
        p2 = play.getInt("p2total",0);
        p1t=(TextView)findViewById(R.id.P1);
        p2t=(TextView)findViewById(R.id.P2);
        p1t.setText(Integer.toString(p1));
        p2t.setText(Integer.toString(p2));

        a = (Button)findViewById(R.id.button2);
        win = (ImageView)findViewById(R.id.winner);
        pace = (ImageView)findViewById(R.id.pace);

        pace.setVisibility(View.INVISIBLE);

        if(p1 > p2){
            win.setImageResource(winner[0]);
        }
        else if(p1 < p2){
            win.setImageResource(winner[1]);
        }
        else if(p1 == p2){
            win.setVisibility(View.INVISIBLE);
            pace.setVisibility(View.VISIBLE);
        }



        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}
package com.example.yahtzee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    int []dice = {0, R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    int totalRolls = 1, A, B, C, D, E, dicesum =0;
    boolean h1 =false;
    boolean h2 =false;
    boolean h3 =false;
    boolean h4 =false;
    boolean h5 =false;
    ImageView[] Dice, DICE;
    private SharedPreferences play;
    TextView player1sc, player2sc;
    int p1 = 0, p2 = 0;
    Button roll;
    ImageButton scoring,di1,di2,di3,di4,di5,D1,D2,D3,D4,D5;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("Data", MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();

        Dice =new ImageView[5];
        DICE =new ImageView[5];

        play=getSharedPreferences("POINT",MODE_MULTI_PROCESS);
        p1 = play.getInt("p1total",0);
        p2 = play.getInt("p2total",0);
        player1sc=(TextView)findViewById(R.id.player1sc);
        player2sc=(TextView)findViewById(R.id.player2sc);
        player1sc.setText(Integer.toString(p1));
        player2sc.setText(Integer.toString(p2));

        Dice[0]=(ImageButton)findViewById(R.id.d1);
        Dice[1]=(ImageButton)findViewById(R.id.d2);
        Dice[2]=(ImageButton)findViewById(R.id.d3);
        Dice[3]=(ImageButton)findViewById(R.id.d4);
        Dice[4]=(ImageButton)findViewById(R.id.d5);
        DICE[0]=(ImageButton)findViewById(R.id.D1);
        DICE[1]=(ImageButton)findViewById(R.id.D2);
        DICE[2]=(ImageButton)findViewById(R.id.D3);
        DICE[3]=(ImageButton)findViewById(R.id.D4);
        DICE[4]=(ImageButton)findViewById(R.id.D5);

        roll=(Button)findViewById(R.id.roll);
        scoring=(ImageButton)findViewById(R.id.sc);
        di1=(ImageButton)findViewById(R.id.d1);
        di2=(ImageButton)findViewById(R.id.d2);
        di3=(ImageButton)findViewById(R.id.d3);
        di4=(ImageButton)findViewById(R.id.d4);
        di5=(ImageButton)findViewById(R.id.d5);
        D1=(ImageButton)findViewById(R.id.D1);
        D2=(ImageButton)findViewById(R.id.D2);
        D3=(ImageButton)findViewById(R.id.D3);
        D4=(ImageButton)findViewById(R.id.D4);
        D5=(ImageButton)findViewById(R.id.D5);

        di1.setVisibility(View.INVISIBLE);
        di2.setVisibility(View.INVISIBLE);
        di3.setVisibility(View.INVISIBLE);
        di4.setVisibility(View.INVISIBLE);
        di5.setVisibility(View.INVISIBLE);
        D1.setVisibility(View.INVISIBLE);
        D2.setVisibility(View.INVISIBLE);
        D3.setVisibility(View.INVISIBLE);
        D4.setVisibility(View.INVISIBLE);
        D5.setVisibility(View.INVISIBLE);
        scoring.setVisibility(View.INVISIBLE);

        if(dicesum == 5)
            scoring.setVisibility(View.VISIBLE);
        else
            scoring.setVisibility(View.INVISIBLE);

        roll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(totalRolls < 3){
                    if(!h1) {
                        di1.setVisibility(View.VISIBLE);
                        A = new Random().nextInt(6)+1;
                        Dice[0].setImageResource(dice[A]);
                    }


                    if(!h2) {
                        di2.setVisibility(View.VISIBLE);
                        B = new Random().nextInt(6)+1;
                        Dice[1].setImageResource(dice[B]);
                    }


                    if(!h3) {
                        di3.setVisibility(View.VISIBLE);
                        C = new Random().nextInt(6)+1;
                        Dice[2].setImageResource(dice[C]);
                    }


                    if(!h4) {
                        di4.setVisibility(View.VISIBLE);
                        D = new Random().nextInt(6)+1;
                        Dice[3].setImageResource(dice[D]);
                    }


                    if(!h5) {
                        di5.setVisibility(View.VISIBLE);
                        E = new Random().nextInt(6)+1;
                        Dice[4].setImageResource(dice[E]);
                    }

                    totalRolls++;
                }
                else{
                    roll.setVisibility(View.INVISIBLE);
                }
            }
        });

        scoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(MainActivity.this  , MainActivity2.class);
                bundle.putInt("DA", A);
                bundle.putInt("DB", B);
                bundle.putInt("DC", C);
                bundle.putInt("DD", D);
                bundle.putInt("DE", E);
                intent.putExtras(bundle);
                startActivity(intent);
                //MainActivity.this.finish();
            }
        });

        di1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h1 = !h1;
                di1.setVisibility(View.INVISIBLE);
                DICE[0].setImageResource(dice[A]);
                DICE[0].setVisibility(View.VISIBLE);
                dicesum++;
                sct();
            }
        });

        di2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h2 = !h2;
                di2.setVisibility(View.INVISIBLE);
                DICE[1].setImageResource(dice[B]);
                DICE[1].setVisibility(View.VISIBLE);
                dicesum++;
                sct();
            }
        });

        di3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h3 = !h3;
                di3.setVisibility(View.INVISIBLE);
                DICE[2].setImageResource(dice[C]);
                DICE[2].setVisibility(View.VISIBLE);
                dicesum++;
                sct();
            }
        });

        di4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h4 = !h4;
                di4.setVisibility(View.INVISIBLE);
                DICE[3].setImageResource(dice[D]);
                DICE[3].setVisibility(View.VISIBLE);
                dicesum++;
                sct();
            }
        });

        di5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h5 = !h5;
                di5.setVisibility(View.INVISIBLE);
                DICE[4].setImageResource(dice[E]);
                DICE[4].setVisibility(View.VISIBLE);
                dicesum++;
                sct();
            }
        });

        D1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h1 = !h1;
                D1.setVisibility(View.INVISIBLE);
                Dice[0].setImageResource(dice[A]);
                di1.setVisibility(View.VISIBLE);
                dicesum--;
                sct();
            }
        });

        D2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h2 = !h2;
                D2.setVisibility(View.INVISIBLE);
                Dice[1].setImageResource(dice[B]);
                di2.setVisibility(View.VISIBLE);
                dicesum--;
                sct();
            }
        });

        D3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h3 = !h3;
                D3.setVisibility(View.INVISIBLE);
                Dice[2].setImageResource(dice[C]);
                di3.setVisibility(View.VISIBLE);
                dicesum--;
                sct();
            }
        });

        D4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h4 = !h4;
                D4.setVisibility(View.INVISIBLE);
                Dice[3].setImageResource(dice[D]);
                di4.setVisibility(View.VISIBLE);
                dicesum--;
                sct();
            }
        });

        D5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h5 = !h5;
                D5.setVisibility(View.INVISIBLE);
                Dice[4].setImageResource(dice[E]);
                di5.setVisibility(View.VISIBLE);
                dicesum--;
                sct();
            }
        });

    }
    void sct(){
        if(dicesum == 5)
            scoring.setVisibility(View.VISIBLE);
        else
            scoring.setVisibility(View.INVISIBLE);
    }
}
package com.example.yahtzee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    int []de = {0, R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    int play1[][] = new int[2][11];
    int play2[][] = new int[2][11];
    ImageView DC1, DC2, DC3, DC4, DC5;
    int A,B,C,D,E;
    private SharedPreferences play;
    Button back, onebutton, twobutton, threebutton, fourbutton, fivebutton, sixbutton, str, fh, all, yat;
    TextView one, two, three, four, five, six, strview, fhview, yatview, allview;
    TextView one2, two2, three2, four2, five2, six2, strview2, fhview2, yatview2, allview2;
    TextView p1total, p2total;
    int alltotal, strn, fhn, yatn;
    String s, alls;
    String s1;
    Bundle bundle;
    int player = 0, p1, p2 = 0;
    int totalplay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bundle = this.getIntent().getExtras();
        A = bundle.getInt("DA");
        B = bundle.getInt("DB");
        C = bundle.getInt("DC");
        D = bundle.getInt("DD");
        E = bundle.getInt("DE");

        play = getSharedPreferences("POINT", MODE_PRIVATE);
        SharedPreferences.Editor editor=play.edit();

        back = (Button)findViewById(R.id.button);
        onebutton = (Button)findViewById(R.id.onebutton);
        twobutton = (Button)findViewById(R.id.twobutton);
        threebutton = (Button)findViewById(R.id.threebutton);
        fourbutton = (Button)findViewById(R.id.fourbutton);
        fivebutton = (Button)findViewById(R.id.fivebutton);
        sixbutton = (Button)findViewById(R.id.sixbutton);
        str = (Button)findViewById(R.id.str);
        fh = (Button)findViewById(R.id.fh);
        yat = (Button)findViewById(R.id.yat);
        all = (Button)findViewById(R.id.all);

        one = (TextView)findViewById(R.id.oneview);
        two = (TextView)findViewById(R.id.twoview);
        three = (TextView)findViewById(R.id.threeview);
        four = (TextView)findViewById(R.id.fourview);
        five = (TextView)findViewById(R.id.fiveview);
        six = (TextView)findViewById(R.id.sixview);
        strview = (TextView)findViewById(R.id.strview);
        fhview = (TextView)findViewById(R.id.fhview);
        yatview = (TextView)findViewById(R.id.yatview);
        allview = (TextView)findViewById(R.id.allview);
        one2 = (TextView)findViewById(R.id.oneview2);
        two2 = (TextView)findViewById(R.id.twoview2);
        three2 = (TextView)findViewById(R.id.threeview2);
        four2 = (TextView)findViewById(R.id.fourview2);
        five2 = (TextView)findViewById(R.id.fiveview2);
        six2 = (TextView)findViewById(R.id.sixview2);
        strview2 = (TextView)findViewById(R.id.strview2);
        fhview2 = (TextView)findViewById(R.id.fhview2);
        yatview2 = (TextView)findViewById(R.id.yatview2);
        allview2 = (TextView)findViewById(R.id.allview2);
        p1total = (TextView)findViewById(R.id.p1total);
        p2total = (TextView)findViewById(R.id.p2total);

        DC1 = (ImageView)findViewById(R.id.dc1);
        DC2 = (ImageView)findViewById(R.id.dc2);
        DC3 = (ImageView)findViewById(R.id.dc3);
        DC4 = (ImageView)findViewById(R.id.dc4);
        DC5 = (ImageView)findViewById(R.id.dc5);

        DC1.setImageResource(de[A]);
        DC2.setImageResource(de[B]);
        DC3.setImageResource(de[C]);
        DC4.setImageResource(de[D]);
        DC5.setImageResource(de[E]);

        back.setVisibility(View.INVISIBLE);
        onebutton.setVisibility(View.VISIBLE);
        twobutton.setVisibility(View.VISIBLE);
        threebutton.setVisibility(View.VISIBLE);
        fourbutton.setVisibility(View.VISIBLE);
        fivebutton.setVisibility(View.VISIBLE);
        sixbutton.setVisibility(View.VISIBLE);
        str.setVisibility(View.VISIBLE);
        fh.setVisibility(View.VISIBLE);
        yat.setVisibility(View.VISIBLE);
        all.setVisibility(View.VISIBLE);

        play=getSharedPreferences("POINT",MODE_MULTI_PROCESS);
        player = play.getInt("tt",0);
        p1 = play.getInt("p1total",0);
        p2 = play.getInt("p2total",0);
        totalplay = play.getInt("totalplay",0);

        p1total.setText(Integer.toString(p1));
        p2total.setText(Integer.toString(p2));

        //顯示分數
        if(player == 0) {
            calculateNumber(1);
            one.setText(s);
            calculateNumber(2);
            two.setText(s);
            calculateNumber(3);
            three.setText(s);
            calculateNumber(4);
            four.setText(s);
            calculateNumber(5);
            five.setText(s);
            calculateNumber(6);
            six.setText(s);
            straight();
            fullhouse();
            yahtzee();
            ALLt();
            allview.setText(alls);
        }
        else{
            calculateNumber(1);
            one2.setText(s);
            calculateNumber(2);
            two2.setText(s);
            calculateNumber(3);
            three2.setText(s);
            calculateNumber(4);
            four2.setText(s);
            calculateNumber(5);
            five2.setText(s);
            calculateNumber(6);
            six2.setText(s);
            straight();
            fullhouse();
            yahtzee();
            ALLt();
            allview2.setText(alls);
        }

        inview();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        onebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    calculateNumber(1);
                    editor.putInt("p10", Integer.parseInt(s)).commit();
                    play1[1][0] = 1;
                    editor.putInt("p10t", play1[1][0]).commit();
                    onebutton.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 = p1 + Integer.parseInt(s);
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    calculateNumber(1);
                    editor.putInt("p20", Integer.parseInt(s)).commit();
                    play2[1][0] = 1;
                    editor.putInt("p20t", play2[1][0]).commit();
                    onebutton.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += Integer.parseInt(s);
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        twobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    calculateNumber(2);
                    editor.putInt("p11", Integer.parseInt(s)).commit();
                    play1[1][1] = 1;
                    editor.putInt("p11t", play1[1][1]).commit();
                    twobutton.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 = p1 + Integer.parseInt(s);
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    calculateNumber(2);
                    editor.putInt("p21", Integer.parseInt(s)).commit();
                    play2[1][1] = 1;
                    editor.putInt("p21t", play2[1][1]).commit();
                    twobutton.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += Integer.parseInt(s);
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        threebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    calculateNumber(3);
                    editor.putInt("p12", Integer.parseInt(s)).commit();
                    play1[1][2] = 1;
                    editor.putInt("p12t", play1[1][2]).commit();
                    threebutton.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 = p1 + Integer.parseInt(s);
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else {
                    calculateNumber(3);
                    editor.putInt("p22", Integer.parseInt(s)).commit();
                    play2[1][2] = 1;
                    editor.putInt("p22t", play2[1][2]).commit();
                    threebutton.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += Integer.parseInt(s);
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        fourbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    calculateNumber(4);
                    editor.putInt("p13", Integer.parseInt(s)).commit();
                    play1[1][3] = 1;
                    editor.putInt("p13t", play1[1][3]).commit();
                    fourbutton.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 = p1 + Integer.parseInt(s);
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    calculateNumber(4);
                    editor.putInt("p23", Integer.parseInt(s)).commit();
                    play2[1][3] = 1;
                    editor.putInt("p23t", play2[1][3]).commit();
                    fourbutton.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += Integer.parseInt(s);
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        fivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    calculateNumber(5);
                    editor.putInt("p14", Integer.parseInt(s)).commit();
                    play1[1][4] = 1;
                    editor.putInt("p14t", play1[1][4]).commit();
                    fivebutton.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 = p1 + Integer.parseInt(s);
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    calculateNumber(5);
                    editor.putInt("p24", Integer.parseInt(s)).commit();
                    play2[1][4] = 1;
                    editor.putInt("p24t", play2[1][4]).commit();
                    fivebutton.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += Integer.parseInt(s);
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        sixbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    calculateNumber(6);
                    editor.putInt("p15", Integer.parseInt(s)).commit();
                    play1[1][5] = 1;
                    editor.putInt("p15t", play1[1][5]).commit();
                    sixbutton.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 = p1 + Integer.parseInt(s);
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    calculateNumber(6);
                    editor.putInt("p25", Integer.parseInt(s)).commit();
                    play2[1][5] = 1;
                    editor.putInt("p25t", play2[1][5]).commit();
                    sixbutton.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += Integer.parseInt(s);
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    editor.putInt("p16", strn).commit();
                    play1[1][6] = 1;
                    editor.putInt("p16t", play1[1][6]).commit();
                    str.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 += strn;
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    editor.putInt("p26", strn).commit();
                    play2[1][6] = 1;
                    editor.putInt("p26t", play2[1][6]).commit();
                    str.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += strn;
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    editor.putInt("p17", fhn).commit();
                    play1[1][7] = 1;
                    editor.putInt("p17t", play1[1][7]).commit();
                    fh.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 += fhn;
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    editor.putInt("p27", fhn).commit();
                    play2[1][7] = 1;
                    editor.putInt("p27t", play2[1][7]).commit();
                    fh.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += fhn;
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        yat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    editor.putInt("p18", yatn).commit();
                    play1[1][8] = 1;
                    editor.putInt("p18t", play1[1][8]).commit();
                    yat.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 += yatn;
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    editor.putInt("p28", yatn).commit();
                    play2[1][8] = 1;
                    editor.putInt("p28t", play2[1][8]).commit();
                    yat.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += yatn;
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player == 0) {
                    editor.putInt("p19", alltotal).commit();
                    play1[1][9] = 1;
                    editor.putInt("p19t", play1[1][9]).commit();
                    all.setEnabled(false);
                    editor.putInt("tt", 1).commit();
                    p1 += alltotal;
                    editor.putInt("p1total", p1).commit();
                    p1total.setText(Integer.toString(p1));
                }
                else{
                    editor.putInt("p29", alltotal).commit();
                    play2[1][9] = 1;
                    editor.putInt("p29t", play2[1][9]).commit();
                    all.setEnabled(false);
                    editor.putInt("tt", 0).commit();
                    p2 += alltotal;
                    editor.putInt("p2total", p2).commit();
                    p2total.setText(Integer.toString(p2));
                }
                pda();
            }
        });

    }

    void inview(){
        play1[1][0] = play.getInt("p10t",0);
        play1[1][1] = play.getInt("p11t",0);
        play1[1][2] = play.getInt("p12t",0);
        play1[1][3] = play.getInt("p13t",0);
        play1[1][4] = play.getInt("p14t",0);
        play1[1][5] = play.getInt("p15t",0);
        play1[1][6] = play.getInt("p16t",0);
        play1[1][7] = play.getInt("p17t",0);
        play1[1][8] = play.getInt("p18t",0);
        play1[1][9] = play.getInt("p19t",0);

        play2[1][0] = play.getInt("p20t",0);
        play2[1][1] = play.getInt("p21t",0);
        play2[1][2] = play.getInt("p22t",0);
        play2[1][3] = play.getInt("p23t",0);
        play2[1][4] = play.getInt("p24t",0);
        play2[1][5] = play.getInt("p25t",0);
        play2[1][6] = play.getInt("p26t",0);
        play2[1][7] = play.getInt("p27t",0);
        play2[1][8] = play.getInt("p28t",0);
        play2[1][9] = play.getInt("p29t",0);
        //play1
        if(play1[1][0] != 0){
            play1[0][0] = play.getInt("p10",0);
            s1 = Integer.toString(play1[0][0]);
            one.setText(s1);
            if(player == 0)
                onebutton.setEnabled(false);
        }
        if(play1[1][1] != 0){
            play1[0][1] = play.getInt("p11",0);
            s1 = Integer.toString(play1[0][1]);
            two.setText(s1);
            if(player == 0)
                twobutton.setEnabled(false);
        }
        if(play1[1][2] != 0){
            play1[0][2] = play.getInt("p12",0);
            s1 = Integer.toString(play1[0][2]);
            three.setText(s1);
            if(player == 0)
                threebutton.setEnabled(false);
        }
        if(play1[1][3] != 0){
            play1[0][3] = play.getInt("p13",0);
            s1 = Integer.toString(play1[0][3]);
            four.setText(s1);
            if(player == 0)
                fourbutton.setEnabled(false);
        }
        if(play1[1][4] != 0){
            play1[0][4] = play.getInt("p14",0);
            s1 = Integer.toString(play1[0][4]);
            five.setText(s1);
            if(player == 0)
                fivebutton.setEnabled(false);
        }
        if(play1[1][5] != 0){
            play1[0][5] = play.getInt("p15",0);
            s1 = Integer.toString(play1[0][5]);
            six.setText(s1);
            if(player == 0)
                sixbutton.setEnabled(false);
        }
        if(play1[1][6] != 0){
            play1[0][6] = play.getInt("p16",0);
            s1 = Integer.toString(play1[0][6]);
            strview.setText(s1);
            if(player == 0)
                str.setEnabled(false);
        }
        if(play1[1][7] != 0){
            play1[0][7] = play.getInt("p17",0);
            s1 = Integer.toString(play1[0][7]);
            fhview.setText(s1);
            if(player == 0)
                fh.setEnabled(false);
        }
        if(play1[1][8] != 0){
            play1[0][8] = play.getInt("p18",0);
            s1 = Integer.toString(play1[0][8]);
            yatview.setText(s1);
            if(player == 0)
                yat.setEnabled(false);
        }
        if(play1[1][9] != 0){
            play1[0][9] = play.getInt("p19",0);
            s1 = Integer.toString(play1[0][9]);
            allview.setText(s1);
            if(player == 0)
                all.setEnabled(false);
        }
        //play2
        if(play2[1][0] != 0){
            play2[0][0] = play.getInt("p20",0);
            s1 = Integer.toString(play2[0][0]);
            one2.setText(s1);
            if(player == 1)
                onebutton.setEnabled(false);
        }
        if(play2[1][1] != 0){
            play2[0][1] = play.getInt("p21",0);
            s1 = Integer.toString(play2[0][1]);
            two2.setText(s1);
            if(player == 1)
                twobutton.setEnabled(false);
        }
        if(play2[1][2] != 0){
            play2[0][2] = play.getInt("p22",0);
            s1 = Integer.toString(play2[0][2]);
            three2.setText(s1);
            if(player == 1)
                threebutton.setEnabled(false);
        }
        if(play2[1][3] != 0){
            play2[0][3] = play.getInt("p23",0);
            s1 = Integer.toString(play2[0][3]);
            four2.setText(s1);
            if(player == 1)
                fourbutton.setEnabled(false);
        }
        if(play2[1][4] != 0){
            play2[0][4] = play.getInt("p24",0);
            s1 = Integer.toString(play2[0][4]);
            five2.setText(s1);
            if(player == 1)
                fivebutton.setEnabled(false);
        }
        if(play2[1][5] != 0){
            play2[0][5] = play.getInt("p25",0);
            s1 = Integer.toString(play2[0][5]);
            six2.setText(s1);
            if(player == 1)
                sixbutton.setEnabled(false);
        }
        if(play2[1][6] != 0){
            play2[0][6] = play.getInt("p26",0);
            s1 = Integer.toString(play2[0][6]);
            strview2.setText(s1);
            if(player == 1)
                str.setEnabled(false);
        }
        if(play2[1][7] != 0){
            play2[0][7] = play.getInt("p27",0);
            s1 = Integer.toString(play2[0][7]);
            fhview2.setText(s1);
            if(player == 1)
                fh.setEnabled(false);
        }
        if(play2[1][8] != 0){
            play2[0][8] = play.getInt("p28",0);
            s1 = Integer.toString(play2[0][8]);
            yatview2.setText(s1);
            if(player == 1)
                yat.setEnabled(false);
        }
        if(play2[1][9] != 0){
            play2[0][9] = play.getInt("p29",0);
            s1 = Integer.toString(play2[0][9]);
            allview2.setText(s1);
            if(player == 1)
                all.setEnabled(false);
        }
    }

    void pda(){
        play=getSharedPreferences("POINT",MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor=play.edit();
        totalplay++;
        editor.putInt("totalplay", totalplay).commit();

        play1[1][0] = play.getInt("p10t",0);
        play1[1][1] = play.getInt("p11t",0);
        play1[1][2] = play.getInt("p12t",0);
        play1[1][3] = play.getInt("p13t",0);
        play1[1][4] = play.getInt("p14t",0);
        play1[1][5] = play.getInt("p15t",0);
        play1[1][6] = play.getInt("p16t",0);
        play1[1][7] = play.getInt("p17t",0);
        play1[1][8] = play.getInt("p18t",0);
        play1[1][9] = play.getInt("p19t",0);

        play2[1][0] = play.getInt("p20t",0);
        play2[1][1] = play.getInt("p21t",0);
        play2[1][2] = play.getInt("p22t",0);
        play2[1][3] = play.getInt("p23t",0);
        play2[1][4] = play.getInt("p24t",0);
        play2[1][5] = play.getInt("p25t",0);
        play2[1][6] = play.getInt("p26t",0);
        play2[1][7] = play.getInt("p27t",0);
        play2[1][8] = play.getInt("p28t",0);
        play2[1][9] = play.getInt("p29t",0);
        if(play1[1][0] == 0){
            one.setText("");
        }
        if(play1[1][1] == 0){
            two.setText("");
        }
        if(play1[1][2] == 0){
            three.setText("");
        }
        if(play1[1][3] == 0){
            four.setText("");
        }
        if(play1[1][4] == 0){
            five.setText("");
        }
        if(play1[1][5] == 0){
            six.setText("");
        }
        if(play1[1][6] == 0){
            strview.setText("");
        }
        if(play1[1][7] == 0){
            fhview.setText("");
        }
        if(play1[1][8] == 0){
            yatview.setText("");
        }
        if(play1[1][9] == 0){
            allview.setText("");
        }

        if(play2[1][0] == 0){
            one2.setText("");
        }
        if(play2[1][1] == 0){
            two2.setText("");
        }
        if(play2[1][2] == 0){
            three2.setText("");
        }
        if(play2[1][3] == 0){
            four2.setText("");
        }
        if(play2[1][4] == 0){
            five2.setText("");
        }
        if(play2[1][5] == 0){
            six2.setText("");
        }
        if(play2[1][6] == 0){
            strview2.setText("");
        }
        if(play2[1][7] == 0){
            fhview2.setText("");
        }
        if(play2[1][8] == 0){
            yatview2.setText("");
        }
        if(play2[1][9] == 0){
            allview2.setText("");
        }
        if(totalplay == 20){
            Intent intent = new Intent();
            intent.setClass(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        }
        back.setVisibility(View.VISIBLE);
        onebutton.setVisibility(View.INVISIBLE);
        twobutton.setVisibility(View.INVISIBLE);
        threebutton.setVisibility(View.INVISIBLE);
        fourbutton.setVisibility(View.INVISIBLE);
        fivebutton.setVisibility(View.INVISIBLE);
        sixbutton.setVisibility(View.INVISIBLE);
        str.setVisibility(View.INVISIBLE);
        fh.setVisibility(View.INVISIBLE);
        yat.setVisibility(View.INVISIBLE);
        all.setVisibility(View.INVISIBLE);
    }

    void calculateNumber(int num){
        s = "";
        int strNum = num;
        int total = 0;
        if (A == strNum)
            total += num;
        if (B == strNum)
            total += num;
        if (C == strNum)
            total += num;
        if (D == strNum)
            total += num;
        if (E == strNum)
            total += num;
        s = Integer.toString(total);
    }

    void straight(){
        int []strdice = {A, B, C, D, E};
        Arrays.sort(strdice);

        int length = 1;

        for (int i = 0; i <= 3; i++) {
            if (strdice[i] + 1 == strdice[i + 1]) {
                length++;
            }
            else if (strdice[i] != strdice[i + 1]) {
                length = 1;
            }
            if (length == 5) {
                if(player == 0)
                    strview.setText("25");
                else
                    strview2.setText("25");
                strn = 25;
                break;
            }
            if(i == 3){
                if(player == 0)
                    strview.setText("0");
                else
                    strview2.setText("0");
                strn = 0;
            }
        }
    }

    void fullhouse(){
        int x = A;
        int y = A;
        if(B != A){
            y = B;
        } else if(C != A){
            y = C;
        } else if(D != A){
            y = D;
        }
        if(x != y) {
            int f1 = 0;
            int f2 = 0;
            if (A == x)
                f1 += 1;
            else if (A == y)
                f2 += 1;
            if (B == x)
                f1 += 1;
            else if (B == y)
                f2 += 1;
            if (C == x)
                f1 += 1;
            else if (C == y)
                f2 += 1;
            if (D == x)
                f1 += 1;
            else if (D == y)
                f2 += 1;
            if (E == x)
                f1 += 1;
            else if (E == y)
                f2 += 1;
            if ((f1 == 3 && f2 == 2) || (f1 == 2 && f2 == 3)) {
                if (player == 0)
                    fhview.setText("25");
                else
                    fhview2.setText("25");
                fhn = 25;
            }
            else {
                if (player == 0)
                    fhview.setText("0");
                else
                    fhview2.setText("0");
                fhn = 0;
            }
        }
        else {
            if (player == 0)
                fhview.setText("0");
            else
                fhview2.setText("0");
            fhn = 0;
        }
    }

    void yahtzee(){
        int []yatdice = {A, B, C, D, E};
        int die1 = yatdice[0];
        boolean fail = false;
        for(int i = 1; i <= 4; i++){
            if(yatdice[i] != die1)
                fail = true;
        }
        if(fail) {
            if (player == 0)
                yatview.setText("0");
            else
                yatview2.setText("0");
            yatn = 0;
        }
        else {
            if (player == 0)
                yatview.setText("50");
            else
                yatview2.setText("50");
            yatn = 50;
        }
    }

    void ALLt(){
        alltotal = 0;
        alltotal += A;
        alltotal += B;
        alltotal += C;
        alltotal += D;
        alltotal += E;
        alls = Integer.toString(alltotal);
    }

}
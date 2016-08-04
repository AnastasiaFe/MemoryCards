package com.example.anastasia.memorycards;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
public class RecordsMain extends Activity {

    ImageButton first,second,third,fourth,fifth,sixth,seventh,eight,nine;
TextView levelTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records);
levelTitle=(TextView)findViewById(R.id.levelTitle);
        levelTitle.setTypeface(Typeface.createFromAsset(getAssets(),"3.ttf"));
        levelTitle.setTextSize(38);
        first=(ImageButton)findViewById(R.id.firstLevel);
        second=(ImageButton)findViewById(R.id.secondLevel);
        third=(ImageButton)findViewById(R.id.third);
        fourth=(ImageButton)findViewById(R.id.forth);
        fifth=(ImageButton)findViewById(R.id.fifth);
        sixth=(ImageButton)findViewById(R.id.sixth);
        seventh=(ImageButton)findViewById(R.id.seventh);
        eight=(ImageButton)findViewById(R.id.eight);
        nine=(ImageButton)findViewById(R.id.nines);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="1";
                startActivity(new Intent(RecordsMain.this,Table.class));


            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="2";
                startActivity(new Intent(RecordsMain.this,Table.class));
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="3";
                startActivity(new Intent(RecordsMain.this,Table.class));
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="4";
                startActivity(new Intent(RecordsMain.this,Table.class));
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="5";
                startActivity(new Intent(RecordsMain.this,Table.class));
            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="6";
                startActivity(new Intent(RecordsMain.this,Table.class));
            }
        });
        seventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="7";
                startActivity(new Intent(RecordsMain.this,Table.class));
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="8"; startActivity(new Intent(RecordsMain.this,Table.class));

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table.level="9"; startActivity(new Intent(RecordsMain.this,Table.class));
            }
        });
    }
}

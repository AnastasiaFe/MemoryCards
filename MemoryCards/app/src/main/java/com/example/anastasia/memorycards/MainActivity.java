package com.example.anastasia.memorycards;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Anastasia on 09.07.2016.
 */
public class MainActivity extends Activity {
    RelativeLayout main;
    TextView title;
    Button start, records,settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startService(new Intent(this, MusicService.class));

title=(TextView)findViewById(R.id.title);
        start=(Button)findViewById(R.id.startButton);
        records=(Button)findViewById(R.id.recordsButton);
        settings=(Button)findViewById(R.id.settingsButton);
start.setTypeface(Typeface.createFromAsset(getAssets(),"3.ttf"));
        start.setTextSize(45);
        records.setTextSize(45);
        settings.setTextSize(45);
        records.setTypeface(Typeface.createFromAsset(getAssets(), "3.ttf"));
        settings.setTypeface(Typeface.createFromAsset(getAssets(),"3.ttf"));
title.setTypeface(Typeface.createFromAsset(getAssets(),"7.ttf"));
        title.setTextSize(35);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        //Integer BackgroundColor = Color.parseColor(settings.getString("BackgroundColor", "Primary"));
        main = (RelativeLayout) findViewById(R.id.main);
       // main.setBackgroundColor(BackgroundColor);




    }




    public void onBackPressed() {
        stopService(new Intent(this, MusicService.class));
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startOnClick(View view) {

        Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
        startActivity(intent);
    }

    public void settingsOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
    }

    public void recordsOnClick(View view) {
        startActivity(new Intent(MainActivity.this,RecordsMain.class));
    }
}

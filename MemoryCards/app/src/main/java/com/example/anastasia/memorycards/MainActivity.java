package com.example.anastasia.memorycards;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
public class MainActivity extends Activity {
    /**text:Memory Cards*/
    TextView title;
    Button start, records;
    /**isMusicOn button*/
    ImageButton ss;
    /**if isMusicOn button clicked*/
 static   boolean isButtonClicked=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


title=(TextView)findViewById(R.id.title);
        start=(Button)findViewById(R.id.startButton);
        records=(Button)findViewById(R.id.recordsButton);
        ss=(ImageButton)findViewById(R.id.ss);
/**set the font to the buttons and application name*/
start.setTypeface(Typeface.createFromAsset(getAssets(), "3.ttf"));
        start.setTextSize(50);
        records.setTextSize(50);
        records.setTypeface(Typeface.createFromAsset(getAssets(), "3.ttf"));
title.setTypeface(Typeface.createFromAsset(getAssets(), "7.ttf"));
        title.setTextSize(45);

        /**if the MusicOn button clicked - the music plays, if not - no.Picture changes*/
if(isButtonClicked)
{
    play();
    ss.setImageResource(R.drawable.mus2);

}
        else {
    ss.setImageResource(R.drawable.mus22);
        }


        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the isButtonClicked value on the opposite one
                isButtonClicked=!isButtonClicked;
                initPlaySound();
            }
        });

    }

public void initPlaySound()
{
    if(isButtonClicked)
    {
ss.setImageResource(R.drawable.mus2);
        play();
    }
    else {
ss.setImageResource(R.drawable.mus22);
        stop();
    }
}
/**the music on*/
public void play()
{
    startService(new Intent(MainActivity.this, MusicService.class));
}
    /**the music off*/
    public void stop()
    {
        stopService(new Intent(MainActivity.this, MusicService.class));
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



    public void recordsOnClick(View view) {
        startActivity(new Intent(MainActivity.this,RecordsMain.class));
    }
}

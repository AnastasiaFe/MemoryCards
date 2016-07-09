package com.example.anastasia.memorycards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Anastasia on 29.06.2016.
 */
public class LevelsActivity extends Activity {

    ImageButton beginner;
    ImageButton easy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
beginner=(ImageButton)findViewById(R.id.imageButton);
        easy=(ImageButton)findViewById(R.id.imageButton2);
        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LevelsActivity.this,GameActivity.class);
                intent.putExtra("level","1");
                intent.putExtra("prefix","animal");
                startActivity(intent);
            }
        });
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LevelsActivity.this,GameActivity.class);
                intent.putExtra("level","2");
                intent.putExtra("prefix","flower");
                startActivity(intent);
            }
        });
    }
}



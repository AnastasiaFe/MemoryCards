package com.example.anastasia.memorycards;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
public class LevelsActivity extends Activity {
    static ImageButton beginner, easy, third, fourth, fifth, sixth, seventh, eight, nineth;
TextView title;
MainActivity m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.levels);
        m=new MainActivity();
        title=(TextView)findViewById(R.id.levelTitle);
        title.setTypeface(Typeface.createFromAsset(getAssets(),"3.ttf"));
        title.setTextSize(38);
        beginner = (ImageButton) findViewById(R.id.firstLevel);
        easy = (ImageButton) findViewById(R.id.secondLevel);
        easy.setEnabled(false);
        third = (ImageButton) findViewById(R.id.third);
        third.setEnabled(false);
        fourth = (ImageButton) findViewById(R.id.forth);
        fourth.setEnabled(false);
        fifth = (ImageButton) findViewById(R.id.fifth);
        fifth.setEnabled(false);
        sixth = (ImageButton) findViewById(R.id.sixth);
        sixth.setEnabled(false);
        seventh = (ImageButton) findViewById(R.id.seventh);
        seventh.setEnabled(false);
        eight = (ImageButton) findViewById(R.id.eight);
        eight.setEnabled(false);
        nineth = (ImageButton) findViewById(R.id.nines);
        nineth.setEnabled(false);
        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "1");


                startActivity(intent);
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "2");


                startActivity(intent);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "3");
                startActivity(intent);
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "4");
                startActivity(intent);
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "5");
                startActivity(intent);
            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "6");
                startActivity(intent);
            }
        });
        seventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "7");
                startActivity(intent);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "8");
                startActivity(intent);
            }
        });
        nineth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
                intent.putExtra("level", "9");
                startActivity(intent);
            }
        });
        switch (GameActivity.count) {
            case 1:
                easy.setEnabled(true);
                break;
            case 2:
               easy.setEnabled(true);
                third.setEnabled(true);
                break;
            case 3:
                easy.setEnabled(true);
                third.setEnabled(true);
                fourth.setEnabled(true);
                break;
            case 4:easy.setEnabled(true);
              third.setEnabled(true);
               fourth.setEnabled(true);
                fifth.setEnabled(true);
                break;
            case 5:
               easy.setEnabled(true);
                third.setEnabled(true);
                fourth.setEnabled(true);
                fifth.setEnabled(true);
                sixth.setEnabled(true);
                break;
            case 6:
               easy.setEnabled(true);
                third.setEnabled(true);
                fourth.setEnabled(true); fifth.setEnabled(true);
                sixth.setEnabled(true);
                seventh.setEnabled(true);
                break;
            case 7:

                easy.setEnabled(true);
                third.setEnabled(true);
                fourth.setEnabled(true); fifth.setEnabled(true);
                sixth.setEnabled(true);
                seventh.setEnabled(true);
                eight.setEnabled(true);
                break;
            case 8:

               easy.setEnabled(true);
                third.setEnabled(true);
                fourth.setEnabled(true);
                sixth.setEnabled(true); fifth.setEnabled(true);
                seventh.setEnabled(true);
                eight.setEnabled(true);
                nineth.setEnabled(true);
                break;


        }
        if(!beginner.isEnabled())
        {
            beginner.setImageResource(R.drawable.flowers);
        }
        if(!easy.isEnabled())
        {
            easy.setImageResource(R.drawable.muss);
        }
        if(!third.isEnabled())
        {
            third.setImageResource(R.drawable.tastys);
        }
        if(!fourth.isEnabled())
        {
            fourth.setImageResource(R.drawable.foods);
        }
        if(!fifth.isEnabled())
        {
            fifth.setImageResource(R.drawable.animals);
        }
        if(!sixth.isEnabled())
        {
           sixth.setImageResource(R.drawable.pes);

        }
        if(!seventh.isEnabled())
        {
            seventh.setImageResource(R.drawable.doms);
        }
        if(!eight.isEnabled())
        {
            eight.setImageResource(R.drawable.bs);
        }
        if(!nineth.isEnabled())
        {
            nineth.setImageResource(R.drawable.ms);
        }
    }

    public void onBackPressed() { super.onBackPressed();
    }
}



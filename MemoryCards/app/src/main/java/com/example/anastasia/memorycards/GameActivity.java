package com.example.anastasia.memorycards;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Anastasia on 29.06.2016.
 */
public class GameActivity extends Activity {


  static   String level;
    String fileName;
  static   boolean isRecordsOpen;
   static int count=0;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private TextView steps;
    private Chronometer timer;

    private Integer StepCount;
    /** Called when the activity is first created. */
    public GameActivity()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
isRecordsOpen=false;
SharedPreferences settings= PreferenceManager.getDefaultSharedPreferences(this);
  //   String pictureSet=settings.getString("PictureSet", "animal");
        Integer BackgroundColor = Color.parseColor(settings.getString("BackgroundColor", "white"));
        timer = (Chronometer) findViewById(R.id.time);
        steps = (TextView)findViewById(R.id.steps);

        gridView = (GridView)findViewById(R.id.field);
        View root = gridView.getRootView();
        root.setBackgroundColor(BackgroundColor);

        gridView.setEnabled(true);

        StepCount = 0;
        steps.setText(StepCount.toString());

        timer.start();
level=getIntent().getExtras().getString("level");



        switch (level)
        {
            case "1":gridView.setNumColumns(6);gridAdapter = new GridAdapter(this, 2, 6,"flower");break;
            case  "2":gridAdapter = new GridAdapter(this, 4, 4,"mus");gridView.setNumColumns(4);break;
            case "3":gridAdapter=new GridAdapter(this,4,6,"tasty");gridView.setNumColumns(4);break;
            case "4":gridAdapter=new GridAdapter(this,4,8,"food");gridView.setNumColumns(8);break;
            case "5":gridAdapter=new GridAdapter(this,6,6,"animal");gridView.setNumColumns(6);break;
            case "6":gridAdapter=new GridAdapter(this,4,10,"pe");gridView.setNumColumns(10);break;
            case "7":gridAdapter=new GridAdapter(this,6,8,"dom");gridView.setNumColumns(8);break;
          case "8":gridAdapter=new GridAdapter(this,6,10,"b");gridView.setNumColumns(10);break;
           case "9":gridAdapter=new GridAdapter(this,8,8,"monster");gridView.setNumColumns(8);break;

        }
        switch (level)
        {
            case "1":fileName="rec1.txt";break;
            case  "2":fileName="rec2.txt";break;
            case "3":fileName="rec3.txt";break;
            case "4":fileName="rec4.txt";break;
            case "5":fileName="rec5.txt";break;
            case "6":fileName="rec6.txt";break;
            case "7":fileName="rec7.txt";break;
            case "8":fileName="rec8.txt";break;
            case "9":fileName="rec9.txt";break;
        }






           // gridAdapter = new GridAdapter(this, 4, 6);

        //связываем адаптер с гридом
        gridView.setAdapter(gridAdapter);

        //устанавливаем обработчик клика по гриду
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                gridAdapter.checkOpenCells();

                if (gridAdapter.openCell(position)) {
                    StepCount++;
                    steps.setText(StepCount.toString());
                }
                if (gridAdapter.checkGameOver()) {
                    switch(level)
                    {
                        case "1":count=1;break;
                        case "2":count=2;break;
                        case "3":count=3;break;
                        case "4":count=4;break;
                        case "5":count=5;break;
                        case "6":count=6;break;
                        case "7":count=7;break;
                        case "8":count=8;break;
                        case "9":count=9;break;
                    }


                    timer.stop();
                    //!!

                    ShowGameOver();
                }

            }
        });
    }
    private void ShowGameOver () {

        // Диалоговое окно
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

        // Заголовок и текст
        alertbox.setTitle("Congratulations!");
        String time = timer.getText().toString();
        RecordsAdapter ra = new RecordsAdapter(this,fileName);
        // Добавляем новые значения
        String value = StepCount.toString() + " "+ time.toString();
        ra.addRecord(value);
        // Записываем рекорды в файл
        ra.WriteRecords();
        String victoryTextToast = "Your result is one of the best!\nSteps: " + StepCount.toString() + "\nTime: " + time + "\nOpen the records?";
        ArrayList<String> records = ra.getRecords();
        if (records.contains(value)) {
            AlertDialog.Builder alertboxShowRecords = new AlertDialog.Builder(this);
            alertboxShowRecords.setTitle("Congratulations!");
            alertboxShowRecords.setMessage(victoryTextToast);
            alertboxShowRecords.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                   Table.level=level;
                    startActivity(new Intent(GameActivity.this, Table.class));
                    isRecordsOpen=true;
                }
            });

            alertboxShowRecords.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                }

            });

            alertboxShowRecords.show();
        }
        else {
            String TextToast = "Game over \nSteps: " + StepCount.toString() + "\nTime: " + time;

            alertbox.setMessage(TextToast);

            // Добавляем кнопку
            alertbox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // закрываем текущую Activity
                    finish();
                }
            });
            // показываем окно
            alertbox.show();

        }
    }



}

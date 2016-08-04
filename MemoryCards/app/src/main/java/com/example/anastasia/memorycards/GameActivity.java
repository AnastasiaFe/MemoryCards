package com.example.anastasia.memorycards;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;

public class GameActivity extends Activity {

/**the current level*/
  static   String level;

    /**the name of file needed to be opened*/
    String fileName;

    /**is the table of records opened*/
  static   boolean isRecordsOpen;

    /**count of passed levels*/
   static int count=0;

    private GridView gridView;
    private GridAdapter gridAdapter;
    /** Textview that shows the count of made steps*/
    private TextView steps;

    /** the time*/
    private Chronometer timer;

    /**count of steps*/
    private Integer StepCount;
    public GameActivity()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
isRecordsOpen=false;
        timer = (Chronometer) findViewById(R.id.time);
        steps = (TextView)findViewById(R.id.steps);
        gridView = (GridView)findViewById(R.id.field);
        gridView.setEnabled(true);
//set the count of steps
        StepCount = 0;
        steps.setText(StepCount.toString());
//trigger the timer
        timer.start();
        //get the current level
level=getIntent().getExtras().getString("level");


//in dependency with current level open the window with particular field size and set of pictures
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
        //in dependency with current level, set the value of file name
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

        //link the adapter with grid
        gridView.setAdapter(gridAdapter);
        //onClick on gridview processing
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//check the opened cells
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
                    ShowGameOver();
                }

            }
        });
    }
    private void ShowGameOver () {

        // Dialog window
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

        // Header and text
        alertbox.setTitle("Congratulations!");
        String time = timer.getText().toString();


        RecordsAdapter ra = new RecordsAdapter(this,fileName);
        // Add new value
        String value = StepCount.toString() + " "+ time;
        ra.addRecord(value);
        // Write records to file
        ra.WriteRecords();
        //the text when the result of user is one of the best
        String victoryTextToast = "Your result is one of the best!\nSteps: " + StepCount.toString() + "\nTime: " + time + "\nOpen the records?";
        ArrayList<String> records = ra.getRecords();
        //in dependency with new user's result, create 2 different alertboxes
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
            // add the neutral ok button to the alertbox
            alertbox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // close the current Activity
                    finish();
                }
            });
            // show the alertbox
            alertbox.show();

        }
    }



}

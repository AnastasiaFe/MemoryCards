package com.example.anastasia.memorycards;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by Anastasia on 29.06.2016.
 */
public class GameActivity extends Activity {

    String level;
    String prefix;
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



        if(level.equals("1"))
        {


            gridView.setNumColumns(6);
            gridAdapter = new GridAdapter(this, 6, 6,"animal");


        }
        else if(level.equals("2"))
        {
            gridAdapter = new GridAdapter(this, 4, 4,"flower");
            gridView.setNumColumns(4);

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
                    timer.stop();
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

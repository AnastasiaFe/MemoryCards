package com.example.anastasia.memorycards;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
public class Table extends ListActivity {
  public static String level;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);


            switch (level) {
                case "1":
                    fileName = "rec1.txt";
                    break;
                case "2":
                    fileName = "rec2.txt";
                    break;
                case "3":
                    fileName = "rec3.txt";
                    break;
                case "4":
                    fileName = "rec4.txt";
                    break;
                case "5":
                    fileName = "rec5.txt";
                    break;
                case "6":
                    fileName = "rec6.txt";
                    break;
                case "7":
                    fileName = "rec7.txt";
                    break;
                case "8":
                    fileName = "rec8.txt";
                    break;
                case "9":
                    fileName = "rec9.txt";
                    break;
            }

        RecordsAdapter ra = new RecordsAdapter (this,fileName);
        ArrayList<String> arr = ra.getRecords();

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, R.layout.item, arr);
        setListAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {
        if(GameActivity.isRecordsOpen)
        {
            finish();
            finish();
        }
        super.onBackPressed();
    }
}

package com.example.anastasia.memorycards;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Anastasia on 22.07.2016.
 */
public class RecordsAdapter {
    ArrayList<String> records;
String fileName;
    Context context;

    public RecordsAdapter(Context mcontext,String fileName) {
        records = new ArrayList<>();
        context = mcontext;
        this.fileName=fileName;
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            records = (ArrayList<String>) is.readObject();
            is.close();
        } catch (Exception e) {
            //Toast.makeText(context, "Произошла ошибка чтения таблицы рекордов", Toast.LENGTH_LONG);
            e.printStackTrace();
        }
    }

    public void WriteRecords() {

        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(records);
            os.close();
        } catch (Exception e) {
           // Toast.makeText(context, "Произошла ошибка записи в таблицу рекордов", Toast.LENGTH_LONG);
            e.printStackTrace();

        }

    }

    public void addRecord(String record) {

        if (!records.contains(record))
            records.add(record);

       Collections.sort(records);

        for (int i = 5; i < records.size(); i++)
            records.remove(i);




    }





    public ArrayList<String> getRecords()
    {

        return records;
    }
}

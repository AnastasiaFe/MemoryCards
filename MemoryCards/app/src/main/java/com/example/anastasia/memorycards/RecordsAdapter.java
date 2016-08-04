package com.example.anastasia.memorycards;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class RecordsAdapter {
    /**the list of records*/
    ArrayList<String> records;
    /**the name of file needed to be opened*/
String fileName;
    Context context;

    public RecordsAdapter(Context mcontext,String fileName) {
        records = new ArrayList<>();
        context = mcontext;
        this.fileName=fileName;
        try {
            /**fill the list of records by data read in the file named filename*/
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            records = (ArrayList<String>) is.readObject();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/**write records into the file*/
    public void WriteRecords() {

        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(records);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    /**add a record to the list of records*/
    public void addRecord(String record) {
//if the list doesn't contain such record - add it to the list.
        if (!records.contains(record))
            records.add(record);
//sort the list of records
       Collections.sort(records);
//take only 5 first records
        for (int i = 5; i < records.size(); i++)
            records.remove(i);
    }

/**get the list of records*/
    public ArrayList<String> getRecords()
    {
        return records;
    }
}

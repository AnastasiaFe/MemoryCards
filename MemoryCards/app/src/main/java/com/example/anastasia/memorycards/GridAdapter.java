package com.example.anastasia.memorycards;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Anastasia on 29.06.2016.
 */
public class GridAdapter extends BaseAdapter {

    private Context context;
    private Integer cols, rows;

    private  enum Status {CELL_OPEN, CELL_CLOSE, CELL_DELETE};
    //cостояния картинки
    private ArrayList<Status> statuses;
    //картинки
    private ArrayList<String> pictures;
    //префикс картинки
    private String prefix;
    //ресурсы приложения
    private Resources resources;

    public GridAdapter(Context context, int mcols, int mrows,String prefix) {
        this.context = context;
        cols = mcols;
        rows = mrows;
        statuses =new ArrayList<>();
this.prefix=prefix;
        pictures = new ArrayList<String>();
        //дальше взять из настроек!!!!
        // Получаем все ресурсы приложения
        resources = this.context.getResources();
        makePictArray();
        closeAllCells();
    }
//устанавливаем статус закрытой всем картинкам
    private void closeAllCells() {
        statuses.clear();
        for (int i = 0; i < cols * rows; i++)
            statuses.add(Status.CELL_CLOSE);
    }

    private void makePictArray () {
        pictures.clear();
        for (int i = 0; i < ((cols * rows) / 2); i++)
        {
            pictures.add(prefix + Integer.toString(i));
            pictures.add(prefix + Integer.toString(i));
        }
        // перемешиваем
        Collections.shuffle(pictures);
    }
    @Override
    public int getCount() {
        return cols * rows;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView view;

        if (convertView == null)
            view = new ImageView(context);
        else
            view = (ImageView) convertView;

        switch (statuses.get(position)) {
            case CELL_OPEN:
                // Получаем идентификатор ресурса для картинки,
                // которая находится в векторе  на позиции position
                Integer drawableId = resources.getIdentifier(pictures.get(position),
                        "drawable", context.getPackageName());
                view.setImageResource(drawableId);
                break;
            case CELL_CLOSE:
                view.setImageResource(R.drawable.close);
                break;
            default:
                view.setImageResource(R.drawable.none);
        }
        return view;
    }
    public void checkOpenCells() {
        int first = statuses.indexOf(Status.CELL_OPEN);
        int second = statuses.lastIndexOf(Status.CELL_OPEN);
        if (first == second)
            return;
        if (pictures.get(first).equals (pictures.get(second)))
        {
            statuses.set(first, Status.CELL_DELETE);
            statuses.set(second, Status.CELL_DELETE);
        }
        else
        {
            statuses.set(first, Status.CELL_CLOSE);
            statuses.set(second, Status.CELL_CLOSE);
        }
        return;
    }

    public boolean openCell(int position) {
        if (statuses.get(position) == Status.CELL_DELETE
                || statuses.get(position) == Status.CELL_OPEN)
            return false;

        if (statuses.get(position) != Status.CELL_DELETE)
            statuses.set(position, Status.CELL_OPEN);

        notifyDataSetChanged();
        return true;
    }

    public boolean checkGameOver() {
        if (statuses.indexOf(Status.CELL_CLOSE) < 0)
            return true;
        return false;
    }
}

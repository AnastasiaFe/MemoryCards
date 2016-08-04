package com.example.anastasia.memorycards;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
public class GridAdapter extends BaseAdapter {

    private Context context;
    /**number of cols and rows in the gridview*/
    private Integer cols, rows;

private SoundPool sounds;
    /**sound for true step*/
    private int sPlus;
/**enum that contains conditions of picture*/
    private  enum Status {CELL_OPEN, CELL_CLOSE, CELL_DELETE}
    /**conditions of picture*/
    private ArrayList<Status> statuses;
   /**names of pictures*/
    private ArrayList<String> pictures;
   /**prefix of the picture*/
    private String prefix;
    /**resources of application*/
    private Resources resources;

    public GridAdapter(Context context, int mcols, int mrows,String prefix) {
        this.context = context;
        cols = mcols;
        rows = mrows;
        statuses =new ArrayList<>();
this.prefix=prefix;
        pictures = new ArrayList<>();
        // get all resources of application
        resources = this.context.getResources();
        makePictArray();
        closeAllCells();
        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        sPlus = sounds.load(context, R.raw.plus, 1);

    }
/**set the closed status to all pictures*/
    private void closeAllCells() {
        statuses.clear();
        for (int i = 0; i < cols * rows; i++)
            statuses.add(Status.CELL_CLOSE);
    }
/**make the array of pictures*/
    private void makePictArray () {
        pictures.clear();
        for (int i = 0; i < ((cols * rows) / 2); i++)
        {
            pictures.add(prefix + Integer.toString(i));
            pictures.add(prefix + Integer.toString(i));
        }
        // mix the pictures
        Collections.shuffle(pictures);
    }
    /**get the count of pictures
     * @return count of pictures*/
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
/**get the view of the picture
 * @return the view of the picture*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView view;

        if (convertView == null)
            view = new ImageView(context);
        else
            view = (ImageView) convertView;

        switch (statuses.get(position)) {
            case CELL_OPEN:
              //get the id of the picture which is on the position
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
    /**check 2 opened pictures on equality*/
    public void checkOpenCells() {
        int first = statuses.indexOf(Status.CELL_OPEN);
        int second = statuses.lastIndexOf(Status.CELL_OPEN);
        if (first == second)
            return;
        if (pictures.get(first).equals (pictures.get(second)))
        {
            if(MainActivity.isButtonClicked){
                sounds.play(sPlus, 1.0f, 1.0f, 0, 0, 1.5f);}

            statuses.set(first, Status.CELL_DELETE);
            statuses.set(second, Status.CELL_DELETE);
        }
        else
        {
            statuses.set(first, Status.CELL_CLOSE);
            statuses.set(second, Status.CELL_CLOSE);
        }

    }
/**is the picture opened*/
    public boolean openCell(int position) {
        if (statuses.get(position) == Status.CELL_DELETE
                || statuses.get(position) == Status.CELL_OPEN)
            return false;

        if (statuses.get(position) != Status.CELL_DELETE)
            statuses.set(position, Status.CELL_OPEN);

        notifyDataSetChanged();
        return true;
    }
/**is the game over*/
    public boolean checkGameOver() {
        if (statuses.indexOf(Status.CELL_CLOSE) < 0){
            if(MainActivity.isButtonClicked){
            sounds.play(sPlus, 1.0f, 1.0f, 0, 0, 1.5f);}
            return true;}
        return false;
    }
}

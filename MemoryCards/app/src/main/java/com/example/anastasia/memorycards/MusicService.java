package com.example.anastasia.memorycards;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Anastasia on 21.07.2016.
 */
public class MusicService extends Service {

    MediaPlayer player;
    @Override
    public void onCreate() {
       // Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();

        player = MediaPlayer.create(this, R.raw.mus);
        player.setLooping(true); // зацикливаем
    }

    @Override
    public void onDestroy() {
       // Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        player.stop();
    }

    @Override
    public void onStart(Intent intent, int startId) {
       // Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        player.start();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

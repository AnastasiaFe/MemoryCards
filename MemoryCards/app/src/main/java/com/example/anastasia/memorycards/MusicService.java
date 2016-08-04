package com.example.anastasia.memorycards;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    MediaPlayer player;
    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.mus);
        player.setLooping(true);
    }

    @Override
    public void onDestroy() {
        player.stop();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        player.start();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

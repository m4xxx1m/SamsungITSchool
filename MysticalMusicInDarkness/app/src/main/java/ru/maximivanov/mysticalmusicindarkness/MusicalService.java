package ru.maximivanov.mysticalmusicindarkness;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicalService extends Service {
    static MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.mystical);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("info", "mediaPlayer created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread = new Thread(new MusicRunnable());
        thread.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    static class MusicRunnable implements Runnable {
        @Override
        public void run() {
            try {
                mediaPlayer.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }
}

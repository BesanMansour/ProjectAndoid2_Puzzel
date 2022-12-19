package com.example.finalproject.modle;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.finalproject.R;

public class MyService extends Service {

    MediaPlayer mp ;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sound);
        // عشان لمن يخلص الصوت تتطفي السيرفس
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSelf();
            }
        });
    }
    // يتم استدعاءها عند تشغبل السيرفس
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mp.isPlaying())
            mp.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying() && mp != null){
            mp.stop();
            mp.release();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
package com.example.mycalc;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.provider.Settings;

public class MusicService extends Service {
    MediaPlayer mPlayer;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

        mPlayer.setLooping(false);
        mPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        mPlayer.stop();
        mPlayer.release();
    }
    @Override
    public IBinder onBind(Intent objIndent) {
        return null;
    }
}
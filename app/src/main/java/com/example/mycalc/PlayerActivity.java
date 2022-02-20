package com.example.mycalc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button play, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        play = findViewById(R.id.play_btn);
        stop = findViewById(R.id.stop_btn);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);

        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(new AirplaneModeReceiver(), filter);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.play_btn:
                startService(new Intent(this, MusicService.class));
                break;
            case R.id.stop_btn:
                stopService(new Intent(this, MusicService.class));
                break;
        }
    }
}

package com.example.mycalc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, MusicService.class);
        if(intent.getAction() == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            if (isAirplaneModeOn(context.getApplicationContext())) {
                context.stopService(myIntent);
            }
            else {
                Toast.makeText(context, "AirPlane Mode is off", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}

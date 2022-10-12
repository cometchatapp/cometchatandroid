package com.example.arteria;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import com.example.explibrarychatsdk.Wavelabs;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CUSTOM_APP_LOG", "app module");
        Wavelabs.setAppConstants(
                this,
                AppConstants.APP_ID,
                AppConstants.REGION,
                AppConstants.AUTH_KEY,
                AppConstants.CHANNEL_1_ID,
                AppConstants.CHANNEL_2_ID
        );
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){


            NotificationChannel channel1 = new NotificationChannel(
                    AppConstants.CHANNEL_1_ID,
                    "channel1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            channel1.setShowBadge(true);



            NotificationChannel channel2 = new NotificationChannel(
                    AppConstants.CHANNEL_2_ID,
                    "channel2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel1.setDescription("This is Channel 2");
            channel1.setShowBadge(true);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}

package com.example.explibrarychatsdk;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import com.cometchat.pro.core.AppSettings;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.uikit.ui_settings.UIKitSettings;
import com.example.explibrarychatsdk.constants.AppConfig;

public class Wavelabs extends Application {

    private static final String TAG = "MY_TESTING_LOGS";
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    public static void setAppConstants(Application application, String APP_ID, String REGION, String AUTH_KEY, String CHANNEL1, String CHANNEL2) {
        AppConfig.APP_ID = APP_ID;
        AppConfig.REGION = REGION;
        AppConfig.AUTH_KEY = AUTH_KEY;
        AppConfig.CHANNEL_1_ID = CHANNEL1;
        AppConfig.CHANNEL_2_ID = CHANNEL2;

        initCometChat(application);
    }

    public static void initCometChat(Application application) {
        AppSettings appSettings = new AppSettings.AppSettingsBuilder()
                .subscribePresenceForAllUsers()
                .subscribePresenceForFriends()
                .setRegion(AppConfig.REGION)
                .build();

        CometChat.init(
                application,
                AppConfig.APP_ID,
                appSettings,
                new CometChat.CallbackListener<String>() {
                    @Override
                    public void onSuccess(String successMessage) {
                        UIKitSettings.setAuthKey(AppConfig.AUTH_KEY);
                        CometChat.setSource("ui-kit", "android", "java");
                        Log.d(TAG, "Initialization completed successfully");
                    }

                    @Override
                    public void onError(CometChatException e) {
                        Log.d(TAG, "Initialization failed with exception: " + e.getMessage());
                    }
                });
    }
}

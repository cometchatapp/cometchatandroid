package com.example.explibrarychatsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.cometchat.pro.uikit.ui_components.cometchat_ui.CometChatUI;
import com.example.explibrarychatsdk.callbacks.CallbackInterface;
import com.example.explibrarychatsdk.constants.AppConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class WavelabsChatActivity extends AppCompatActivity {

    public static final String TAG = "LoginClass";
    private static String token;
    //    private static int count = 0;
    private static Context context = null;

    private static Class homeScreenActivities = null;
    private static boolean isHomeScreenEnable = false;

private static CallbackInterface callbackinterface=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "I am getting called");

        String uid = getIntent().getStringExtra("USER_ID");
        if (uid != null) {
            launchChatScreen(this, uid, true, CometChatUI.class, new CallbackInterface() {
                @Override
                public void onCallbackResponse(String onCallbackResponse) {

                }
            });
        } else {
            Intent intent = new Intent();
            intent.setClass(this, CometChatUI.class);
            startActivity(intent);
        }
    }

    public static void launchChatScreen(
            Activity MainActivity,
            String UID,
            boolean isEnableHomeScreen,
            Class homeScreenActivity,
            CallbackInterface callbackInterface) {
        callbackinterface=callbackInterface;
        isHomeScreenEnable = isEnableHomeScreen;
        homeScreenActivities = homeScreenActivity;
        context = MainActivity.getBaseContext();
        CometChatUI.setHomeActivity(homeScreenActivity, isEnableHomeScreen);
        try {
            CometChat.login(UID, AppConfig.AUTH_KEY, new CometChat.CallbackListener<User>() {

                @Override
                public void onSuccess(User user) {
                    Log.d(TAG, "Login Successful: " + user.toString());

                    token = MyFirebaseMessagingService.token; // firebaseInstance
                    if (token == null)
                        fetchFirebaseToken(MainActivity, UID);
                    else
                        registerPushNotifications(MainActivity, token, UID);
                }

                @Override
                public void onError(CometChatException e) {

                    if(callbackinterface!=null){
                        callbackinterface.onCallbackResponse(e.getMessage());
                    }
                    Log.d(TAG, "Login failed with exception: " + e.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fetchFirebaseToken(Activity MainActivity, String UID) {
        //@Provided by Google docs
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    if(callbackinterface!=null){
                        callbackinterface.onCallbackResponse("Unable to fetch FirebaseToken");
                    }
                    return;
                }
                token = task.getResult();
                Log.d(TAG, token);
                registerPushNotifications(MainActivity, token, UID);
            }
        });
    }

    private static void registerPushNotifications(Activity MainActivity, String token, String uid) {
        CometChat.registerTokenForPushNotification(token, new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e("onSuccessPN: ", s);


                Intent intent = new Intent();
                intent.setClass(MainActivity, CometChatUI.class);
                MainActivity.startActivity(intent);
            }

            @Override
            public void onError(CometChatException e) {
                Log.e("onErrorPN: ", e.getMessage());
                if(callbackinterface!=null){
                    callbackinterface.onCallbackResponse(e.getMessage());
                }
            }
        });
    }
}

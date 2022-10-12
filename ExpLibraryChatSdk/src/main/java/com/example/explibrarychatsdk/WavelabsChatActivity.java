package com.example.explibrarychatsdk;

import static android.provider.Contacts.GroupMembership.GROUP_ID;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.cometchat.pro.uikit.ui_components.cometchat_ui.CometChatUI;
import com.cometchat.pro.uikit.ui_components.messages.message_list.CometChatMessageListActivity;
import com.cometchat.pro.uikit.ui_resources.constants.UIKitConstants;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "I am getting called");

        String uid = getIntent().getStringExtra("USER_ID");
        if (uid != null) {
            launchChatScreen(this, uid, true, CometChatUI.class);
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
            Class homeScreenActivity
    ) {
        isHomeScreenEnable = isEnableHomeScreen;
        homeScreenActivities = homeScreenActivity;
        context = MainActivity.getBaseContext();
        CometChatUI.setHomeActivity(homeScreenActivity,isEnableHomeScreen);
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
            }
        });
    }
}

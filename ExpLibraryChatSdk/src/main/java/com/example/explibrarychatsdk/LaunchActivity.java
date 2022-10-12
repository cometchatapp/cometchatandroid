package com.example.explibrarychatsdk;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.example.explibrarychatsdk.constants.AppConfig;

public class LaunchActivity extends AppCompatActivity {

    public static final String TAG = "LauchActivity";
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            String username = intent.getStringExtra("USERNAME");
            String uid = intent.getStringExtra("UID");

            proceedToLogin(uid);
        }
    }

    private void proceedToLogin(String UID){
        CometChat.login(UID, AppConfig.AUTH_KEY, new CometChat.CallbackListener<User>(){

            @Override
            public void onSuccess(User user) {
                Log.d(TAG, "Login Successful: " + user.toString());
            }

            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Login failed with exception: " + e.getMessage());
            }
        });
    }
}

//android:name=".ExpLibraryChatSdkApp"


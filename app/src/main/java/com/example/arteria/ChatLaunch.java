package com.example.arteria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.uikit.ui_components.cometchat_ui.CometChatUI;
import com.example.explibrarychatsdk.WavelabsChatActivity;

public class ChatLaunch extends AppCompatActivity {

    private static final String TAG = ChatLaunch.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_launch);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.e(TAG, "onActvityResult");

        if (requestCode == 0) {
            Log.e(TAG, "onActvityResult");
            WavelabsChatActivity.launchChatScreen(
                    ChatLaunch.this,
                    MainActivity.USER_ID,
                    true,
                    CometChatUI.class
            );
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void launchChatApp(View view) {
        String UID = "";
        if (CometChat.getLoggedInUser() != null) {
            UID = CometChat.getLoggedInUser().getUid();
        } else {
            UID = MainActivity.USER_ID;
        }
        WavelabsChatActivity.launchChatScreen(
                ChatLaunch.this,
                MainActivity.USER_ID,
                true,
                CometChatUI.class
        );
    }
}
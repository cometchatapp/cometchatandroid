package com.example.arteria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cometchat.pro.core.CometChat;
import com.example.explibrarychatsdk.WavelabsChatActivity;
import com.example.explibrarychatsdk.create_user.CreateUser;

public class MainActivity extends AppCompatActivity {

    EditText _user_id;
    public static String USERNAME = "";
    public static String USER_ID = "";

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _user_id = findViewById(R.id.view_user_id);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                Object value = extras.get(key);
                Log.e(TAG, "Extras received at onCreate:  Key: " + key + " Value: " + value);
            }
            String title = extras.getString("title");
            String message = extras.getString("body");
            if (message!=null && message.length()>0) {
                getIntent().removeExtra("body");
                //showNotificationInADialog(title, message);

                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void arteriaLogin(View view) {
        USER_ID = _user_id.getText().toString();

        if (USER_ID.isEmpty()) {
            if (USER_ID.isEmpty()) _user_id.setError("Please provide User ID");
        } else {
            Intent intent = new Intent(this, WavelabsChatActivity.class);
            intent.putExtra("USER_ID", USER_ID);
            startActivity(intent);
            //WavelabsChatActivity.launchChatScreen(MainActivity.this, USER_ID);
        }
    }

    public void createNewUser(View view) {
        startActivity(new Intent(MainActivity.this, CreateUser.class));
    }
}
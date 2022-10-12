package com.example.explibrarychatsdk.create_user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.example.explibrarychatsdk.R;
import com.example.explibrarychatsdk.constants.AppConfig;

import java.util.ArrayList;
import java.util.List;

public class CreateUser extends AppCompatActivity {

    EditText uid, username, userrole, designationTag, locationTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        initUIElements();
    }

    private void initUIElements(){
        uid = findViewById(R.id.new_user_id);
        username = findViewById(R.id.new_user_name);
        locationTag = findViewById(R.id.location_tag);
        designationTag = findViewById(R.id.designation_tag);
    }

    public void createUserButtonClick(View view) {
        if(uid.getText().toString().isEmpty())
            uid.setError("UID required");
        else if(username.getText().toString().isEmpty())
            username.setError("Username required");
        else if(locationTag.getText().toString().isEmpty())
            locationTag.setError("Location Tag required");
        else if(designationTag.getText().toString().isEmpty())
            designationTag.setError("Designation Tag required");
        else
            createUserInCometChat();
    }

    private void createUserInCometChat(){
        User user = new User();
        user.setUid(uid.getText().toString()); // Replace with your uid for the user to be created.
        user.setName(username.getText().toString()); // Replace with the name of the user

        List<String> tags = new ArrayList<>();
        tags.add(locationTag.getText().toString());
        tags.add(designationTag.getText().toString());
        user.setTags(tags);

        CometChat.createUser(user, AppConfig.AUTH_KEY, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                Log.d("createUser", user.toString());
                finish();
            }

            @Override
            public void onError(CometChatException e) {

            }
        });
    }
}
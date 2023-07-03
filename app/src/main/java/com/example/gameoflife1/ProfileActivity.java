package com.example.gameoflife1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gameoflife1.controller.UserController;
import com.example.gameoflife1.model.UserModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    Button Update;
    EditText username, email, newpassword;

    DatabaseReference mDatabase;
    UserModel userModel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Update = findViewById(R.id.btnUpdate);
        username = findViewById(R.id.username);
        email = findViewById(R.id.emailprofile);
        newpassword = findViewById(R.id.newpassword);

        String username2 = getIntent().getStringExtra("username");
        username.setText(username2);
        UserController userController = new UserController();
        userController.getUserEmail(username2, email);
        Log.d("username", username2);

        context = this;
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = email.getText().toString();
                String password = newpassword.getText().toString();
                userController.updatePass(username2, email2, password, context);
            }
        });
    }
}
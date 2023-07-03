package com.example.gameoflife1.controller;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.gameoflife1.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    DatabaseReference mDatabase;
    UserModel userModel;

    public UserController(){
        mDatabase = FirebaseDatabase.getInstance().getReference("User");
    }

    public void getUserEmail(String username, EditText email){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    if (data.child("username").getValue(String.class).equals(username)){
                        email.setText(data.child("email").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updatePass(String username, String email ,String pass, Context context){
        if(!pass.equals(null)){
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String id;
                    for(DataSnapshot data: snapshot.getChildren()){
                        if(data.child("username").getValue(String.class).equals(username)){
                            id = data.getKey();
                            UserModel userModel = new UserModel();
                            userModel.setEmail(email);
                            userModel.setUsername(username);
                            userModel.setPass(pass);
                            mDatabase.child(id).setValue(userModel);
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(context, "New password can not bet empty", Toast.LENGTH_SHORT).show();
        }
    }
}

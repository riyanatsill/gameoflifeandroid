package com.example.gameoflife1.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.gameoflife1.adapter.SectionAdapter;
import com.example.gameoflife1.model.GamesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GamesController {
    private DatabaseReference mDatabase;

    public GamesController(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Games");
    }
    public void setSectionAdapter(SectionAdapter adapter, List<GamesModel> gamesModelList){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gamesModelList.clear();
                for(DataSnapshot data: snapshot.getChildren()){
                    GamesModel result = data.getValue(GamesModel.class);
                    result.setGame_id(data.child("game_id").getValue(String.class));
                    gamesModelList.add(result);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setTitleSection(int position, TextView title, ImageView image, Context context){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    if(data.child("game_id").getValue(String.class).equals(String.valueOf(position+1))){
                        title.setText(data.child("game").getValue(String.class));
                        Glide.with(context).load(data.child("imageUrl").getValue(String.class)).into(image);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}


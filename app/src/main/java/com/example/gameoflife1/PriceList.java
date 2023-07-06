package com.example.gameoflife1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.gameoflife1.adapter.SectionAdapter;
import com.example.gameoflife1.controller.GamesController;
import com.example.gameoflife1.model.GamesModel;

import java.util.ArrayList;
import java.util.List;

public class PriceList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SectionAdapter sectionAdapter;
    private GamesController gamesController;
    private List<GamesModel> gamesModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list); // Replace with the correct XML layout file name

        recyclerView = findViewById(R.id.recyclerView2); // Make sure the ID matches your XML layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        gamesController = new GamesController();

        gamesModelList = new ArrayList<>();

        sectionAdapter = new SectionAdapter(gamesModelList);
        recyclerView.setAdapter(sectionAdapter);

        gamesController.setSectionAdapter(sectionAdapter, gamesModelList);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon2);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
    public void onBackPressed() {
        Intent intent = new Intent(PriceList.this, MainActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}

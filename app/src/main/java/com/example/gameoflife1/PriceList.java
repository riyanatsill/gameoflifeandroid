package com.example.gameoflife1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.gameoflife1.adapter.SectionAdapter;
import com.example.gameoflife1.controller.GamesController;
import com.example.gameoflife1.controller.ProductController;
import com.example.gameoflife1.model.GamesModel;
import com.example.gameoflife1.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class PriceList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SectionAdapter sectionAdapter;
    private GamesController gamesController;
    private List<GamesModel> gamesModelList;
    private ProductModel productModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list);

        recyclerView = findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        gamesController = new GamesController();

        gamesModelList = new ArrayList<>();

        List<ProductModel> sectionList = new ArrayList<>();
        sectionAdapter = new SectionAdapter(gamesModelList);
        recyclerView.setAdapter(sectionAdapter);

        gamesController.setSectionAdapter(sectionAdapter, gamesModelList);

//        for(int i=0;i<4;i++){
//            switch (i){
//                case 0:
//                    List<ProductModel> valoList = new ArrayList<>();
//                    controller.setSectionAdapter(valoList, sectionAdapter, String.valueOf(i), gamesModelList);
//                    break;
//                case 1:
//                    List<ProductModel> pubgList = new ArrayList<>();
//                    controller.setSectionAdapter(pubgList, sectionAdapter, String.valueOf(i), gamesModelList);
//                    break;
//                case 2:
//                    List<ProductModel> mlList = new ArrayList<>();
//                    controller.setSectionAdapter(mlList, sectionAdapter, String.valueOf(i), gamesModelList);
//                    break;
//                case 3:
//                    List<ProductModel> genshinList = new ArrayList<>();
//                    controller.setSectionAdapter(genshinList, sectionAdapter, String.valueOf(i), gamesModelList);
//                    break;
//            }
//        }
    }
}
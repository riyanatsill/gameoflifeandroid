package com.example.gameoflife1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.gameoflife1.adapter.MyAdapter;
import com.example.gameoflife1.controller.TransactionController;
import com.example.gameoflife1.model.GenshinModel;
import com.example.gameoflife1.model.MlModel;
import com.example.gameoflife1.model.ProductModel;
import com.example.gameoflife1.model.PubgmModel;
import com.example.gameoflife1.model.TransactionModel;
import com.example.gameoflife1.model.ValorantModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private TransactionModel transactionModel;
    private List<ProductModel> productModelList;
    private List<TransactionModel> transactionModelList;
    private List<ValorantModel> valorantModelList;
    private List<GenshinModel> genshinModelList;
    private List<PubgmModel> pubgmModelList;
    private List<MlModel> mlModelList;
    private TransactionController controller;
    private String username;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private Context context;
    private GridLayoutManager gridLayoutManager;

    public HistoryActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        username = getIntent().getStringExtra("username");

        transactionModel = new TransactionModel();
        transactionModelList = new ArrayList<>();
        controller = new TransactionController();

        productModelList = new ArrayList<>();
        valorantModelList = new ArrayList<>();
        genshinModelList = new ArrayList<>();
        pubgmModelList = new ArrayList<>();
        mlModelList = new ArrayList<>();

        transactionModel.setUsername(username);
        Log.d("username", transactionModel.getUsername());

        context = this;

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(context, valorantModelList, genshinModelList, mlModelList, pubgmModelList,
                transactionModel, transactionModelList, productModelList);
        gridLayoutManager = new GridLayoutManager(context, 1);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);

        controller.setAdapter(transactionModel,transactionModel.getUsername(),transactionModelList,adapter);
    }
}
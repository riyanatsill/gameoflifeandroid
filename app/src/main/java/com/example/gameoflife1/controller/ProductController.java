package com.example.gameoflife1.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.gameoflife1.model.ProductModel;
import com.example.gameoflife1.model.TransactionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    DatabaseReference mDatabase;
    ProductModel model;
    public ProductController(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Product");
    }

    public void getImageUrl(String gameName, List<ProductModel> productModelList, ImageView image,
                            List<TransactionModel> transactionModelList, Context context, int position, String gameId){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    if(data.child("game_id").getValue(String.class).equals(gameId)){
                        ProductModel result = data.getValue(ProductModel.class);
                        result.setImageUrl(data.child("imageUrl").getValue(String.class));
                        result.setGame_id(data.child("game_id").getValue(String.class));
                        productModelList.add(result);
                    }
                }
                setRecImage(transactionModelList, productModelList, gameName, image, position, context);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setRecImage(List<TransactionModel> transactionModelList, List<ProductModel> productModelList,
                            String gameName, ImageView image, int position, Context context){
        for(int i=0;i<productModelList.size();i++){
            TransactionModel dataTrans = transactionModelList.get(position);
            ProductModel dataPro = productModelList.get(i);
            if(dataTrans.getGame().equals(gameName)){
                Glide.with(context).load(productModelList.get(i).getImageUrl()).into(image);
            }
        }
    }
}

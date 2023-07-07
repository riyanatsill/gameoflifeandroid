package com.example.gameoflife1.controller;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gameoflife1.adapter.MyAdapter;
import com.example.gameoflife1.model.ProductModel;
import com.example.gameoflife1.model.TransactionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Random;

public class TransactionController {
    DatabaseReference mDatabase;
    TransactionModel transactionModel;
    public TransactionController(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Transaction");
    }

    public void setAdapter(TransactionModel transactionModel, String username, List<TransactionModel> transactionModelList,
                           MyAdapter adapter){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    if(data.child("username").getValue(String.class).equals(username)){
                        TransactionModel result = data.getValue(TransactionModel.class);
                        result.setUsername(data.child("username").getValue(String.class));
                        result.setGame(data.child("game").getValue(String.class));
                        result.setStatus(data.child("status").getValue(String.class));
                        result.setId(data.child("id").getValue(String.class));
                        result.setProduct(data.child("product").getValue(String.class));
                        result.setPayment(data.child("payment").getValue(String.class));
                        result.setKey(data.getKey());

                        transactionModelList.add(result);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setholder(int position, List<TransactionModel> transactionModelList, TransactionModel transactionModel,
                          TextView title, TextView id, TextView payment, TextView product, ImageView image, List<ProductModel> productModelList){
        Log.d("Set holder", "masuk");
        for (int i = 0; i < transactionModelList.size(); i++){
            Log.d("set holder", "loop");
            TransactionModel game = transactionModelList.get(i);
            String gameName = game.getGame();
            String gameId;
            if(gameName.equals("Valorant")){
                gameId = "1";
                Log.d("set holder game", gameName);
                valorant(position, transactionModelList, title, id, payment, product, image,
                        gameName, productModelList, image.getContext(), gameId);
            }
            if(gameName.equals("Genshin Impact")) {
                gameId = "4";
                Log.d("set holder game", gameName);
                valorant(position, transactionModelList, title, id, payment, product, image,
                        gameName, productModelList, image.getContext(), gameId);
            }
            if(gameName.equals("PUBG Mobile")) {
                gameId = "2";
                Log.d("set holder game", gameName);
                valorant(position, transactionModelList, title, id, payment, product, image,
                        gameName, productModelList, image.getContext(), gameId);
            }
            if(gameName.equals("Mobile Legend")) {
                gameId = "3";
                Log.d("set holder game", gameName);
                valorant(position, transactionModelList, title, id, payment, product, image,
                        gameName, productModelList, image.getContext(), gameId);
            }
        }
    }

    public void valorant(int i, List<TransactionModel> transactionModelList, TextView title,
                        TextView id, TextView payment, TextView product, ImageView image, String gameName,
                        List<ProductModel> productModelList, Context context, String gameId){

        Log.d("valorant", "masuk");
        ProductController productController = new ProductController();
        productController.getImageUrl(gameName, productModelList, image, transactionModelList, context, i, gameId);

        title.setText(transactionModelList.get(i).getGame());
        id.setText(transactionModelList.get(i).getId());
        payment.setText(transactionModelList.get(i).getPayment());
        product.setText(transactionModelList.get(i).getProduct());
    }
}

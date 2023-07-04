package com.example.gameoflife1.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.gameoflife1.adapter.SectionAdapter;
import com.example.gameoflife1.model.GamesModel;
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

    public void setTextValue(List<ProductModel> productModelList, String gameId, TextView product, int position, TextView price) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModelList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    ProductModel result = data.getValue(ProductModel.class);
                    if(data.child("game_id").getValue(String.class).equals(gameId)){
                        result.setProduct(data.child("product").getValue(String.class));
                        result.setPrice(data.child("price").getValue(String.class));
                        Log.d("event listener", data.child("price").getValue(String.class));
                        productModelList.add(result);
                    }
                }
                Log.d("size", String.valueOf(productModelList.size()));
                for(int i=0;i<productModelList.size();i++){
                    if(i == position){
                        ProductModel dataModel = productModelList.get(i);
                        Log.d("product Text", String.valueOf(product));
                        product.setText(dataModel.getProduct());
                        price.setText(dataModel.getPrice());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setTextView(int position, TextView product, TextView price, TableRow row, SectionAdapter.LinearViewHolder holder){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ProductModel> listModel = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    if (data.child("game_id").getValue(String.class).equals(String.valueOf(position+1))) {
                        ProductModel result = data.getValue(ProductModel.class);
                        result.setProduct(data.child("product").getValue(String.class));
                        result.setPrice(data.child("price").getValue(String.class));
                        listModel.add(result);
                    }
                }

                for (int i = 0; i < listModel.size(); i++) {
                    if(i<8){
                        ProductModel data = listModel.get(i);
                        TableRow row = new TableRow(holder.itemView.getContext());
                        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT
                        );
                        row.setLayoutParams(layoutParams);

                        TextView product = new TextView(holder.itemView.getContext());
                        product.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                        product.setText(data.getProduct());

                        TextView price = new TextView(holder.itemView.getContext());
                        price.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                        price.setText(data.getPrice());

                        row.addView(product);
                        row.addView(price);

                        holder.tableLayout.addView(row);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

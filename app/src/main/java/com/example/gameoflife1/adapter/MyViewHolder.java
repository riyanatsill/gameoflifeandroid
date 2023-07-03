package com.example.gameoflife1.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameoflife1.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView recImage;
    TextView recTitle, recProduct, recID, recPayment;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recProduct = itemView.findViewById(R.id.recProduct);
        recTitle = itemView.findViewById(R.id.recTitle);
        recID = itemView.findViewById(R.id.recId);
        recPayment = itemView.findViewById(R.id.recPayment);
    }
}

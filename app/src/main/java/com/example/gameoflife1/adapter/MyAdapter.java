package com.example.gameoflife1.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gameoflife1.HistoryDetails;
import com.example.gameoflife1.MlActivity;
import com.example.gameoflife1.R;
import com.example.gameoflife1.controller.TransactionController;
import com.example.gameoflife1.model.GenshinModel;
import com.example.gameoflife1.model.MlModel;
import com.example.gameoflife1.model.ProductModel;
import com.example.gameoflife1.model.PubgmModel;
import com.example.gameoflife1.model.TransactionModel;
import com.example.gameoflife1.model.ValorantModel;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<ValorantModel> valorantList;
    private List<GenshinModel> genshinList;
    private List<MlModel> mlList;
    private List<PubgmModel> pubgmList;
    private TransactionController transactionController;
    private TransactionModel model;

    private List<TransactionModel> transactionModelList;
    private List<ProductModel> productModelList;

    public MyAdapter(Context context, List<ValorantModel> valorantList, List<GenshinModel> genshinList,
                     List<MlModel> mlList, List<PubgmModel> pubgmList, TransactionModel model,
                     List<TransactionModel> transactionModelList, List<ProductModel> productModelList) {
        this.context = context;
        this.valorantList = valorantList;
        this.genshinList = genshinList;
        this.mlList = mlList;
        this.pubgmList = pubgmList;
        this.model = model;
        this.transactionModelList = transactionModelList;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        transactionController = new TransactionController();
        Log.d("adapter", "adapter running");
        transactionController.setholder(position, transactionModelList, model, holder.recTitle, holder.recID,
                        holder.recPayment, holder.recProduct, holder.recImage, productModelList);

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the clicked transaction model
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    TransactionModel clickedTransaction = transactionModelList.get(clickedPosition);

                    // Pass the clicked transaction model to the detail activity or perform any desired action
                    Intent intent = new Intent(context, HistoryDetails.class);
                    intent.putExtra("username", transactionModelList.get(holder.getAdapterPosition()).getUsername());
                    intent.putExtra("id", transactionModelList.get(holder.getAdapterPosition()).getId());
                    intent.putExtra("game", transactionModelList.get(holder.getAdapterPosition()).getGame());
                    intent.putExtra("product", transactionModelList.get(holder.getAdapterPosition()).getProduct());
                    intent.putExtra("payment", transactionModelList.get(holder.getAdapterPosition()).getPayment());
                    intent.putExtra("status", transactionModelList.get(holder.getAdapterPosition()).getStatus());
                    intent.putExtra("transaction", clickedTransaction);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionModelList.size();
    }
}

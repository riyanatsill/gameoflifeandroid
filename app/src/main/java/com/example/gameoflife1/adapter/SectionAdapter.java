package com.example.gameoflife1.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameoflife1.R;
import com.example.gameoflife1.controller.GamesController;
import com.example.gameoflife1.controller.ProductController;
import com.example.gameoflife1.model.GamesModel;
import com.example.gameoflife1.model.ProductModel;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    private List<GamesModel> gamesList;
    private ProductController productController;
    private GamesController gamesController;

    public SectionAdapter(List<GamesModel> gamesList) {
        this.gamesList = gamesList;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        gamesController = new GamesController();
        GamesModel dataProduct = gamesList.get(position);
//        holder.titleTextView.setText(dataProduct.getTitle());
//        holder.imageView.setImageResource(dataProduct.getImageResId());
        gamesController.setTitleSection(position,holder.titleTextView, holder.imageView, holder.imageView.getContext());

        holder.tableLayout.removeAllViews();
        Log.d("size", String.valueOf(gamesList.size()));
        holder.tableLayout.removeAllViews();

        // Add table rows dynamically
        TableRow row = new TableRow(holder.itemView.getContext());

        TextView productTextView = new TextView(holder.itemView.getContext());
        productTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

        TextView priceTextView = new TextView(holder.itemView.getContext());
        priceTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


        productController = new ProductController();
        productController.setTextView(position, productTextView, priceTextView, row, holder);
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        public TableLayout tableLayout;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.imageView);
            tableLayout = itemView.findViewById(R.id.tableLayout);
        }
    }
}


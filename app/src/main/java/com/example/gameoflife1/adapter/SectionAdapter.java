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

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_LINEAR = 0;
    private static final int VIEW_TYPE_FOOTER = 1;

    private List<GamesModel> gamesList;
    private ProductController productController;
    private GamesController gamesController;

    public SectionAdapter(List<GamesModel> gamesList) {
        this.gamesList = gamesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_LINEAR) {
            View linearView = inflater.inflate(R.layout.section_layout, parent, false);
            return new LinearViewHolder(linearView);
        } else {
            View footerView = inflater.inflate(R.layout.footer_layout, parent, false);
            return new FooterViewHolder(footerView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LinearViewHolder) {
            LinearViewHolder linearViewHolder = (LinearViewHolder) holder;
            GamesModel dataProduct = gamesList.get(position);
            gamesController = new GamesController();
            gamesController.setTitleSection(position, linearViewHolder.titleTextView, linearViewHolder.imageView, linearViewHolder.imageView.getContext());

            linearViewHolder.tableLayout.removeAllViews();
            linearViewHolder.tableLayout.removeAllViews();

            // Add table rows dynamically
            TableRow row = new TableRow(linearViewHolder.itemView.getContext());

            TextView productTextView = new TextView(linearViewHolder.itemView.getContext());
            productTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView priceTextView = new TextView(linearViewHolder.itemView.getContext());
            priceTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            productController = new ProductController();
            productController.setTextView(position, productTextView, priceTextView, row, linearViewHolder);
        } else if (holder instanceof FooterViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        // Add 1 to the total item count to account for the footer view
        return gamesList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < gamesList.size()) {
            return VIEW_TYPE_LINEAR;
        } else {
            return VIEW_TYPE_FOOTER;
        }
    }

    public static class LinearViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        public TableLayout tableLayout;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.imageView);
            tableLayout = itemView.findViewById(R.id.tableLayout);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

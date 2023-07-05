package com.example.gameoflife1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetails extends AppCompatActivity {
    TextView username, id, game, product, payment, status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        username = findViewById(R.id.detailUsername);
        id = findViewById(R.id.detailId);
        game = findViewById(R.id.detailGame);
        product = findViewById(R.id.detailProduct);
        payment = findViewById(R.id.detailPayment);
        status = findViewById(R.id.detailStatus);

        username.setText(getIntent().getStringExtra("username"));
        id.setText(getIntent().getStringExtra("id"));
        game.setText(getIntent().getStringExtra("game"));
        product.setText(getIntent().getStringExtra("product"));
        payment.setText(getIntent().getStringExtra("payment"));
        status.setText(getIntent().getStringExtra("status"));
    }
}
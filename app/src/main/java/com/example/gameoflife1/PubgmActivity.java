package com.example.gameoflife1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gameoflife1.controller.ProductController;
import com.example.gameoflife1.model.MlModel;
import com.example.gameoflife1.model.ProductModel;
import com.example.gameoflife1.model.PubgmModel;
import com.example.gameoflife1.model.ValorantModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PubgmActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            startActivity(new Intent(PubgmActivity.this, MainActivity.class));
            return true;
        }else if (itemId == R.id.history) {
            startActivity(new Intent(PubgmActivity.this, HistoryActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    String value;
    String Payment;
    int index = 0;
    int naon = 0;

    DatabaseReference mDatabase;
    ProductModel model;
    private PubgmModel pubgmModel = new PubgmModel();
    private List<ProductModel> productModelList;
    private Context context;
    private ProductController productController;
    
    TextView pProduct2, pProduct3, pProduct4, pProduct5, pProduct6, pProduct7, pProduct8, pProduct9,
            pPrice2, pPrice3, pPrice4, pPrice5, pPrice6, pPrice7, pPrice8, pPrice9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        productController = new ProductController();
        productModelList = new ArrayList<>();
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubgm);

        final CardView card1 = findViewById(R.id.pubgmCard);
        final CardView card2 = findViewById(R.id.pubgmCard2);
        final CardView card3 = findViewById(R.id.pubgmCard3);
        final CardView card4 = findViewById(R.id.pubgmCard4);
        final CardView card5 = findViewById(R.id.pubgmCard5);
        final CardView card6 = findViewById(R.id.pubgmCard6);
        final CardView card7 = findViewById(R.id.pubgmCard7);
        final CardView card8 = findViewById(R.id.pubgmCard8);
        final CardView card9 = findViewById(R.id.bca);
        final CardView card10 = findViewById(R.id.mandiri);
        final CardView card11 = findViewById(R.id.gopay);
        final CardView card12 = findViewById(R.id.dana);
        final CardView card13 = findViewById(R.id.shopeepay);
        final CardView card14 = findViewById(R.id.ovo);

        final EditText pubgmid = findViewById(R.id.p1);
        final EditText email = findViewById(R.id.pemail);

        final Button submit = findViewById(R.id.submit);
        model = new ProductModel();

        pProduct2 = findViewById(R.id.PubgmProduct);
        pProduct3 = findViewById(R.id.PubgmProduct2);
        pProduct4 = findViewById(R.id.PubgmProduct3);
        pProduct5 = findViewById(R.id.PubgmProduct4);
        pProduct6 = findViewById(R.id.PubgmProduct5);
        pProduct7 = findViewById(R.id.PubgmProduct6);
        pProduct8 = findViewById(R.id.PubgmProduct7);
        pProduct9 = findViewById(R.id.PubgmProduct8);

        pPrice2 = findViewById(R.id.PubgmPrice);
        pPrice3 = findViewById(R.id.PubgmPrice2);
        pPrice4 = findViewById(R.id.PubgmPrice3);
        pPrice5 = findViewById(R.id.PubgmPrice4);
        pPrice6 = findViewById(R.id.PubgmPrice5);
        pPrice7 = findViewById(R.id.PubgmPrice6);
        pPrice8 = findViewById(R.id.PubgmPrice7);
        pPrice9 = findViewById(R.id.PubgmPrice8);

        for (int i = 0; i < 8; i++) {
            String productID = "PubgmProduct" + (i+2);
            String priceID = "PubgmPrice" + i;

            TextView test = findViewById(R.id.PubgmPrice);
            Log.d("test", String.valueOf(test));


            switch (i){
                case 0:
                    productController.setTextValue(productModelList, "2", pProduct2, i, pPrice2);
                    break;
                case 1:
                    productController.setTextValue(productModelList, "2", pProduct3, i, pPrice3);
                    break;
                case 2:
                    productController.setTextValue(productModelList, "2", pProduct4, i, pPrice4);
                    break;
                case 3:
                    productController.setTextValue(productModelList, "2", pProduct5, i, pPrice5);
                    break;
                case 4:
                    productController.setTextValue(productModelList, "2", pProduct6, i, pPrice6);
                    break;
                case 5:
                    productController.setTextValue(productModelList, "2", pProduct7, i, pPrice7);
                    break;
                case 6:
                    productController.setTextValue(productModelList, "2", pProduct8, i, pPrice8);
                    break;
                case 7:
                    productController.setTextValue(productModelList, "2", pProduct9, i, pPrice9);
                    break;
            }

//            productController.setTextValue(productModelList, "1", productTextView, priceTextView, i);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Product");
                String id = pubgmid.getText().toString().trim();
                String id3 = email.getText().toString().trim();
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data:snapshot.getChildren()){
                            if (data.child("product").getValue(String.class).equals(value)){
                                model.setPrice(data.child("price").getValue(String.class));
                                model.setGame_id(data.child("game_id").getValue(String.class));
                                model.setProduct(data.child("product").getValue(String.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                String game = "PUBG Mobile";
                pubgmModel.setEmail(id3);
                pubgmModel.setGame("PUBG Mobile");
                pubgmModel.setProduct(value);
                pubgmModel.setId(id);
                pubgmModel.setPayment(Payment);
                pubgmModel.setUsername(getIntent().getStringExtra("username"));
                Intent intent = new Intent(PubgmActivity.this, Details.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                intent.putExtra("pubgmModel", pubgmModel);
                intent.putExtra("game", game);
                startActivity(intent);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                card1.setCardBackgroundColor(getColor(R.color.bg2));
                value = "100 UC";

                card2.setCardBackgroundColor(getColor(R.color.bg));
                card3.setCardBackgroundColor(getColor(R.color.bg));
                card4.setCardBackgroundColor(getColor(R.color.bg));
                card5.setCardBackgroundColor(getColor(R.color.bg));
                card6.setCardBackgroundColor(getColor(R.color.bg));
                card7.setCardBackgroundColor(getColor(R.color.bg));
                card8.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 2;
                card2.setCardBackgroundColor(getColor(R.color.bg2));
                value = "250 UC";

                card1.setCardBackgroundColor(getColor(R.color.bg));
                card3.setCardBackgroundColor(getColor(R.color.bg));
                card4.setCardBackgroundColor(getColor(R.color.bg));
                card5.setCardBackgroundColor(getColor(R.color.bg));
                card6.setCardBackgroundColor(getColor(R.color.bg));
                card7.setCardBackgroundColor(getColor(R.color.bg));
                card8.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 3;
                card3.setCardBackgroundColor(getColor(R.color.bg2));
                value = "500 UC";

                card1.setCardBackgroundColor(getColor(R.color.bg));
                card2.setCardBackgroundColor(getColor(R.color.bg));
                card4.setCardBackgroundColor(getColor(R.color.bg));
                card5.setCardBackgroundColor(getColor(R.color.bg));
                card6.setCardBackgroundColor(getColor(R.color.bg));
                card7.setCardBackgroundColor(getColor(R.color.bg));
                card8.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 4;
                card4.setCardBackgroundColor(getColor(R.color.bg2));
                value = "780 UC";

                card1.setCardBackgroundColor(getColor(R.color.bg));
                card2.setCardBackgroundColor(getColor(R.color.bg));
                card3.setCardBackgroundColor(getColor(R.color.bg));
                card5.setCardBackgroundColor(getColor(R.color.bg));
                card6.setCardBackgroundColor(getColor(R.color.bg));
                card7.setCardBackgroundColor(getColor(R.color.bg));
                card8.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 5;
                card5.setCardBackgroundColor(getColor(R.color.bg2));
                value = "1500 UC";

                card1.setCardBackgroundColor(getColor(R.color.bg));
                card2.setCardBackgroundColor(getColor(R.color.bg));
                card3.setCardBackgroundColor(getColor(R.color.bg));
                card4.setCardBackgroundColor(getColor(R.color.bg));
                card6.setCardBackgroundColor(getColor(R.color.bg));
                card7.setCardBackgroundColor(getColor(R.color.bg));
                card8.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 6;
                card6.setCardBackgroundColor(getColor(R.color.bg2));
                value = "2100 UC";

                card1.setCardBackgroundColor(getColor(R.color.bg));
                card2.setCardBackgroundColor(getColor(R.color.bg));
                card3.setCardBackgroundColor(getColor(R.color.bg));
                card4.setCardBackgroundColor(getColor(R.color.bg));
                card5.setCardBackgroundColor(getColor(R.color.bg));
                card7.setCardBackgroundColor(getColor(R.color.bg));
                card8.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 7;
                card7.setCardBackgroundColor(getColor(R.color.bg2));
                value = "2700 UC";

                card1.setCardBackgroundColor(getColor(R.color.bg));
                card2.setCardBackgroundColor(getColor(R.color.bg));
                card3.setCardBackgroundColor(getColor(R.color.bg));
                card4.setCardBackgroundColor(getColor(R.color.bg));
                card5.setCardBackgroundColor(getColor(R.color.bg));
                card6.setCardBackgroundColor(getColor(R.color.bg));
                card8.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 8;
                card8.setCardBackgroundColor(getColor(R.color.bg2));
                value = "3500 UC";

                card1.setCardBackgroundColor(getColor(R.color.bg));
                card2.setCardBackgroundColor(getColor(R.color.bg));
                card3.setCardBackgroundColor(getColor(R.color.bg));
                card4.setCardBackgroundColor(getColor(R.color.bg));
                card5.setCardBackgroundColor(getColor(R.color.bg));
                card6.setCardBackgroundColor(getColor(R.color.bg));
                card7.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naon = 1;
                card9.setCardBackgroundColor(getColor(R.color.bg2));
                Payment = "BCA";

                card10.setCardBackgroundColor(getColor(R.color.bg));
                card11.setCardBackgroundColor(getColor(R.color.bg));
                card12.setCardBackgroundColor(getColor(R.color.bg));
                card13.setCardBackgroundColor(getColor(R.color.bg));
                card14.setCardBackgroundColor(getColor(R.color.bg));
            }
        });
        card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naon = 2;
                card10.setCardBackgroundColor(getColor(R.color.bg2));
                Payment = "MANDIRI";

                card9.setCardBackgroundColor(getColor(R.color.bg));
                card11.setCardBackgroundColor(getColor(R.color.bg));
                card12.setCardBackgroundColor(getColor(R.color.bg));
                card13.setCardBackgroundColor(getColor(R.color.bg));
                card14.setCardBackgroundColor(getColor(R.color.bg));
            }
        });
        card11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naon = 3;
                card11.setCardBackgroundColor(getColor(R.color.bg2));
                Payment = "GOPAY";

                card9.setCardBackgroundColor(getColor(R.color.bg));
                card10.setCardBackgroundColor(getColor(R.color.bg));
                card12.setCardBackgroundColor(getColor(R.color.bg));
                card13.setCardBackgroundColor(getColor(R.color.bg));
                card14.setCardBackgroundColor(getColor(R.color.bg));
            }
        });
        card12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naon = 4;
                card12.setCardBackgroundColor(getColor(R.color.bg2));
                Payment = "DANA";

                card9.setCardBackgroundColor(getColor(R.color.bg));
                card10.setCardBackgroundColor(getColor(R.color.bg));
                card11.setCardBackgroundColor(getColor(R.color.bg));
                card13.setCardBackgroundColor(getColor(R.color.bg));
                card14.setCardBackgroundColor(getColor(R.color.bg));
            }
        });
        card13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naon = 5;
                card13.setCardBackgroundColor(getColor(R.color.bg2));
                Payment = "SHOPEEPAY";

                card9.setCardBackgroundColor(getColor(R.color.bg));
                card10.setCardBackgroundColor(getColor(R.color.bg));
                card11.setCardBackgroundColor(getColor(R.color.bg));
                card12.setCardBackgroundColor(getColor(R.color.bg));
                card14.setCardBackgroundColor(getColor(R.color.bg));
            }
        });
        card14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naon = 6;
                card14.setCardBackgroundColor(getColor(R.color.bg2));
                Payment = "OVO";

                card9.setCardBackgroundColor(getColor(R.color.bg));
                card10.setCardBackgroundColor(getColor(R.color.bg));
                card11.setCardBackgroundColor(getColor(R.color.bg));
                card12.setCardBackgroundColor(getColor(R.color.bg));
                card13.setCardBackgroundColor(getColor(R.color.bg));
            }
        });

    }
}
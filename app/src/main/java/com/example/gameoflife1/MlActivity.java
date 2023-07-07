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
import android.widget.Toast;

import com.example.gameoflife1.controller.ProductController;
import com.example.gameoflife1.model.MlModel;
import com.example.gameoflife1.model.ProductModel;
import com.example.gameoflife1.model.ValorantModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MlActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            Intent intent = new Intent(MlActivity.this, MainActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
            return true;
        }else if (itemId == R.id.history) {
            Intent intent = new Intent(MlActivity.this, HistoryActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
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
    private MlModel mlModel = new MlModel();

    private List<ProductModel> productModelList;
    private Context context;
    private ProductController productController;

    TextView mProduct2, mProduct3, mProduct4, mProduct5, mProduct6, mProduct7, mProduct8, mProduct9,
            mPrice2, mPrice3, mPrice4, mPrice5, mPrice6, mPrice7, mPrice8, mPrice9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        productController = new ProductController();
        productModelList = new ArrayList<>();
        context = this;
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml);

        final CardView card1 = findViewById(R.id.mlCard);
        final CardView card2 = findViewById(R.id.mlCard2);
        final CardView card3 = findViewById(R.id.mlCard3);
        final CardView card4 = findViewById(R.id.mlCard4);
        final CardView card5 = findViewById(R.id.mlCard5);
        final CardView card6 = findViewById(R.id.mlCard6);
        final CardView card7 = findViewById(R.id.mlCard7);
        final CardView card8 = findViewById(R.id.mlCard8);
        final CardView card9 = findViewById(R.id.bca);
        final CardView card10 = findViewById(R.id.mandiri);
        final CardView card11 = findViewById(R.id.gopay);
        final CardView card12 = findViewById(R.id.dana);
        final CardView card13 = findViewById(R.id.shopeepay);
        final CardView card14 = findViewById(R.id.ovo);

        final EditText mlid = findViewById(R.id.mlid);
        final EditText zone = findViewById(R.id.mlzone);

        final Button submit = findViewById(R.id.submit);
        model = new ProductModel();

        mProduct2 = findViewById(R.id.MlProduct);
        mProduct3 = findViewById(R.id.MlProduct2);
        mProduct4 = findViewById(R.id.MlProduct3);
        mProduct5 = findViewById(R.id.MlProduct4);
        mProduct6 = findViewById(R.id.MlProduct5);
        mProduct7 = findViewById(R.id.MlProduct6);
        mProduct8 = findViewById(R.id.MlProduct7);
        mProduct9 = findViewById(R.id.MlProduct8);

        mPrice2 = findViewById(R.id.MlPrice);
        mPrice3 = findViewById(R.id.MlPrice2);
        mPrice4 = findViewById(R.id.MlPrice3);
        mPrice5 = findViewById(R.id.MlPrice4);
        mPrice6 = findViewById(R.id.MlPrice5);
        mPrice7 = findViewById(R.id.MlPrice6);
        mPrice8 = findViewById(R.id.MlPrice7);
        mPrice9 = findViewById(R.id.MlPrice8);

        for (int i = 0; i < 8; i++) {
            String productID = "MlProduct" + (i+2);
            String priceID = "MlPrice" + i;

            TextView test = findViewById(R.id.MlPrice);
            Log.d("test", String.valueOf(test));


            switch (i){
                case 0:
                    productController.setTextValue(productModelList, "3", mProduct2, i, mPrice2);
                    break;
                case 1:
                    productController.setTextValue(productModelList, "3", mProduct3, i, mPrice3);
                    break;
                case 2:
                    productController.setTextValue(productModelList, "3", mProduct4, i, mPrice4);
                    break;
                case 3:
                    productController.setTextValue(productModelList, "3", mProduct5, i, mPrice5);
                    break;
                case 4:
                    productController.setTextValue(productModelList, "3", mProduct6, i, mPrice6);
                    break;
                case 5:
                    productController.setTextValue(productModelList, "3", mProduct7, i, mPrice7);
                    break;
                case 6:
                    productController.setTextValue(productModelList, "3", mProduct8, i, mPrice8);
                    break;
                case 7:
                    productController.setTextValue(productModelList, "3", mProduct9, i, mPrice9);
                    break;
            }

//            productController.setTextValue(productModelList, "1", productTextView, priceTextView, i);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Product");
                String id = mlid.getText().toString().trim();
                String id2 = zone.getText().toString().trim();
                boolean isValid = true;

                if (id.isEmpty()) {
                    Toast.makeText(MlActivity.this, "Please enter the ID", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (id.length() == 6) {
                    Toast.makeText(MlActivity.this, "Min 7 Character", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (id2.isEmpty()) {
                    Toast.makeText(MlActivity.this, "Please enter the Zone", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (id2.length() == 3 || id2.length() >= 5) {
                    Toast.makeText(MlActivity.this, "Zone must 4 Character (exp: 2202)", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (value == null) {
                    Toast.makeText(MlActivity.this, "Please Choose the Product", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (Payment == null) {
                    Toast.makeText(MlActivity.this, "Please Choose the Payment", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                if (isValid){
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
                    String game = "Mobile Legend";
                    mlModel.setStatus("Belum Selesai");
                    mlModel.setGame("Mobile Legend");
                    mlModel.setProduct(value);
                    mlModel.setZone(id2);
                    mlModel.setId(id);
                    mlModel.setPayment(Payment);
                    mlModel.setUsername(getIntent().getStringExtra("username"));
                    Intent intent = new Intent(MlActivity.this, Details.class);
                    intent.putExtra("username",mlModel.getUsername());
                    intent.putExtra("mlModel", mlModel);
                    intent.putExtra("game", game);
                    startActivity(intent);
                }
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                card1.setCardBackgroundColor(getColor(R.color.bg2));
                value = "250 Diamond";

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
                value = "400 Diamond";

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
                value = "600 Diamond";

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
                value = "780 Diamond";

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
                value = "1500 Diamond";

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
                value = "2100 Diamond";

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
                value = "2700 Diamond";

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
                value = "3500 Diamond";

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

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon2);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
    public void onBackPressed() {
        Intent intent = new Intent(MlActivity.this, MainActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}
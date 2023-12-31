package com.example.gameoflife1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
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
import com.example.gameoflife1.model.ProductModel;
import com.example.gameoflife1.model.ValorantModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ValorantActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            Intent intent = new Intent(ValorantActivity.this, MainActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
            return true;
        }else if (itemId == R.id.history) {
            Intent intent = new Intent(ValorantActivity.this, HistoryActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    String value,Payment;
    int index = 0;
    int naon = 0;
    DatabaseReference mDatabase;
    ProductModel model;

    private ValorantModel valorantModel = new ValorantModel();
    private List<ProductModel> productModelList;
    private Context context;
    private ProductController productController;
    TextView vProduct2, vProduct3, vProduct4, vProduct5, vProduct6, vProduct7, vProduct8, vProduct9,
            vPrice2, vPrice3, vPrice4, vPrice5, vPrice6, vPrice7, vPrice8, vPrice9;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valorant);

        productController = new ProductController();
        productModelList = new ArrayList<>();
        context = this;
        Log.d("valorant", "start");

        final CardView card1 = findViewById(R.id.valorantCard);
        final CardView card2 = findViewById(R.id.valorantCard2);
        final CardView card3 = findViewById(R.id.valorantCard3);
        final CardView card4 = findViewById(R.id.valorantCard4);
        final CardView card5 = findViewById(R.id.valorantCard5);
        final CardView card6 = findViewById(R.id.valorantCard6);
        final CardView card7 = findViewById(R.id.valorantCard7);
        final CardView card8 = findViewById(R.id.valorantCard8);
        final CardView card9 = findViewById(R.id.bca);
        final CardView card10 = findViewById(R.id.mandiri);
        final CardView card11 = findViewById(R.id.gopay);
        final CardView card12 = findViewById(R.id.dana);
        final CardView card13 = findViewById(R.id.shopeepay);
        final CardView card14 = findViewById(R.id.ovo);
        final EditText riotId = (EditText) findViewById(R.id.valo1);
        final EditText tagline = (EditText) findViewById(R.id.valo2);

        vProduct2 = findViewById(R.id.ValorantProduct2);
        vProduct3 = findViewById(R.id.ValorantProduct3);
        vProduct4 = findViewById(R.id.ValorantProduct4);
        vProduct5 = findViewById(R.id.ValorantProduct5);
        vProduct6 = findViewById(R.id.ValorantProduct6);
        vProduct7 = findViewById(R.id.ValorantProduct7);
        vProduct8 = findViewById(R.id.ValorantProduct8);
        vProduct9 = findViewById(R.id.ValorantProduct9);

        vPrice2 = findViewById(R.id.valorantPrice);
        vPrice3 = findViewById(R.id.valorantPrice2);
        vPrice4 = findViewById(R.id.valorantPrice3);
        vPrice5 = findViewById(R.id.valorantPrice4);
        vPrice6 = findViewById(R.id.valorantPrice5);
        vPrice7 = findViewById(R.id.valorantPrice6);
        vPrice8 = findViewById(R.id.valorantPrice7);
        vPrice9 = findViewById(R.id.valorantPrice8);

        final Button submit = findViewById(R.id.submit);
        model = new ProductModel();

        for (int i = 0; i < 8; i++) {
            String productID = "valorantProduct" + (i+2);
            String priceID = "valorantPrice" + i;

            TextView test = findViewById(R.id.valorantPrice);
            Log.d("test", String.valueOf(test));


            switch (i){
                case 0:
                    productController.setTextValue(productModelList, "1", vProduct2, i, vPrice2);
                    break;
                case 1:
                    productController.setTextValue(productModelList, "1", vProduct3, i, vPrice3);
                    break;
                case 2:
                    productController.setTextValue(productModelList, "1", vProduct4, i, vPrice4);
                    break;
                case 3:
                    productController.setTextValue(productModelList, "1", vProduct5, i, vPrice5);
                    break;
                case 4:
                    productController.setTextValue(productModelList, "1", vProduct6, i, vPrice6);
                    break;
                case 5:
                    productController.setTextValue(productModelList, "1", vProduct7, i, vPrice7);
                    break;
                case 6:
                    productController.setTextValue(productModelList, "1", vProduct8, i, vPrice8);
                    break;
                case 7:
                    productController.setTextValue(productModelList, "1", vProduct9, i, vPrice9);
                    break;
            }
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Product");
                String id = riotId.getText().toString().trim();
                String id2 = tagline.getText().toString().trim();
                boolean isValid = true;

                if (id.isEmpty()) {
                    Toast.makeText(ValorantActivity.this, "Please enter the Riot ID", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (id.length() == 3) {
                    Toast.makeText(ValorantActivity.this, "Min 4 Character", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (id2.isEmpty()) {
                    Toast.makeText(ValorantActivity.this, "Please enter the Tagline", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (id2.length() == 3) {
                    Toast.makeText(ValorantActivity.this, "Min 4 Character", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (value == null) {
                    Toast.makeText(ValorantActivity.this, "Please Choose the Product", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (Payment == null) {
                    Toast.makeText(ValorantActivity.this, "Please Choose the Payment", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                if (isValid) {
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot data : snapshot.getChildren()) {
                                if (data.child("product").getValue(String.class).equals(value)) {
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

                    String game = "Valorant";
                    valorantModel.setStatus("Belum Selesai");
                    valorantModel.setGame("Valorant");
                    valorantModel.setProduct(value);
                    valorantModel.setTagline(id2);
                    valorantModel.setId(id);
                    valorantModel.setPayment(Payment);
                    valorantModel.setUsername(getIntent().getStringExtra("username"));
                    Intent intent = new Intent(ValorantActivity.this, Details.class);
                    intent.putExtra("valorantModel", valorantModel);
                    intent.putExtra("username", valorantModel.getUsername());
                    intent.putExtra("game", game);
                    Log.d("status", valorantModel.getStatus());
                    startActivity(intent);
                }

            }

        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                card1.setCardBackgroundColor(getColor(R.color.bg2));
                value = "100 Valorant Point";

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
                value = "250 Valorant Point";

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
                value = "500 Valorant Point";

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
                value = "780 Valorant Point";

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
                value = "1500 Valorant Point";

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
                value = "2100 Valorant Point";

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
                value = "2700 Valorant Point";

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
                value = "3500 Valorant Point";

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
        Intent intent = new Intent(ValorantActivity.this, MainActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}
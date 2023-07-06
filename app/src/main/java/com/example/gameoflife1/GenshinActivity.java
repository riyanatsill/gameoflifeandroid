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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameoflife1.controller.ProductController;
import com.example.gameoflife1.model.GenshinModel;
import com.example.gameoflife1.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class GenshinActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            Intent intent = new Intent(GenshinActivity.this, MainActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
            return true;
        }else if (itemId == R.id.history) {
            Intent intent = new Intent(GenshinActivity.this, HistoryActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    String value;
    String pvalue;
    int index = 0;
    int naon = 0;

    String[] items = {"Asia", "Europe", "America", "Australia"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    
    private List<ProductModel> productModelList;
    private Context context;
    private ProductController productController;

    TextView gProduct2, gProduct3, gProduct4, gProduct5, gProduct6, gProduct7, gProduct8, gProduct9,
            gPrice2, gPrice3, gPrice4, gPrice5, gPrice6, gPrice7, gPrice8, gPrice9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        productController = new ProductController();
        productModelList = new ArrayList<>();
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genshin);

        final CardView card1 = findViewById(R.id.genshinCard);
        final CardView card2 = findViewById(R.id.genshinCard2);
        final CardView card3 = findViewById(R.id.genshinCard3);
        final CardView card4 = findViewById(R.id.genshinCard4);
        final CardView card5 = findViewById(R.id.genshinCard5);
        final CardView card6 = findViewById(R.id.genshinCard6);
        final CardView card7 = findViewById(R.id.genshinCard7);
        final CardView card8 = findViewById(R.id.genshinCard8);
        final CardView card9 = findViewById(R.id.bca);
        final CardView card10 = findViewById(R.id.mandiri);
        final CardView card11 = findViewById(R.id.gopay);
        final CardView card12 = findViewById(R.id.dana);
        final CardView card13 = findViewById(R.id.shopeepay);
        final CardView card14 = findViewById(R.id.ovo);
        final EditText id = findViewById(R.id.inputId);

        autoCompleteTxt = findViewById(R.id.auto1);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        final Button submit = findViewById(R.id.submit);
        GenshinModel genshinModel = new GenshinModel();

        gProduct2 = findViewById(R.id.GenshinProduct);
        gProduct3 = findViewById(R.id.GenshinProduct2);
        gProduct4 = findViewById(R.id.GenshinProduct3);
        gProduct5 = findViewById(R.id.GenshinProduct4);
        gProduct6 = findViewById(R.id.GenshinProduct5);
        gProduct7 = findViewById(R.id.GenshinProduct6);
        gProduct8 = findViewById(R.id.GenshinProduct7);
        gProduct9 = findViewById(R.id.GenshinProduct8);

        gPrice2 = findViewById(R.id.GenshinPrice);
        gPrice3 = findViewById(R.id.GenshinPrice2);
        gPrice4 = findViewById(R.id.GenshinPrice3);
        gPrice5 = findViewById(R.id.GenshinPrice4);
        gPrice6 = findViewById(R.id.GenshinPrice5);
        gPrice7 = findViewById(R.id.GenshinPrice6);
        gPrice8 = findViewById(R.id.GenshinPrice7);
        gPrice9 = findViewById(R.id.GenshinPrice8);

        for (int i = 0; i < 8; i++) {
            String productID = "GenshinProduct" + (i+2);
            String priceID = "GenshinPrice" + i;

            TextView test = findViewById(R.id.GenshinPrice);
            Log.d("test", String.valueOf(test));


            switch (i){
                case 0:
                    productController.setTextValue(productModelList, "4", gProduct2, i, gPrice2);
                    break;
                case 1:
                    productController.setTextValue(productModelList, "4", gProduct3, i, gPrice3);
                    break;
                case 2:
                    productController.setTextValue(productModelList, "4", gProduct4, i, gPrice4);
                    break;
                case 3:
                    productController.setTextValue(productModelList, "4", gProduct5, i, gPrice5);
                    break;
                case 4:
                    productController.setTextValue(productModelList, "4", gProduct6, i, gPrice6);
                    break;
                case 5:
                    productController.setTextValue(productModelList, "4", gProduct7, i, gPrice7);
                    break;
                case 6:
                    productController.setTextValue(productModelList, "4", gProduct8, i, gPrice8);
                    break;
                case 7:
                    productController.setTextValue(productModelList, "4", gProduct9, i, gPrice9);
                    break;
            }
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputId = id.getText().toString();
                boolean isValid = true;

                if (inputId.isEmpty()) {
                    Toast.makeText(GenshinActivity.this, "Please enter the Genshin ID", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (inputId.length() == 6) {
                    Toast.makeText(GenshinActivity.this, "Min 7 Character", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (value == null) {
                    Toast.makeText(GenshinActivity.this, "Please Choose the Product", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (pvalue == null) {
                    Toast.makeText(GenshinActivity.this, "Please Choose the Payment", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                if (isValid){
                    String game = "Genshin";
                    genshinModel.setGame("Genshin Impact");
                    genshinModel.setEmail("Belum Selesai");
                    genshinModel.setId(inputId);
                    genshinModel.setProduct(value);
                    genshinModel.setPayment(pvalue);
                    genshinModel.setUsername(getIntent().getStringExtra("username"));
                    Log.d("server", genshinModel.getServer());
                    Log.d("pro", genshinModel.getProduct());
                    Intent intent = new Intent(GenshinActivity.this, Details.class);
                    intent.putExtra("game", game);
                    intent.putExtra("username",genshinModel.getUsername());
                    intent.putExtra("genshinModel", genshinModel);
                    startActivity(intent);
                }

            }
        });

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String item = parent.getItemAtPosition(position).toString();
                    genshinModel.setServer(item);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                card1.setCardBackgroundColor(getColor(R.color.bg2));
                value = "100 Genesis Crystal";

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
                value = "250 Genesis Crystal";

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
                value = "500 Genesis Crystal";

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
                value = "780 Genesis Crystal";

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
                value = "1500 Genesis Crystal";

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
                value = "2100 Genesis Crystal";

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
                value = "2700 Genesis Crystal";

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
                value = "3500 Genesis Crystal";

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
                pvalue = "BCA";

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
                pvalue = "MANDIRI";

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
                pvalue = "GOPAY";

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
                pvalue = "DANA";

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
                pvalue = "SHOPEEPAY";

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
                pvalue = "OVO";

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
        Intent intent = new Intent(GenshinActivity.this, MainActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}
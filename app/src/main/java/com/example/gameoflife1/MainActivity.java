package com.example.gameoflife1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.profiles) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
            return true;
        }else if (itemId == R.id.history) {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
            return true;
        }else if (itemId == R.id.logout) {
            getIntent().removeExtra("email");
            getIntent().removeExtra("username");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    CardView valorant;
    CardView genshin;
    CardView ml;
    CardView pubgm;
    private Button priceList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("email", getIntent().getStringExtra("email"));

        ImageSlider imageSlider = findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.valorant1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.pubgm1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ml2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.gi2, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        valorant = findViewById(R.id.valorantCard);
        genshin = findViewById(R.id.genshinCard);
        ml = findViewById(R.id.mlCard);
        pubgm = findViewById(R.id.pubgmCard);

        priceList = findViewById(R.id.priceList);

        priceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PriceList.class);
                startActivity(intent);
            }
        });

        valorant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ValorantActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        genshin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenshinActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MlActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        pubgm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PubgmActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}
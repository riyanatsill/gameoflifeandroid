package com.example.gameoflife1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CardView valorant;
    CardView genshin;
    CardView ml;
    CardView pubgm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        valorant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ValorantActivity.class));
            }
        });
        genshin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GenshinActivity.class));
            }
        });
        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MlActivity.class));
            }
        });
        pubgm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PubgmActivity.class));
            }
        });
    }
}
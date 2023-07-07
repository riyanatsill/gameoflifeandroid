package com.example.gameoflife1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gameoflife1.controller.ProductController;
import com.example.gameoflife1.controller.SendEmail;
import com.example.gameoflife1.model.GenshinModel;
import com.example.gameoflife1.model.MlModel;
import com.example.gameoflife1.model.ProductModel;
import com.example.gameoflife1.model.PubgmModel;
import com.example.gameoflife1.model.TransactionModel;
import com.example.gameoflife1.model.ValorantModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Details extends AppCompatActivity {

    private ValorantModel valorantModel = new ValorantModel();
    private ProductModel Model = new ProductModel();
    private ProductController controller = new ProductController();
    private TransactionModel transactionModel = new TransactionModel();
    DatabaseReference mDatabase, pDatatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final EditText id = findViewById(R.id.ke1);
        final EditText tagline = findViewById(R.id.ke2);
        final EditText product = findViewById(R.id.ke4);
        final EditText payment = findViewById(R.id.ke5);
        final EditText price = findViewById(R.id.ke6);

        final TextInputLayout id2 = findViewById(R.id.outlinedTextField1);
        final TextInputLayout tagline2 = findViewById(R.id.outlinedTextField2);
        final TextInputLayout product2 = findViewById(R.id.outlinedTextField4);
        final TextInputLayout payment2 = findViewById(R.id.outlinedTextField5);
        final TextInputLayout price2 = findViewById(R.id.outlinedTextField6);

        final Button submit = findViewById(R.id.submit1);

        String game = getIntent().getStringExtra("game");
        switch (game){
            case "Valorant":
                valorant(id, tagline, product, payment, price, id2, tagline2, product2, payment2, price2, submit);
                break;
            case "Genshin" :
                genshin(id, tagline, product, payment, price, id2, tagline2, product2, payment2, price2, submit);
                break;
            case "Mobile Legend" :
                ml(id, tagline, product, payment, price, id2, tagline2, product2, payment2, price2, submit);
                break;
            case "PUBG Mobile" :
                pubgm(id, tagline, product, payment, id2, tagline2, product2, payment2, submit);
                break;
        }
    }

    public void valorant(EditText id, EditText tagline, EditText product, EditText payment, EditText price,
                         TextInputLayout id2, TextInputLayout tagline2, TextInputLayout product2,
                         TextInputLayout payment2, TextInputLayout price2, Button submit){
        ValorantModel valorantModel = (ValorantModel) getIntent().getSerializableExtra("valorantModel");
        pDatatabase = FirebaseDatabase.getInstance().getReference("Product");
        pDatatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("function", "sebelum loop");
                for(DataSnapshot data: snapshot.getChildren()){
                    if(data.child("product").getValue(String.class).equals(valorantModel.getProduct())){
                        Log.d("valorant", data.child("price").getValue().toString());
                        price.setText(data.child("price").getValue().toString());
                        price2.setHint("Price");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        id.setText(valorantModel.getId());
        id2.setHint("ID");
        tagline.setText(valorantModel.getTagline());
        tagline2.setHint("Tagline");
        product.setText(valorantModel.getProduct());
        product2.setHint("Product");
        Log.d("product", valorantModel.getProduct());
        payment.setText(valorantModel.getPayment());
        payment2.setHint("Payment");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Transaction");
                String key = mDatabase.push().getKey();
                transactionModel.setKey(key);
                Log.d("STATUS", valorantModel.getStatus());
                SendEmail.sendPasswordEmail(valorantModel.getUsername(), valorantModel);
                mDatabase.child(key).setValue(valorantModel);
                Intent intent = new Intent(Details.this, MainActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                Toast.makeText(Details.this, "Orders Valorant Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void genshin(EditText id, EditText tagline, EditText product, EditText payment, EditText price,
                        TextInputLayout id2, TextInputLayout tagline2, TextInputLayout product2,
                        TextInputLayout payment2, TextInputLayout price2, Button submit){
        GenshinModel genshinModel = (GenshinModel) getIntent().getSerializableExtra("genshinModel");
        pDatatabase = FirebaseDatabase.getInstance().getReference("Product");
        pDatatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    if(data.child("product").getValue(String.class).equals(genshinModel.getProduct())){
                        Log.d("valorant", data.child("price").getValue().toString());
                        price.setText(data.child("price").getValue().toString());
                        price2.setHint("Price");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        id.setText(genshinModel.getId());
        id2.setHint("ID");
        tagline.setText(genshinModel.getServer());
        tagline2.setHint("Server");
        product.setText(genshinModel.getProduct());
        product2.setHint("Product");
        Log.d("product", genshinModel.getProduct());
        payment.setText(genshinModel.getPayment());
        payment2.setHint("Payment");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Transaction");
                String key = mDatabase.push().getKey();
                transactionModel.setKey(key);
                SendEmail.sendPasswordEmail(genshinModel.getUsername(), genshinModel);
                mDatabase.child(key).setValue(genshinModel);
                Intent intent = new Intent(Details.this, MainActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                Log.d("username", getIntent().getStringExtra("username"));
                Toast.makeText(Details.this, "Orders Genshin Impact Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ml(EditText id, EditText tagline, EditText product, EditText payment, EditText price,
                        TextInputLayout id2, TextInputLayout tagline2, TextInputLayout product2,
                        TextInputLayout payment2, TextInputLayout price2, Button submit){
        MlModel mlModel = (MlModel) getIntent().getSerializableExtra("mlModel");
        pDatatabase = FirebaseDatabase.getInstance().getReference("Product");
        pDatatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    if(data.child("product").getValue(String.class).equals(mlModel.getProduct())){
                        price.setText(data.child("price").getValue().toString());
                        price2.setHint("Price");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        id.setText(mlModel.getId());
        id2.setHint("ID");
        tagline.setText(mlModel.getZone());
        tagline2.setHint("Zone");
        product.setText(mlModel.getProduct());
        product2.setHint("Product");
        Log.d("product", mlModel.getProduct());
        payment.setText(mlModel.getPayment());
        payment2.setHint("Payment");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Transaction");
                String key = mDatabase.push().getKey();
                transactionModel.setKey(key);
                SendEmail.sendPasswordEmail(mlModel.getUsername(), mlModel);
                mDatabase.child(key).setValue(mlModel);
                Intent intent = new Intent(Details.this, MainActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                Toast.makeText(Details.this, "Orders Mobile Legend Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void pubgm(EditText id, EditText tagline, EditText product, EditText payment,
                      TextInputLayout id2, TextInputLayout tagline2, TextInputLayout product2,
                      TextInputLayout payment2, Button submit){
        PubgmModel pubgmModel = (PubgmModel) getIntent().getSerializableExtra("pubgmModel");
        pDatatabase = FirebaseDatabase.getInstance().getReference("Product");
        pDatatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    if(data.child("product").getValue(String.class).equals(pubgmModel.getProduct())){
                        payment.setText(data.child("price").getValue().toString());
                        Log.d("price", data.child("price").getValue().toString());
                        payment2.setHint("Price");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        id.setText(pubgmModel.getId());
        id2.setHint("ID");
        tagline.setText(pubgmModel.getProduct());
        tagline2.setHint("Product");
        Log.d("product", pubgmModel.getProduct());
        product.setText(pubgmModel.getPayment());
        product2.setHint("Payment");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Transaction");
                String key = mDatabase.push().getKey();
                transactionModel.setKey(key);
                SendEmail.sendPasswordEmail(pubgmModel.getUsername(), pubgmModel);
                mDatabase.child(key).setValue(pubgmModel);
                Intent intent = new Intent(Details.this, MainActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                Toast.makeText(Details.this, "Orders PUBG Mobile Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

}
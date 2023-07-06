package com.example.gameoflife1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HistoryDetails extends AppCompatActivity {
    TextView username, id, game, product, payment, status;
    Button submit, delete;
    private String key;
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
        submit = findViewById(R.id.selesai);
        delete = findViewById(R.id.hapus);

        key = getIntent().getStringExtra("key");

        username.setText(getIntent().getStringExtra("username"));
        id.setText(getIntent().getStringExtra("id"));
        game.setText(getIntent().getStringExtra("game"));
        product.setText(getIntent().getStringExtra("product"));
        payment.setText(getIntent().getStringExtra("payment"));
        status.setText(getIntent().getStringExtra("status"));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the new status value
                String newStatus = "Selesai"; // Replace "New Status" with your desired new status value
                String username = getIntent().getStringExtra("username");

                // Update the status value in the Firebase database
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Transaction");

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Log.d("key", key);
                                if(snapshot.getKey().equals(key)){
                                    status.setText(newStatus);
                                    mDatabase.child(key).child("email").setValue(newStatus).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Intent intent = new Intent(HistoryDetails.this, HistoryActivity.class);
                                            intent.putExtra("username", username);
                                            Toast.makeText(HistoryDetails.this, "pembelian selesai", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }
                        }else{
                            System.out.println("error");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle the cancellation case
                        Toast.makeText(HistoryDetails.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getIntent().getStringExtra("username");

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Transaction");

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.getKey().equals(key)) {
                                    // Delete the transaction from the database
                                    snapshot.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Intent intent = new Intent(HistoryDetails.this, HistoryActivity.class);
                                            intent.putExtra("username", username);
                                            Toast.makeText(HistoryDetails.this, "Pembelian dihapus", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }
                        } else {
                            System.out.println("error");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle the cancellation case
                        Toast.makeText(HistoryDetails.this, "Database error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon2);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}
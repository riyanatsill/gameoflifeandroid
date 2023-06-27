package com.example.gameoflife1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText reg_username = (EditText) findViewById(R.id.username);
        final EditText reg_password = (EditText) findViewById(R.id.pass);
        final EditText reg_email = (EditText) findViewById(R.id.email);
        final Button Register =  findViewById(R.id.btnRegister);
        final TextView Login =  findViewById(R.id.btnLogin);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameTxt = reg_username.getText().toString().trim();
                final String passwordTxt = reg_password.getText().toString();
                final String emailTxt = reg_email.getText().toString();

                if (usernameTxt.isEmpty() || passwordTxt.isEmpty() || emailTxt.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Fill the Requirements", Toast.LENGTH_SHORT).show();
                } else {
                    mDatabase.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean usernameExists = false;
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                String username = userSnapshot.child("username").getValue(String.class);
                                if (username != null && username.equals(usernameTxt)) {
                                    usernameExists = true;
                                    break;
                                }
                            }

                            if (usernameExists) {
                                Toast.makeText(RegisterActivity.this, "Username is Already Taken", Toast.LENGTH_SHORT).show();
                            } else {
                                DatabaseReference userRef = mDatabase.child("User").push(); // Generate unique ID
                                userRef.child("username").setValue(usernameTxt);
                                userRef.child("pass").setValue(passwordTxt);
                                userRef.child("email").setValue(emailTxt);

                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle error
                        }
                    });
                }
            }
        });



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
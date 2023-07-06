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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText reg_password = (EditText) findViewById(R.id.pass);
        final EditText reg_email = (EditText) findViewById(R.id.email);
        final Button Register =  findViewById(R.id.btnRegister);
        final TextView Login =  findViewById(R.id.btnLogin);
        auth = FirebaseAuth.getInstance();


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String passwordTxt = reg_password.getText().toString().trim();
                final String emailTxt = reg_email.getText().toString().trim();

                if (passwordTxt.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Fill the Requirements", Toast.LENGTH_SHORT).show();
                }
                if (emailTxt.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Fill the Requirements", Toast.LENGTH_SHORT).show();
                }else {
                    auth.createUserWithEmailAndPassword(emailTxt, passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Register Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
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

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    public void onBackPressed() {

    }
}
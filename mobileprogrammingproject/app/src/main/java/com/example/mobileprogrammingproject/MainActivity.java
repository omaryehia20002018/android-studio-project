package com.example.mobileprogrammingproject;

import static com.example.mobileprogrammingproject.R.id.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        //TextView a;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //a= (TextView) findViewById(R.id.textview);
         Button a = (Button) findViewById(R.id.login);
        Button b = (Button) findViewById(R.id.signup1);
        EditText c = (EditText) findViewById(R.id.edittext);
        EditText d = (EditText) findViewById(R.id.edittext2);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
          a.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  /*
                  Intent intent;
                  intent = new Intent(MainActivity.this,orderhistoryrecycleview.class);
                  startActivity(intent);*/


                  if(TextUtils.isEmpty(c.getText().toString())){
                      Toast.makeText(MainActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
                      return;
                  }

                  if(TextUtils.isEmpty(d.getText().toString())){
                      Toast.makeText(MainActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                      return;
                  }

                  mAuth.signInWithEmailAndPassword(c.getText().toString(),d.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {



                          if (task.isSuccessful()) {
                              Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();

                              Intent intent;
                              intent = new Intent(MainActivity.this, afterlogin.class);
                              //intent.putExtra("user", mAuth.getCurrentUser().getUid());

                              startActivity(intent);
                          } else {
                              Toast.makeText(MainActivity.this, "login fail", Toast.LENGTH_SHORT).show();

                          }




                      }
                  }
                  );




              }
          });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainActivity.this,signup.class);

                startActivity(intent);

            }
        });




        };

    }


package com.example.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class afterlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
        Button l= (Button) findViewById(R.id.see);
        Button o= (Button) findViewById(R.id.see1);
        Button oo= (Button) findViewById(R.id.see2);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(afterlogin.this, resturantrecyclerview.class);
                startActivity(intent);
            }
        });



        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(afterlogin.this, orderhistoryrecycleview.class);
                startActivity(intent);
            }
        });

        oo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(afterlogin.this, profile.class);
                startActivity(intent);
            }
        });

    }
}
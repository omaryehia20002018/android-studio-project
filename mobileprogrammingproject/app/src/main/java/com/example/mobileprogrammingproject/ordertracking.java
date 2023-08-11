package com.example.mobileprogrammingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ordertracking extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref1;
    DatabaseReference remo1;
    orderadapter z;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordertracking);
        ProgressBar a= (ProgressBar) findViewById(R.id.track);
        String u=mAuth.getCurrentUser().getUid();
       database=FirebaseDatabase.getInstance("https://mobileprogrammingproject-f3b20-default-rtdb.firebaseio.com/");
        ref1=database.getReference("cart/"+"currentorder/");
        remo1=database.getReference(u+"/"+"oldorders/");
        DatabaseReference r=remo1.push();
        String idd=r.getKey();
        String c="cooking";
        Button k=(Button) findViewById(R.id.homepage);
        /*
        if(ref1.child("status").getRef()==c){
            a.setProgress(10);
        }
        if(ref1.child("status").getValue(String.class).equals("on our way")){
            a.setProgress(50);
        }
        if(ref1.child("status").getValue(String.class).equals("delivered")){
            a.setProgress(100);
        }*/


        //String s;
        ref1.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String status = dataSnapshot.child("status").getValue(String.class);
                    //if(status)

                }*/
                String s = snapshot.child("status").getValue(String.class);


                String k1="cooking";
                String kk="on our way";
                String kkk="delivered";

                if(s.equals(k1)){
                    a.setProgress(20);
                }

                if(s.equals(kk)){
                    a.setProgress(50);
                }


                if(s.equals(kkk)){
                    a.setProgress(100);



                    k.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {




                            Intent intent;
                            intent = new Intent(ordertracking.this, afterlogin.class);
                            startActivity(intent);
                        }
                    });










                }
                //z.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ordertracking.this, "error", Toast.LENGTH_SHORT).show();
            }
        });










        //String s= ref1.child("status").toString();



    }




}

package com.example.mobileprogrammingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class dishrecyclerview extends AppCompatActivity {
    private dishadapter dishadapter;
    private ArrayList<dishes> dishlist;

    private RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference ref1;
    DatabaseReference ref2;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishrecyclerview);
        String restaurantname;
        String u=mAuth.getCurrentUser().getUid();
        Button o= (Button) findViewById(R.id.uio);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                restaurantname= null;
            } else {
                restaurantname= extras.getString("name");
            }
        } else {
            restaurantname= (String) savedInstanceState.getSerializable("name");
        }


        String restaurantname1;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                restaurantname1= null;
            } else {
                restaurantname1= extras.getString("cartname");
            }
        } else {
            restaurantname1= (String) savedInstanceState.getSerializable("cartname");
        }


        String restaurantimage;

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if(extra == null) {
                restaurantimage= null;
            } else {
                restaurantimage= extra.getString("image");
            }
        } else {
            restaurantimage= (String) savedInstanceState.getSerializable("image");
        }



        recyclerView = (RecyclerView) findViewById(R.id.recycledish);
        dishlist=new ArrayList<>();
        /*
        dishlist.add(new dishes("meat","available","40",R.drawable.fresh_meat_logo_design_01__1_));
        dishlist.add(new dishes("pizza","available","50",R.drawable.pizza_logo_vector_13019707_1_));
        dishlist.add(new dishes("chicken","available","60",R.drawable.oips5js7j13));
        dishlist.add(new dishes("burger","available","70",R.drawable.r_2_));*/

        database=FirebaseDatabase.getInstance("https://mobileprogrammingproject-f3b20-default-rtdb.firebaseio.com/");
        ref1= database.getReference("Restuarants/"+restaurantname+"/"+"dishes");
        ref2=database.getReference("orders/"+"users/"+u);
        DatabaseReference r=ref2.push();
        String id=r.getKey();
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dishlist.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    String link=  dataSnapshot.child("image").getValue(String.class);
                    String name=dataSnapshot.child("name").getValue(String.class);
                    String price=dataSnapshot.child("price").getValue(String.class);
                    String available=dataSnapshot.child("availability").getValue(String.class);


                    dishes dish= new dishes(name,available,price,link);
                    dishlist.add(dish);

                }
                dishadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*
        database=FirebaseDatabase.getInstance("https://mobileprogrammingproject-f3b20-default-rtdb.firebaseio.com/");
        ref2= database.getReference("Restuarants/"+restaurantname1+"/"+"dishes");


        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dishlist.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    String link=  dataSnapshot.child("image").getValue(String.class);
                    String name=dataSnapshot.child("name").getValue(String.class);
                    String price=dataSnapshot.child("price").getValue(String.class);
                    String available=dataSnapshot.child("availability").getValue(String.class);


                    dishes dish= new dishes(name,available,price,link);
                    dishlist.add(dish);

                }
                dishadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/








        dishadapter=new dishadapter(this,dishlist,new dishadapter.ItemClickListener() {

            @Override
            public void onItemClick(dishes p, int position) {

                String dishname=p.getTitle1();
                String dishavailable=p.getTitle2();
                String dishprice=p.getTitle3();
                String dishimage=p.getImage1();
                Intent inten;
                inten = new Intent(dishrecyclerview.this, cartrecyclerview.class);

                inten.putExtra("ordername",restaurantname);
                inten.putExtra("orderimage",restaurantimage);
                inten.putExtra("dishnam",dishname);
                inten.putExtra("dishimg",dishimage);
               // inten.putExtra("dishavailable",dishavailable);
                inten.putExtra("dishpric",dishprice);
                ref2.child("order"+id).child("dishname").setValue(dishname);
                ref2.child("order"+id).child("dishprice").setValue(dishprice);
                ref2.child("order"+id).child("dishimage").setValue(dishimage);
                inten.putExtra(id,"order"+id);
                startActivity(inten);
            }
        });
        /*
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten;
                inten = new Intent(dishrecyclerview.this, cartrecyclerview.class);
                startActivity(inten);
            }
        });
*/



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dishadapter);




    }
}
package com.example.mobileprogrammingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class orderhistoryrecycleview extends AppCompatActivity {
     orderadapter orderadapter;
    private ArrayList<orders>orderlist;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    DatabaseReference ref2;
    DatabaseReference remo;
    DatabaseReference remox;
    DatabaseReference remo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistoryrecycleview);
        recyclerView = (RecyclerView) findViewById(R.id.recycleorder);
        orderlist=new ArrayList<>();
        mAuth= FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance("https://mobileprogrammingproject-f3b20-default-rtdb.firebaseio.com/");
        String u=mAuth.getCurrentUser().getUid();
        remo=database.getReference(u+"/"+"currentorder");
        String uu=mAuth.getCurrentUser().getUid();
        remo1=database.getReference(uu+"/"+"oldorders");
        DatabaseReference r=remo1.push();
        /*
        String idd=r.getKey();
        remo1.child(uu).child("oldorders").setValue(u);*/

        ref2=database.getReference("cart"+"/"+"currentorder/");

        /*
        orderlist.add(new orders("king of burger restaurant","28/7/2022","60LE", R.drawable.kisspng_portable_network_graphics_food_fusion_cuisine_logo_restrokhana_your_online_restaurant_partner_br_5cfe4eadd1c225_5829975215601701578592_1_));
        orderlist.add(new orders("king of burger restaurant","29/7/2022","90LE", R.drawable.kisspng_portable_network_graphics_food_fusion_cuisine_logo_restrokhana_your_online_restaurant_partner_br_5cfe4eadd1c225_5829975215601701578592_1_));
        orderlist.add(new orders("bazooka restaurant","30/7/2022","60LE", R.drawable._694358_1_));
        orderlist.add(new orders("bazooka restaurnt","31/7/2022","60LE", R.drawable._694358_1_));*/

        /*

        ref2.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderlist.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){


                }
                remo1.child(idd).child("restaurantnam").setValue(snapshot.child("restaurantname").getValue(String.class));
                remo1.child(idd).child("restaurantimge").setValue(snapshot.child("restaurantimage").getValue(String.class));
                remo1.child(idd).child("orderdat").setValue(snapshot.child("orderdate").getValue(String.class));

                //orderadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        remo1.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderlist.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {


                    String rname = dataSnapshot.child("restaurantname").getValue(String.class);
                    String rimg = dataSnapshot.child("restaurantimg").getValue(String.class);
                    ;
                    String rdate = dataSnapshot.child("orderdate").getValue(String.class);
                    orders order = new orders(rname, rdate, rimg);
                    orderlist.add(order);
                }
                orderadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });















        orderadapter=new orderadapter(this, orderlist, new orderadapter.ItemClickListener() {
            @Override
            public void onItemClick(orders p, int position) {
                Intent inten= new Intent(orderhistoryrecycleview.this,resturantrecyclerview.class);
                startActivity(inten);

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderadapter);
        //remo1.child("os").removeValue();
        remo.removeValue();
        //ref2.removeValue();
        //orderadapter.notifyDataSetChanged();
    }
}
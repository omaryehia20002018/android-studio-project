package com.example.mobileprogrammingproject;

import static com.example.mobileprogrammingproject.R.id.recycledish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class resturantrecyclerview extends AppCompatActivity {
    private resturantadapter resturantadapter;
    private ArrayList<resturantrecycle> resturants;

    private RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference ref;
    DatabaseReference ref1;
   dishes dish;
    private dishadapter dishadapter;
    private ArrayList<dishes> dishlist;
    private RecyclerView recyclerView1;
    DataSnapshot d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturantrecyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        resturants=new ArrayList<>();
/*
        resturants.add(new resturantrecycle("king of burger restaurant",R.drawable.kisspng_portable_network_graphics_food_fusion_cuisine_logo_restrokhana_your_online_restaurant_partner_br_5cfe4eadd1c225_5829975215601701578592_1_));
        resturants.add(new resturantrecycle("bazooka restaurant",R.drawable._694358_1_));
        resturants.add(new resturantrecycle("lord of wings resturant",R.drawable._e8feb109720503_5fda2a433c12d_1_));
        resturants.add(new resturantrecycle("king of chicken resturant",R.drawable._7b804b9d40711a527420491cffc5f9e_1_));

        resturants.add(new resturantrecycle(" family restaurant",R.drawable.number5));
        resturants.add(new resturantrecycle("sprint restaurant",R.drawable.number_6));
        resturants.add(new resturantrecycle("little mac resturant",R.drawable.number_7));
        resturants.add(new resturantrecycle("king of meat resturant",R.drawable.number_8));

        resturants.add(new resturantrecycle("king of pizza restaurant",R.drawable.number_9));
        resturants.add(new resturantrecycle("king of fish restaurant","https://firebasestorage.googleapis.com/v0/b/mobileprogrammingproject-f3b20.appspot.com/o/1694358%5B1%5D.jpg?alt=media&token=e8b3db19-7503-4e85-8be4-985b02245fcb"));*/


        database=FirebaseDatabase.getInstance("https://mobileprogrammingproject-f3b20-default-rtdb.firebaseio.com/");

        recyclerView1 = (RecyclerView) findViewById(recycledish);
        dishlist=new ArrayList<>();





















        ref= database.getReference("Restuarants");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                resturants.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    String link=  dataSnapshot.child("image").getValue(String.class);
                    String name=dataSnapshot.child("name").getValue(String.class);
                   // int a= Integer.parseInt(image);

                    resturantrecycle resturant= new resturantrecycle(name,link);
                    resturants.add(resturant);

                }
                resturantadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        resturantadapter=new resturantadapter(this,resturants, new resturantadapter.ItemClickListener(){
            @Override
            public void onItemClick(resturantrecycle p , int position) {

                String name=p.getTitle();
                String image=p.getImage();

                Intent intent;
                intent = new Intent(resturantrecyclerview.this, dishrecyclerview.class);
                intent.putExtra("name",name);
                intent.putExtra("image",image);
                startActivity(intent);

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(resturantadapter);








    }
}
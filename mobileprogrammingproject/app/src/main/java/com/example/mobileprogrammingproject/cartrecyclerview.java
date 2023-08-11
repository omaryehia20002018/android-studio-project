package com.example.mobileprogrammingproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cartrecyclerview extends AppCompatActivity {

    private cartadapter cartadapter;
     public ArrayList<carts> cartlist;
    FirebaseDatabase database;
    DatabaseReference ref1;
    DatabaseReference ref2;
    DatabaseReference er;
    DatabaseReference remo;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartrecyclerview);
        Button add= (Button) findViewById(R.id.additem);
        Button remove=(Button) findViewById(R.id.removeall);
        Button pay=(Button) findViewById(R.id.pay);
        TextView meat=(TextView)findViewById(R.id.meat);
        TextView money=(TextView)findViewById(R.id.money);
        String date= String.valueOf(java.time.LocalDate.now());
        recyclerView = (RecyclerView) findViewById(R.id.cartrecycle);
        cartlist = new ArrayList<>();
        database=FirebaseDatabase.getInstance("https://mobileprogrammingproject-f3b20-default-rtdb.firebaseio.com/");
        String u=mAuth.getCurrentUser().getUid();
        String uu=mAuth.getCurrentUser().getUid()+"i";
       ref1=database.getReference("orders/"+"users/"+u);
       remo=database.getReference(u+"/"+"currentorder");
       ref2=database.getReference("cart"+"/"+"currentorder");
       er=database.getReference();
        DatabaseReference r=er.push();
        String idk=r.getKey();

       //er=database.getReference(uu+"/"+"oldorders");


       // DatabaseReference r=ref1.push();
        //String id=r.getKey();
        /*
        cartlist.add(new carts("meat","40"));
        cartlist.add(new carts("chicken","70"));
        cartlist.add(new carts("burger","40"));
        cartlist.add(new carts("pizza","60"));

        cartadapter = new cartadapter(this, cartlist);*/

        String ordername;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                ordername = null;
            } else {
                ordername = extra.getString("ordername");
            }
        } else {
            ordername = (String) savedInstanceState.getSerializable("ordername");
        }

        TextView or = (TextView) findViewById(R.id.resid);
        or.setText("your order details from"+" "+ordername);
        ImageView oi = (ImageView) findViewById(R.id.image2);


        String orderimage;

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                orderimage = null;
            } else {
                orderimage = extra.getString("orderimage");
            }
        } else {
            orderimage = (String) savedInstanceState.getSerializable("orderimage");
        }


        Picasso.get().load(orderimage).into(oi);


        String dishname;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                dishname = null;
            } else {
                dishname = extra.getString("dishnam");
            }
        } else {
            dishname = (String) savedInstanceState.getSerializable("dishnam");
        }

        String dishprice;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                dishprice = null;
            } else {
                dishprice = extra.getString("dishpric");
            }
        } else {
            dishprice = (String) savedInstanceState.getSerializable("dishpric");
        }


        String dishimg;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                dishimg = null;
            } else {
                dishimg = extra.getString("dishimg");
            }
        } else {
            dishimg = (String) savedInstanceState.getSerializable("dishimg");
        }





        String id;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                id = null;
            } else {
                id = extra.getString("id");
            }
        } else {
            id = (String) savedInstanceState.getSerializable("id");
        }
        /*
        meat.setText(dishname);
        money.setText(dishprice);*/
        cartlist.clear();
        ref1.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartlist.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){


                    String name=dataSnapshot.child("dishname").getValue(String.class);
                    String pric=dataSnapshot.child("dishprice").getValue(String.class);

                    String img=dataSnapshot.child("dishimage").getValue(String.class);

                    carts cart= new carts(name,pric,img);
                    cartlist.add(cart);

                }
                cartadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartlist.clone();
                Intent inten;
                inten = new Intent(cartrecyclerview.this, dishrecyclerview.class);
                inten.putExtra("name",ordername);
                inten.putExtra("image",orderimage);
                int a=cartlist.size();
                String aa=String.valueOf(a);



                startActivity(inten);
            }
        });


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*
                ref1.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //cartlist.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                        }
                        cartadapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });*/

                ref1.removeValue();
            }
        });

        cartadapter = new cartadapter(this, cartlist, new cartadapter.ItemClickListener() {
            @Override
            public void onItemClick(carts p, int position) {

                /*
                Intent inten;
                inten = new Intent(cartrecyclerview.this, resturantrecyclerview.class);
                startActivity(inten);*/



                    //cartlist.clear();
                        /*
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            if(dataSnapshot.child(dishname).getValue(String.class)==p.getOrder()){
                                dataSnapshot.getRef().child(dishname).removeValue();
                                dataSnapshot.getRef().child(dishprice).removeValue();


                        }*/







            }







        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remo=ref1;


                cartlist.clear();
                ref1.addValueEventListener(new ValueEventListener() {



                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //cartlist.clear();
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            DatabaseReference r=remo.push();
                            String idd=r.getKey();
                            remo.child(idd).child("dishname").setValue(dataSnapshot.child("dishname").getValue(String.class));
                            remo.child(idd).child("dishprice").setValue(dataSnapshot.child("dishprice").getValue(String.class));
                            remo.child(idd).child("dishimage").setValue(dataSnapshot.child("dishimage").getValue(String.class));


                            ref2.child(idd).child("dishname").setValue(dataSnapshot.child("dishname").getValue(String.class));
                            ref2.child(idd).child("dishprice").setValue(dataSnapshot.child("dishprice").getValue(String.class));
                            ref2.child(idd).child("dishimage").setValue(dataSnapshot.child("dishimage").getValue(String.class));


                        }
                        cartadapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
              // er.child("restaurantname").setValue(ordername);
               // er.child("restaurantimage").setValue(orderimage);
                er.child(u).child("oldorders").child(idk).child("orderdate").setValue(date);
                er.child(u).child("oldorders").child(idk).child("restaurantname").setValue(ordername);
                er.child(u).child("oldorders").child(idk).child("restaurantimg").setValue(orderimage);
                ref2.child("restaurantname").setValue(ordername);
                ref2.child("restaurantimage").setValue(orderimage);

                remo.child("status").setValue("not started");
                ref2.child("status").setValue("not started");
                //er.child("orderdate").setValue(date);
                ref2.child("orderdate").setValue(date);
                //er.child("os").setValue("o");
                ref1.removeValue();
                Intent inten;
                inten = new Intent(cartrecyclerview.this, pay.class);
                startActivity(inten);







            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartadapter);


    }
    public void onDestroy(){

        super.onDestroy();
        ref1.removeValue();
        //remo.removeValue();
        setContentView(R.layout.activity_cartrecyclerview);

    }


}
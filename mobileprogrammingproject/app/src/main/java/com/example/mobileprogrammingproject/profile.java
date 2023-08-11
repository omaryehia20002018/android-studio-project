package com.example.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference profile;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView a=(TextView) findViewById(R.id.firstname);
        TextView b=(TextView) findViewById(R.id.lastname);
        TextView c=(TextView) findViewById(R.id.email);
        TextView d=(TextView) findViewById(R.id.password);


        String firstname;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                firstname = null;
            } else {
                firstname = extra.getString("firstname");
            }
        } else {
            firstname = (String) savedInstanceState.getSerializable("firstname");
        }


        String lastname;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                lastname = null;
            } else {
                lastname = extra.getString("lastname");
            }
        } else {
            lastname = (String) savedInstanceState.getSerializable("lastname");
        }



        String email;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                email = null;
            } else {
                email = extra.getString("email");
            }
        } else {
            email = (String) savedInstanceState.getSerializable("email");
        }




        String password;
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                password= null;
            } else {
                password = extra.getString("password");
            }
        } else {
            password = (String) savedInstanceState.getSerializable("password");
        }




/*
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        User user = new User();
        user.firstname = firstname;
        user.lastname = lastname;
        user.email=email;
        user.password=password;

        UserDao userDao = db.userDao();
        userDao.insert(user);*/

        database= FirebaseDatabase.getInstance("https://mobileprogrammingproject-f3b20-default-rtdb.firebaseio.com/");
        String u=mAuth.getCurrentUser().getUid();
        String uu=mAuth.getCurrentUser().getEmail();
       String uuuh;
        uuuh = mAuth.getCurrentUser().getEmail();
        profile=database.getReference(u);





        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        UserDao userDao = db.userDao();
        //Log.d("gh", userDao.getUserByEmaiL(uu)));

       a.setText("firstname:"+" "+String.valueOf(userDao.getUserByEmaiL(uu).firstname));
        b.setText("lastname:"+" "+String.valueOf(userDao.getUserByEmaiL(uu).lastname));
        c.setText("email:"+" "+String.valueOf(userDao.getUserByEmaiL(uu).email));
        d.setText("password:"+" "+String.valueOf(userDao.getUserByEmaiL(uu).password));
        //a.setText(s.getString(firstname));


    }
}
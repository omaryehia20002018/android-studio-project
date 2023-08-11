package com.example.mobileprogrammingproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    // ...
    // Initialize Firebase Auth
    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }*/
    FirebaseDatabase database;
    DatabaseReference profile;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static boolean isCollegeEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9_!#$%&'+/=?`{|}~^-]+(?:\\.[A-Z0-9_!#$%&'+/=?`{|}~^-]+↵\n" +
                ")*@eng.asu.edu.eg$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText a = (EditText) findViewById(R.id.edittext3);
        EditText b = (EditText) findViewById(R.id.edittext4);
        EditText d = (EditText) findViewById(R.id.edittext5);

        EditText aa = (EditText) findViewById(R.id.edittextname);
        EditText bb = (EditText) findViewById(R.id.edittextlastname);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Button c = (Button) findViewById(R.id.signup);










        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(a.getText().toString())){
                    Toast.makeText(signup.this, "please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(b.getText().toString())){
                    Toast.makeText(signup.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(b.getText().toString().length()<8){
                    Toast.makeText(signup.this, " password should not be less than 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean v= isCollegeEmail(a.getText().toString());

                if(v==false){
                    Toast.makeText(signup.this, "email should be in the format @eng.asu.edu.eg", Toast.LENGTH_SHORT).show();
                    return;
                }


               /*

                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                UserDao userDao = db.userDao();

                User user = new User();
                user.firstname = aa.getText().toString();
                user.lastname = bb.getText().toString();
                user.email=a.getText().toString();
                user.password=b.getText().toString();


                userDao.insertAll(user);*/

                //Log.d("myLogs", userDao.getAll().get(4).lastname.toString());

                mAuth.createUserWithEmailAndPassword(a.getText().toString(), b.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(signup.this, "signup success", Toast.LENGTH_SHORT).show();


                            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                            UserDao userDao = db.userDao();

                            User user = new User();
                            user.firstname = aa.getText().toString();
                            user.lastname = bb.getText().toString();
                            user.email=a.getText().toString();
                            user.password=b.getText().toString();
                            userDao.insertAll(user);
                            /*
                            SharedPreferences s;
                            s=getSharedPreferences(user.firstname,this);
                            SharedPreferences.Editor editor=s.edit();
                            editor.putString(user.firstname,firstname);
                            editor.putString(lastname,lastname);
                            userDao.insertAll(user);*/
                            database= FirebaseDatabase.getInstance("https://mobileprogrammingproject-f3b20-default-rtdb.firebaseio.com/");
                            String u=mAuth.getCurrentUser().getUid();
                            profile=database.getReference(u);
                            /*
                            profile.child("firstname").setValue(user.firstname);
                            profile.child("lastname").setValue(user.lastname);
                            profile.child("email").setValue(user.email);
                            profile.child("password").setValue(user.password);*/

                            Intent intent;
                            intent = new Intent(signup.this, MainActivity.class);
                            intent.putExtra("firstname",user.firstname);
                            intent.putExtra("lastname",user.lastname);
                            intent.putExtra("email",user.email);
                            intent.putExtra("password",user.password);

                            startActivity(intent);
                        } else {
                            Toast.makeText(signup.this, "signup fail", Toast.LENGTH_SHORT).show();

                        }


                    }
                });
            }


        });



        
       // Log.d("signup",userDao.getAll().toString());


    }


}


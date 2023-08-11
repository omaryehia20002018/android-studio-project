package com.example.mobileprogrammingproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name ="first_name")
    public String firstname;
    @ColumnInfo(name ="last_name")
    public String lastname;
    @ColumnInfo(name ="email")
    public String email;
    @ColumnInfo(name ="password")
    public String password;

}

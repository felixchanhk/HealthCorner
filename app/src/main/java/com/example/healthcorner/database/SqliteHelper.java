package com.example.healthcorner.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.healthcorner.database.model.User;


public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "health_corner";
    //DATABASE VERSION
    public static final int DATABASE_VERSION = 8;
    //TABLE NAME
    public static final String TABLE_USERS = "users";
    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";
    //COLUMN user name
    public static final String KEY_USER_NAME = "username";
    //COLUMN email
    public static final String KEY_EMAIL = "email";
    //COLUMN password
    public static final String KEY_PASSWORD = "password";
    //COLUMN password
    public static final String KEY_HEIGHT = "height";
    //COLUMN password
    public static final String KEY_WEIGHT = "weight";
    //COLUMN password
    public static final String KEY_AGE = "age";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_HEIGHT + " TEXT, "
            + KEY_WEIGHT + " TEXT, "
            + KEY_AGE + " TEXT"
            + " ) ";

    //SQL for creating users table
    public static final String SQL_TABLE_WATER = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_HEIGHT + " TEXT, "
            + KEY_WEIGHT + " TEXT, "
            + KEY_AGE + " TEXT"
            + " ) ";

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.getUserName());

        //Put email in  @values
        values.put(KEY_EMAIL, user.getEmail());

        //Put password in  @values
        values.put(KEY_PASSWORD, user.getPassword());

        //Put height in  @values
        values.put(KEY_HEIGHT, user.getHeight());

        //Put weight in  @values
        values.put(KEY_WEIGHT, user.getWeight());

        //Put age in  @values
        values.put(KEY_AGE, user.getAge());

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_HEIGHT, KEY_WEIGHT, KEY_AGE},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.getEmail()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6) );

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

    public User getUser(String email){
        String id = "", userName = "", userEmail = "", password = "", height = "", weight = "",age = "";

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //use rawQuery method
        Cursor cursor = db.rawQuery(
                "select * from users where email=?",
                new String[]{email});

        while (cursor.moveToNext()) {
            id = cursor.getString(0);
            userName = cursor.getString(1);
            userEmail = cursor.getString(2);
            password = cursor.getString(3);
            height = cursor.getString(4);
            weight = cursor.getString(5);
            age = cursor.getString(6);
            Log.e("user:", id + ": " + userName + ", " + userEmail + "," + password + " , " + height + " , "+weight + " , " + age);
        }

        cursor.close();
        User getUser = new User(id, userName, userEmail, password, height, weight, age);

        return getUser;
    }
}
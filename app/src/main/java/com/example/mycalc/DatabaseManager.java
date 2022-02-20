package com.example.mycalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseManager extends SQLiteOpenHelper {


    public static final String NAME = "name";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String GENDER = "gender";

    public static final String TABLE_NAME = "personTable";

    public static final String DATABASE_NAME = "UserDatabase";
    public static final int DATABASE_VERSION = 1;

    public static final String QUERY_FOR_CREATE_TABLE =
            "CREATE TABLE "+ TABLE_NAME + "("
                    + NAME + " text,"
                    + USERNAME + " text,"
                    + PASSWORD + " text,"
                    + GENDER + " text);";
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Log.d("SQLite", "Database created");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_FOR_CREATE_TABLE);
        //Log.d("SQLite", "Table created");
    }

    // for insert data
    public void insertData(User user, SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, user.name);
        contentValues.put(USERNAME, user.userName);
        contentValues.put(PASSWORD, user.password);
        contentValues.put(GENDER, user.gender);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        //Log.d("SQLite", "Data inserted");

    }

    // for view data
    public Cursor getAllData(SQLiteDatabase sqLiteDatabase){
        Cursor cursor;
        String columns[] = {NAME, USERNAME, PASSWORD, GENDER};
        cursor = sqLiteDatabase.query(TABLE_NAME,columns,null,null,null,null,null);
        return cursor;
    }

    // search user
    public Cursor searchUser(String uname, SQLiteDatabase sqLiteDatabase){
        Cursor cursor;
        String column[] = {USERNAME, PASSWORD};
        String searchQry = USERNAME + " LIKE ?";
        String string[] = {uname};
        cursor = sqLiteDatabase.query(TABLE_NAME, column, searchQry, string,null,null,null);
        return cursor;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
}


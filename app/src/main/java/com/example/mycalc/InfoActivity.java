package com.example.mycalc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity implements Helper {
    private RecyclerView mList;
    private MyAdapter adapter;
    private ArrayList<User> list;
    DatabaseManager databaseManager;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        mList = findViewById(R.id.infoView);
        list = new ArrayList<>();

        databaseManager = new DatabaseManager(InfoActivity.this);
        sqLiteDatabase = databaseManager.getReadableDatabase();
        cursor =  databaseManager.getAllData(sqLiteDatabase);

        if(cursor.moveToFirst()){
            do{
                String _name, _uname, _password, _gender;
                _name = cursor.getString(0);
                _uname = cursor.getString(1);
                _password = cursor.getString(2);
                _gender = cursor.getString(3);

                User user = new User(_name, _uname, _password, _gender);
                list.add(user);
            } while (cursor.moveToNext());
        }

        adapter = new MyAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mList.setLayoutManager(layoutManager);
        mList.setItemAnimator(new DefaultItemAnimator());
        adapter.setHelper(this);
        mList.setAdapter(adapter);
/*
        listView = (ListView) findViewById(R.id.list1);
        myAdapter = new MyAdapter(ViewDataActivity.this, R.layout.list_items_layout);
        listView.setAdapter(myAdapter);
*/

    }
    /*private ArrayList<User> getData(){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        list.add(new User("Aditya", "inquisitor","Adi1234", "M"));
        return list;
    }*/

    @Override
    public void onItemClick(View view){
        Toast t = Toast.makeText(getApplicationContext(),  "View is clicked",  Toast.LENGTH_LONG);
        t.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
        t.show();
    }


}

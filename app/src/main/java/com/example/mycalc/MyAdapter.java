package com.example.mycalc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private ArrayList<User> mDataList;
    private Helper helper;

    public MyAdapter(ArrayList<User> list){
        mDataList = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_db_list_view, parent, false);
        return new MyHolder(view, this);
    }

    public void setHelper(Helper ob) {
        helper = ob;
    }

    public Helper getHelper(){
        return helper;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder viewHolder, int position){
        MyHolder holder = (MyHolder) viewHolder;
        ArrayList<TextView> main = holder.getView();
        User user = mDataList.get(position);

        main.get(0).setText( user.name);
        main.get(1).setText("Gender : " + user.gender);
        main.get(2).setText("Username : " + user.userName);
        main.get(3).setText("Password : "+ user.password);
    }
    public int getItemCount(){
        return mDataList.size();
    }
}


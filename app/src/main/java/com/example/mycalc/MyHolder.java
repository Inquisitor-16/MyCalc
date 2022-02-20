package com.example.mycalc;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView _name;
    private TextView _uname;
    private TextView _pwd;
    private TextView _gender;

    MyAdapter mAdapter;
    public MyHolder(@NonNull View itemView, MyAdapter myAdapter){
        super(itemView);
        mAdapter = myAdapter;
        _name = itemView.findViewById(R.id.name_here);
        _gender = itemView.findViewById(R.id.gender_here);
        _uname = itemView.findViewById(R.id.uname_here);
        _pwd = itemView.findViewById(R.id.pwd_here);
        itemView.setOnClickListener(this);
    }

    public ArrayList<TextView> getView() {
        ArrayList<TextView> tv_list = new ArrayList<>();
        tv_list.add(_name);
        tv_list.add(_gender);
        tv_list.add(_uname);
        tv_list.add(_pwd);
        return tv_list;
    }

    @Override
    public void onClick(View view) {
        mAdapter.getHelper().onItemClick(view);
    }
}

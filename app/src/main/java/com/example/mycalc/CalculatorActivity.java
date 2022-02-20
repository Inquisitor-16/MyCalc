package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button add_btn, subtract_btn, multiply_btn, divide_btn, mod_btn, equal_btn;
    private Button bracks_open, bracks_close;
    private TextView in_tv, out_tv, hello;
    Solve solve = new Solve();
    String stream = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        SharedPreferences sharedPreferences = getSharedPreferences("Info", MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        add_btn = findViewById(R.id.add);
        subtract_btn = findViewById(R.id.subtract);
        multiply_btn = findViewById(R.id.multiply);
        divide_btn = findViewById(R.id.divide);
        mod_btn = findViewById(R.id.mod);

        bracks_open = findViewById(R.id.bracket_open);
        bracks_close = findViewById(R.id.bracket_close);

        equal_btn = findViewById(R.id.equals);

        in_tv = findViewById(R.id.input);
        out_tv = findViewById(R.id.output);
        out_tv.setText("0");
        in_tv.setText("0");
        hello = findViewById(R.id.welcome);
        hello.setText(username);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        add_btn.setOnClickListener(this);
        subtract_btn.setOnClickListener(this);
        multiply_btn.setOnClickListener(this);
        divide_btn.setOnClickListener(this);
        mod_btn.setOnClickListener(this);

        bracks_open.setOnClickListener(this);
        bracks_close.setOnClickListener(this);

        equal_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){

        switch(view.getId()){
            case R.id.btn0:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("0");
                }
                else {
                    in_tv.setText(stream + "0");
                    stream = stream + "0";
                }
                break;
            case R.id.btn1:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("1");
                }
                else {
                    in_tv.setText(stream + "1");
                    stream = stream + "1";
                }
                break;
            case R.id.btn2:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("2");
                }
                else {
                    in_tv.setText(stream + "2");
                    stream = stream + "2";
                }
                break;
            case R.id.btn3:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("3");
                }
                else {
                    in_tv.setText(stream + "3");
                    stream = stream + "3";
                }
                break;
            case R.id.btn4:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("4");
                }
                else {
                    in_tv.setText(stream + "4");
                    stream = stream + "4";
                }
                break;
            case R.id.btn5:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("5");
                }
                else {
                    in_tv.setText(stream + "5");
                    stream = stream + "5";
                }
                break;
            case R.id.btn6:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("6");
                }
                else {
                    in_tv.setText(stream + "6");
                    stream = stream + "6";
                }
                break;
            case R.id.btn7:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("7");
                }
                else {
                    in_tv.setText(stream + "7");
                    stream = stream + "7";
                }
                break;
            case R.id.btn8:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("8");
                }
                else {
                    in_tv.setText(stream + "8");
                    stream = stream + "8";
                }
                break;
            case R.id.btn9:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText("9");
                }
                else {
                    in_tv.setText(stream + "9");
                    stream = stream + "9";
                }
                break;
            case R.id.bracket_open:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText(stream + "(");
                }
                else {
                    in_tv.setText(stream + "(");
                    stream = stream + "(";
                }
                break;
            case R.id.bracket_close:
                stream = in_tv.getText().toString();
                if(stream == "0"){
                    in_tv.setText(stream + ")");
                }
                else {
                    in_tv.setText(stream + ")");
                    stream = stream + ")";
                }
                break;
            case R.id.add:
                stream = in_tv.getText().toString();
                in_tv.setText(stream + "+");
                break;
            case R.id.subtract:
                stream = in_tv.getText().toString();
                in_tv.setText(stream + "-");
                break;
            case R.id.multiply:
                stream = in_tv.getText().toString();
                in_tv.setText(stream + "×");
                break;
            case R.id.divide:
                stream = in_tv.getText().toString();
                in_tv.setText(stream + "÷");
                break;
            case R.id.equals:
                int res = solve.evaluate(stream);
                out_tv.setText(Integer.toString(res));
                in_tv.setText("0");
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.player:
                Intent intent_toPlayer = new Intent(this, PlayerActivity.class);
                startActivity(intent_toPlayer);
                return true;
            case R.id.rView:
                Intent intent_torView = new Intent(this, InfoActivity.class);
                startActivity(intent_torView);
                Toast.makeText(getApplicationContext(),"Directed to RecyclerView",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opt3:
                Toast.makeText(getApplicationContext(),"Option 3 Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opt4:
                Toast.makeText(getApplicationContext(),"Option 4 Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opt5:
                Toast.makeText(getApplicationContext(),"Option 5 Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Intent intent_toAbout = new Intent(this, About.class);
                startActivity(intent_toAbout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

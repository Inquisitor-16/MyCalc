package com.example.mycalc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText uname, password, captcha;
    String text;
    private Button login, signup;
    private AlertDialog.Builder builder;
    DatabaseManager databaseManager;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        uname = findViewById(R.id.uname_login);
        password = findViewById(R.id.pwd_login);
        login = findViewById(R.id.cnf_login);
        signup = findViewById(R.id.login_signup);
        captcha = findViewById(R.id.editText);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }
    private void callCalculatorActivity(){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.cnf_login:
                String username = uname.getText().toString();
                String pwd = password.getText().toString();
                databaseManager = new DatabaseManager(LoginActivity.this);
                sqLiteDatabase = databaseManager.getReadableDatabase();
                cursor = databaseManager.searchUser(username, sqLiteDatabase);

                String username_db ="";
                String pwd_db = "";

                if(cursor.moveToFirst()) {
                    do {
                        username_db = cursor.getString(0);
                        pwd_db = cursor.getString(1);
                    } while (cursor.moveToNext());
                }

                if((!username.isEmpty() && username.equals(username_db)) && !pwd.isEmpty() && pwd.equals(pwd_db)){
                    LayoutInflater layoutInflater = LayoutInflater.from(this);
                    View captchaView = layoutInflater.inflate(R.layout.captcha_verification, null);
                    builder = new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    builder.setTitle("Solve the Captcha");
                    builder.setMessage("To login, enter the text that you see");
                    captcha = captchaView.findViewById(R.id.editText);
                    builder.setView(captchaView);

                    builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            text = captcha.getText().toString();
                            if(text.equals("Td4eva")){
                                //Toast.makeText(LoginActivity.this, text, Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences("Info", MODE_PRIVATE);
                                SharedPreferences.Editor myEditor = sharedPreferences.edit();

                                myEditor.putString("Username", username);
                                myEditor.apply();

                                uname.setText("");
                                password.setText("");

                                callCalculatorActivity();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Wrong Captcha", Toast.LENGTH_SHORT).show();
                            }
                            //Toast.makeText(MainActivity.this, "Okk pressed", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(LoginActivity.this, "No pressed", Toast.LENGTH_SHORT).show();
                            //dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    uname.setText("");
                    password.setText("");

                }
                else{
                    Toast t = Toast.makeText(getApplicationContext(),  "Either Username or Password is wrong",  Toast.LENGTH_LONG);
                    t.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                    t.show();
                }
                break;

            case R.id.login_signup:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }


    }
}

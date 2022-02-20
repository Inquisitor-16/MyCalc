package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, uname, pwd, cnf_pwd;
    private Button signup, login;
    private RadioGroup radioGroup;
    DatabaseManager databaseManager;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name_signup);
        uname = findViewById(R.id.uname_signup);
        pwd = findViewById(R.id.pwd_signup);
        cnf_pwd = findViewById(R.id.cnf_pwd_signup);

        radioGroup = findViewById(R.id.sex);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            RadioButton radioButton = (RadioButton)radioGroup.findViewById(checkedId);
        });

        signup = findViewById(R.id.cnf_signup);
        login = findViewById(R.id.signup_login);

        signup.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.cnf_signup:
                String name_str, gender_str, uname_str, pwd_str, cnf_pwd_str;

                name_str = name.getText().toString();
                if(name_str.isEmpty()){
                    name.setError("Name cannot be empty");
                }
                else if(name_str.length() < 3){
                    name.setError("Name should be at least 3 characters long");
                }

                uname_str = uname.getText().toString();
                if(uname_str.isEmpty()){
                    uname.setError("Username cannot be empty");
                }
                else if(uname_str.length() < 4){
                    uname.setError("Name should be at least 4 characters long");
                }
                else if(uname_str.charAt(0) >= '0' && uname_str.charAt(0) <= '9'){
                    uname.setError("Username should not start with a number");
                }
                pwd_str = pwd.getText().toString();
                cnf_pwd_str = cnf_pwd.getText().toString();

                if(!isValidPassword(pwd_str)){
                    pwd.setError("Password must be at least 8 characters and at most 20 characters.\n" +
                            "It must contain least one digit.\n" +
                            "It must contain least one upper case alphabet.\n" +
                            "It must contain least one lower case alphabet.\n" +
                            "It must contain least one special character which includes !@#$%&*()-+=^.\n" +
                            "It doesn’t contain any white space.");
                    pwd.setText("");
                    cnf_pwd.setText("");
                }
                else if(!pwd_str.equals(cnf_pwd_str)){
                    Toast.makeText(getApplicationContext(),
                            "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                    pwd.setText("");
                    cnf_pwd.setText("");
                }

                else if (name_str.length() < 3 || uname_str.length() < 4
                        || pwd_str.isEmpty() || cnf_pwd_str.isEmpty()
                        || !isValidPassword(pwd_str)
                        || radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(),
                            "One or more fields is empty or inconsistent",
                            Toast.LENGTH_SHORT).show();

                }
                else {
                    RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                    gender_str = radioButton.getText().toString();
                    if(isPresentInDb(uname_str)){
                        Toast.makeText(getApplicationContext(),
                                "Username already exists, Kindly login",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        storeData(name_str, uname_str, pwd_str, gender_str);
//
                        name.setText("");
                        radioGroup.clearCheck();
                        uname.setText("");
                        pwd.setText("");
                        cnf_pwd.setText("");


                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                    }
                }

                break;
            case R.id.signup_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void storeData(String name, String userName, String password, String gender){
        databaseManager = new DatabaseManager(MainActivity.this);
        sqLiteDatabase = databaseManager.getWritableDatabase();
        databaseManager.insertData(new User(name, userName,password, gender),sqLiteDatabase);
        //Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
        databaseManager.close();
    }

    private boolean isPresentInDb(String userName){
        databaseManager = new DatabaseManager(getApplicationContext());
        sqLiteDatabase = databaseManager.getReadableDatabase();
        cursor = databaseManager.searchUser(userName, sqLiteDatabase);
        String username_db ="";
        if(cursor.moveToFirst()) {
            do {
                username_db = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return userName.equals(username_db);
    }

    public static boolean isValidPassword(String password)
    {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
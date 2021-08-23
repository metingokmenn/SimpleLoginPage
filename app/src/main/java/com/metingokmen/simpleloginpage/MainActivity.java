package com.metingokmen.simpleloginpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText editText1;
    EditText editText2;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.metingokmen.simpleloginpage", Context.MODE_PRIVATE);
        editText1 = findViewById(R.id.editTextPersonName);
        editText2 = findViewById(R.id.editTextPassword);
        String savedUsername = sharedPreferences.getString("username","");
        String savedPassword = sharedPreferences.getString("password","");
        username = savedUsername;
        password = savedPassword;

    }

    public void signUp(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Error!");
        alert.setMessage("Invalid input");

        if(!((editText1.getText().toString().matches("")) || (editText2.getText().toString().matches("")))){
            Toast.makeText(MainActivity.this,"Sign up succesful" , Toast.LENGTH_LONG).show();
            sharedPreferences.edit().putString("username",editText1.getText().toString()).apply();
            sharedPreferences.edit().putString("password",editText2.getText().toString()).apply();
            username = sharedPreferences.getString("username","");
            password = sharedPreferences.getString("password","");

        }
        else{
            alert.show();
        }

    }


    public void login(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        String enteredUsername = editText1.getText().toString();
        String enteredPassword = editText2.getText().toString();
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Error!");
        alert.setMessage("Invalid input");
        if(editText1.getText().toString().matches("") || editText2.getText().toString().matches("")){
            alert.show();

        }
        else{
            if(enteredUsername.matches(username) && enteredPassword.matches(password)){
                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this,"Username or password is incorrect",Toast.LENGTH_LONG).show();
            }
        }


    }
    public void delete(View view){
        String storedUsername = sharedPreferences.getString("username","");
        String storedPassword = sharedPreferences.getString("password","");
        if(!(storedUsername.isEmpty() && storedPassword.isEmpty())){
            Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_LONG).show();
            //sharedPreferences.edit().remove("username").apply();
            //sharedPreferences.edit().remove("password").apply();
            username = "";
            password = "";
        }

        //sharedPreferences.edit().remove("username").apply();
        //sharedPreferences.edit().remove("password").apply();

    }
}
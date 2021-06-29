package com.example.internallogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPref;
    String usr;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        sharedPref = context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);

        usr = sharedPref.getString("n_usr", "-");
        pwd = sharedPref.getString("pwd_usr", "-");

    }

    public void login(View v) {
        EditText etU = findViewById(R.id.et_usr);
        EditText etP = findViewById(R.id.et_pwd);

        String u = etU.getText().toString();
        String p = etP.getText().toString();

        if (u.compareTo(usr) == 0 && p.compareTo(pwd) == 0) {
            Intent intent = new Intent(this, Configuration.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }
}
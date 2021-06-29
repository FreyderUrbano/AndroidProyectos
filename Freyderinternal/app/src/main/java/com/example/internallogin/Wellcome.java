package com.example.internallogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Wellcome extends AppCompatActivity {
    Context context;
    SharedPreferences sharedPref;
    FloatingActionButton fab;
    RelativeLayout rl;
    String colour;
    TextView tv1;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setContentView(R.layout.activity_wellcome);

        context = this;
        sharedPref = context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        String usr = sharedPref.getString("n_usr", "-");

        TextView tv = findViewById(R.id.tv_w);
        tv.setText("Bienvenido usuario " + usr);

        /*rl = (RelativeLayout) findViewById(R.id.RelativeLayout);
        if (colour == "GREEN") {
            rl.setBackgroundColor(Color.GREEN);
        } else if (colour == "YELLOW") {
            rl.setBackgroundColor(Color.YELLOW);
        } else if (colour == "BLUE") {
            rl.setBackgroundColor(Color.BLUE);
        } else {
            rl.setBackgroundColor(Color.RED);
        }
*/
        tv1 = (TextView)findViewById(R.id.tv_w);
        String texto = getIntent().getStringExtra("texto");
        tv1.setText("Bienvenido "  + texto);

        fab = findViewById(R.id.fab);
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, Login.class);
                startActivity(intent);
                return false;
            }
        });
    }

    public void goToLogin() {

    }
}
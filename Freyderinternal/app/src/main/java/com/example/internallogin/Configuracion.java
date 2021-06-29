package com.example.internallogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.internallogin.R.*;

public class Configuracion extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SharedPreferences sharedPref;
    FloatingActionButton fab;

    Button boton1;
    private EditText et1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_configuracion);
        RadioGroup radioGroup = (RadioGroup) findViewById(id.r_g_1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences prefs = getSharedPreferences("bgColour", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                String colourSelected = "";
                switch (checkedId) {
                    case id.azul:
                        colourSelected = "AZUL";
                        editor.putString("colour", colourSelected);
                        editor.commit();
                        break;
                    case id.rojo:
                        colourSelected = "ROJO";
                        editor.putString("colour", colourSelected);
                        editor.commit();
                        break;

                    case id.verde:
                        colourSelected = "VERDE";
                        editor.putString("colour", colourSelected);
                        editor.commit();
                        break;
                }
            }
        });

        et1 = (EditText) findViewById(R.id.nt1);
    }

    public void Aplicar(View view) {
        Intent aplicar = new Intent(this, Wellcome.class);
        aplicar.putExtra("texto", et1.getText().toString());
        startActivity(aplicar);
    }


    @Override
    public void onClick(View v) {

    }
}
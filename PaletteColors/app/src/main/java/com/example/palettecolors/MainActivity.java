package com.example.palettecolors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import static com.example.palettecolors.R.menu.menu1;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar sbr_red = null;
    private SeekBar sbr_green = null;
    private SeekBar sbr_blue = null;
    private SeekBar sbr_alpha = null;
    private View vie_colors = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbr_red = findViewById(R.id.sbrRed);
        sbr_green = findViewById(R.id.sbrGreen);
        sbr_blue = findViewById(R.id.sbrBlue);
        sbr_alpha = findViewById(R.id.sbrAlpha);
        vie_colors = findViewById(R.id.vieColors);

        sbr_red.setOnSeekBarChangeListener(this);
        sbr_green.setOnSeekBarChangeListener(this);
        sbr_blue.setOnSeekBarChangeListener(this);
        sbr_alpha.setOnSeekBarChangeListener(this);
    }
    //Show options menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }// el método onCreateOptionsMenu permite mostrar el menu en mainactivity

    //actions option menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.itemAboutOf :
                //Toast.makeText(this, "PRESIONASTE ACERCA DE", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Aboutof.class);
                startActivity(intent);
                break;

            case R.id.item_help :
                Intent helpAct = new Intent(this, HelpActivity.class);
                startActivity(helpAct);
                //Toast.makeText(this, "PRESIONASTE AYUDA", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_exit :
                Toast.makeText(this, "PRESIONASTE SALIR",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.iteReset :
                Toast.makeText(this, "REINICIAR",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.idtransparent :
                sbr_alpha.setProgress(0);
                break;
            case R.id.idSemitransparent :
                sbr_alpha.setProgress(128);
                break;
            case R.id.idOpaco :
                sbr_alpha.setProgress(255);
                break;
            case R.id.idBlack :
                break;
            case R.id.idWhite :
                break;
            case R.id.idGreen :
                break;
            case R.id.idBlue :
                sbr_red.setProgress(0);
                sbr_green.setProgress(0);
                sbr_blue.setProgress(255);
                break;
            case R.id.idCian :
                break;
            case R.id.idYellow :
                sbr_red.setProgress(255);
                sbr_green.setProgress(0);
                sbr_blue.setProgress(0);
                break;
            case R.id.idMagenta :
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean bo) {
        int r = sbr_red.getProgress();
        int g = sbr_green.getProgress();
        int b= sbr_blue.getProgress();
        int a = sbr_alpha.getProgress();

        int color = Color.argb(a,r,g,b);
        vie_colors.setBackgroundColor(color);


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
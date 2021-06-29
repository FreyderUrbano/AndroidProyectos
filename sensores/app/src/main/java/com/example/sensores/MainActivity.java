package com.example.sensores;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintSet;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    Context context;
    TextView txt;
    LinearLayout lnL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt = findViewById(R.id.txtSensor);
        lnL = findViewById(R.id.fondo);




        context = this;
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        String v = sensorEvent.values[0] + "";
        float milan = Float.parseFloat(v);
        Log.d("VS", "La alarma inicia a sonar");
        txt.setText("Sensor value: " + v);



        if (milan > 0) {
            lnL.setBackgroundColor(Color.RED);
            txt.setText("ROJO");
            txt.setTextColor(Color.BLUE);
        }else {
            lnL.setBackgroundColor(Color.BLUE);
            txt.setText("AZUL");
            txt.setTextColor(Color.RED);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
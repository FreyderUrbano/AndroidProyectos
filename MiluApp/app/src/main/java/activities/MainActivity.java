package activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.miluapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    EditText etNom;
    EditText etApe;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LounchWidgets();
        LounchEvents();

    }

    private void LounchWidgets() {
        context = this;
        fab = findViewById(R.id.fab);
        etNom = findViewById(R.id.et_Name);
        etApe = findViewById(R.id.et_Ape);
    }

    private void LounchEvents() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = etNom.getText().toString();
                String a = etApe.getText().toString();
                Toast.makeText(view.getContext(), "Nombre"+n+ "Apellido"+a, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MainMenu.class);

                if(n.compareTo("Freyder")==0 && a.compareTo("Urbano")==0){
                    i.putExtra( "Nombre", "Freyder");
                    i.putExtra("Apellido", "Urbano");
                    i.putExtra("Icono",R.drawable.usu);
                    startActivity(i);
                    finish();
                }
                else if(n.compareTo("admin")==0 && a.compareTo("admin")==0){
                    i.putExtra( "Nombre", "Administrador");
                    i.putExtra("Apellido", "Urbano");
                    i.putExtra("icono",R.drawable.ad);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(view.getContext(), "Datos no validos\nNombre"+n+ "Apellido"+a, Toast.LENGTH_SHORT).show();
                }
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
}
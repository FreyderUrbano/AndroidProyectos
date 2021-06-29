package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.urbanoapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    EditText etUser;
    EditText etApe;
    Context context;

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lounchWidgets();
        lounchEvents();
    }

    private void lounchWidgets() {

        context = this;

        fab = findViewById(R.id.fab);
        etUser = findViewById(R.id.et_Name);
        etApe = findViewById(R.id.et_Ape);

    }

    private void lounchEvents() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = etUser.getText().toString();
                String p = etApe.getText().toString();

                Intent i = new Intent(context, MenuUrbano.class);

                if (u.compareTo("admin") == 0 && p.compareTo("1234") == 0) {
                    i.putExtra("UserName", "Administrador");
                    startActivity(i);
                    finish();
                } else if (u.compareTo("freyder") == 0 && p.compareTo("1234") == 0) {
                    i.putExtra("UserName", "Freyder Urbano");
                    startActivity(i);
                    finish();
                } else {
                    etUser.setText("");
                    etApe.setText("");
                    Toast.makeText(view.getContext(), "Datos no válidos Envío\nUsr: " + u + " Pass: " + p, Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
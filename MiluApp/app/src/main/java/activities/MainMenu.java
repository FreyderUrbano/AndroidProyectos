package activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miluapp.R;
import com.example.miluapp.ui.gallery.GalleryFragment;
import com.example.miluapp.ui.home.HomeFragment;
import com.example.miluapp.ui.slideshow.SlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fragments.Animaciones;
import fragments.Componentes;
import fragments.Dialogos;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        context = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tvN = navigationView.getHeaderView(0).findViewById(R.id.freyder);
        TextView tvA = navigationView.findViewById(R.id.urbano);
        ImageView iv = navigationView.getHeaderView(0).findViewById(R.id.imageView);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        Bundle b = getIntent().getExtras();
        String nombre = b.getString("Nombre");
        String apellido = b.getString("Apellido");
        int icono = b.getInt("Icono");
        iv.setImageDrawable(getDrawable(icono));
        tvN.setText(nombre);
        tvA.setText(apellido);

        Toast.makeText(this, "Nombre" + nombre + "Apellido" + apellido, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
       return false;
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item){

        int id_menu = item.getItemId();
        Fragment fragment = null;

        switch (id_menu) {
            case R.id.nav_home:
                fragment = new Componentes();
                break;
            case R.id.nav_gallery:
                fragment = new Dialogos();
                break;

            case R.id.nav_slideshow:
                fragment = new Animaciones();
                break;

        }
       if(fragment != null){
           FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
           ft.replace(R.id.fragmento_principal,fragment);
           ft.commit();

       }
        return true;
    }
}
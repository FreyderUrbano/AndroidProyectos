package com.example.mapajosecaicedo;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapajosecaicedo.comons.ImageTools;
import com.example.mapajosecaicedo.comons.ImageTools;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Context context;
    public Activity activity;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        context = this;
        activity = this;
        counter = 0;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setBuildingsEnabled(true);

        // Add a marker in Sydney and move the camera

        askPermissions();
        organizeMarkers();
        lounchEvents();
        drawPolyline();
    }

    public void organizeMarkers() {

        LatLng chacha = new LatLng(1.405150, -77.290221);
        MarkerOptions Chachagui = new MarkerOptions().position(chacha).title("CHACHAGUI");
        Chachagui.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        Chachagui.draggable(true);
        mMap.addMarker(Chachagui);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chacha, 20));

    }

    public void askPermissions() {
         /*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "No se han asignado permisos", Toast.LENGTH_LONG).show();
                return;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
            }
        }
        */

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(context, "sin permisos", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    10);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void lounchEvents() {
        if (mMap != null) {

            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    mMap.addMarker(new MarkerOptions().position(
                            new LatLng(location.getLatitude(), location.getLongitude())
                            )
                                    .title("localizacion")
                    );
                }
            });

            mMap.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
                @Override
                public void onPoiClick(PointOfInterest pointOfInterest) {
                    mMap.addMarker(new MarkerOptions().position(
                            new LatLng(pointOfInterest.latLng.latitude, pointOfInterest.latLng.longitude)
                            )
                                    .title("Punto de interes")
                    );
                }
            });

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = getLayoutInflater();

                    View v = inflater.inflate(R.layout.dialog, null);
                    final EditText et = v.findViewById(R.id.nombre);
                    final Spinner sp = v.findViewById(R.id.sp_pasto);

                    builder.setView(v)
                            .setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean banName = et.getText().toString().compareTo("")!=0;
                                    boolean banCol = sp.getSelectedItem().toString().compareTo("")!=0;
                                    if (banName && banCol){
                                        dialog.cancel();
                                    }
                                    else{
                                        Toast.makeText(context,"TODOS LOS CAMPOS SON OBLIGATORIOS", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                    builder.setCancelable(true);
                    builder.show();

                }




                /*counter++;

                LayoutInflater inflater = getLayoutInflater();
                View point =inflater.inflate(R.layout.marker, null);

                TextView ppTV = (TextView) point.findViewById(R.id.texto);
                ppTV.setText("Contador # "+counter);

                ImageView pepe = (ImageView) point.findViewById(R.id.pushpin_bg);
                pepe.setImageDrawable(getDrawable(counter%2 == 0?R.drawable.pasto:R.drawable.pepe));


                mMap.addMarker(new MarkerOptions().position(
                        new LatLng(latLng.latitude, latLng.longitude)
                        )
                                .title("Punto del evento")

                                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                                .icon(
                                        BitmapDescriptorFactory.fromBitmap(
                                                ImageTools.createDrawableFromView(context, point)
                                        )
                                )
                );*/
            });
        }
    }


    public void drawPolyline() {
        LatLng pi = new LatLng(1.197987, -77.278172);
        LatLng p1 = new LatLng(1.210419, -77.276546);
        LatLng p2 = new LatLng(1.210414, -77.282763);
        LatLng pf = new LatLng(1.212779, -77.287188);

        List<PatternItem> pattern = Arrays.<PatternItem>asList(new Dash(30), new Gap(20));

        Polyline pl = mMap.addPolyline(new PolylineOptions().add(pi, p1, p2, pf).width(3).color(Color.GREEN));
        pl.setPattern(pattern);


    }

}

package com.example.maps;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.maps.Commons.ImageTools;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.security.Policy;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Context context;
    public Activity activity;

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setBuildingsEnabled(true);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //move camera
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        askPermissions();
        organizeMarkers();
        lounchEvents();
        drawPolyline();


    }


    public void organizeMarkers() {
        // Agregando un punto en el mapa en la plaza de Nariño
        LatLng plN = new LatLng(1.214702, -77.278222);
        MarkerOptions marker = new MarkerOptions().position(plN).title("Plaza de Nariño Mia");
        mMap.addMarker(marker);


        LatLng mm = new LatLng(1.214702, -77.278222);
        MarkerOptions marker3 = new MarkerOptions().position(mm).title("Marcador Movil");
        marker3.draggable(true);
        marker3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(marker3);

        LatLng plC = new LatLng(1.210760, -77.276629);
        MarkerOptions marker2 = new MarkerOptions().position(plC).title("Plaza del Carnaval Mia");
        mMap.addMarker(marker2);

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(plN));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plN, 15));
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
            Toast.makeText(context, "No se han asignado permisos", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    10);
        }
    }

    public void lounchEvents() {
        if (mMap != null) {

            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    mMap.addMarker(new MarkerOptions().position(
                            new LatLng(location.getLatitude(), location.getLongitude())
                            )
                                    .title("Aquí estamos")
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
                    ImageView iv = new ImageView(context);
                    iv.setImageDrawable(getDrawable(R.drawable.logo));

                    mMap.addMarker(new MarkerOptions().position(
                            new LatLng(latLng.latitude, latLng.longitude)
                            )
                                    .title("Punto del evento")
                                    //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                                    .icon(
                                            BitmapDescriptorFactory.fromBitmap(
                                                    ImageTools.createDrawableFromView(context, iv)
                                            )
                                    )
                    );
                }
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
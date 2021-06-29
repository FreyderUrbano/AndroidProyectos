package com.example.mapacentrado;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setBuildingsEnabled(true);


        //Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng localizacion = new LatLng(1.210828, -77.279296);
        MarkerOptions casaM = new MarkerOptions().position(localizacion).title("Google Maps Evelin");
        mMap.addMarker(casaM);

        LatLng PlazaCarnaval = new LatLng(1.210788, -77.276661);
        MarkerOptions Plaza = new MarkerOptions().position(PlazaCarnaval).title("Google Maps Plaza Carnaval");
        Plaza.draggable(true);
        Plaza.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(Plaza);




        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "SIN PERMISOS", Toast.LENGTH_LONG).show();
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);

            }


            if (mMap != null){

                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getAltitude())
                        mMap.addMarker(new MarkerOptions().position(
                                new LatLng(location.getLatitude(), location.getAltitude())
                        )
                        .title("Google Maps Evelin")
                        );
                    }
                });
                mMap.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
                    @Override
                    public void onPoiClick(PointOfInterest pointOfInterest) {
                        mMap.addMarker(new MarkerOptions().position(
                                new LatLng(pointOfInterest.latLng.latitude,pointOfInterest.latLng.longitude)
                                )
                                        .title("Google Maps Evelin")
                        );
                    }
                });
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        mMap.addMarker(new MarkerOptions().position(
                                new LatLng(latLng.latitude, latLng.longitude)
                                )
                                        .title("punto del evento")
                        );

                    }
                });

            }

        }
        mMap.setMyLocationEnabled(true);
    }

}

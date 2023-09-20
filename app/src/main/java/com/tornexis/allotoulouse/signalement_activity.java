package com.tornexis.allotoulouse;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.tornexis.allotoulouse.degradations.degradations_activity;

import java.io.IOException;
import java.util.List;

public class signalement_activity extends AppCompatActivity {
    private MapView mapView;
    private Marker marker;
    private float latitude = 43.607479f;
    private float longitude = 1.428254f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_signalement);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mrfreedy/clmm3eadx000y01qnhoxy4k4v"), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mapboxMap.getUiSettings().setCompassEnabled(false);
                        mapboxMap.getUiSettings().setLogoEnabled(false);
                        mapboxMap.getUiSettings().setAttributionEnabled(false);
                        mapboxMap.getUiSettings().setRotateGesturesEnabled(false);
                        mapboxMap.getUiSettings().setTiltGesturesEnabled(false);

                        mapView.getMapAsync(mapboxMap -> {
                            mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 15));
                        });

                        mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                .position(new LatLng(latitude, longitude))
                                .title("55 Allée de Barcelone, 31000 Toulouse")
                        );
                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}

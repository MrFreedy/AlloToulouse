package com.tornexis.allotoulouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.tornexis.allotoulouse.degradations.degradations_activity;
import com.tornexis.allotoulouse.dv.dv_activity;
import com.tornexis.allotoulouse.encombrants.encombrant_activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class autour_activity extends AppCompatActivity {
    private MapView mapView;
    private PermissionsManager permissionsManager;
    private LocationComponent locationComponent;
    private float latitude = 0.0f;
    private float longitude = 0.0f;

    private static final int PMR_ID = R.id.pmr;
    private static final int HORODATEUR_ID = R.id.horodateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_autour);

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

                        enableLocationComponent(style,mapboxMap);

                    }
                });
            }

        });

        ImageButton return_accueil_button_autour = findViewById(R.id.return_accueil_button_autour);
        return_accueil_button_autour.setOnClickListener(v->{
            finish();
        });

        ImageButton location = findViewById(R.id.location);
        location.setOnClickListener(v -> {
            /*mapView.getMapAsync(mapboxMap -> {
                mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 15));
            });
            String uri = String.format(Locale.ENGLISH, "geo:?q=%f,%f", latitude, longitude);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);*/
        });
        ImageButton menu = findViewById(R.id.menu);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigation_view = findViewById(R.id.navigation_view);

        menu.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Gérez les sélections d'éléments de menu ici
                int id = menuItem.getItemId();
                if(id == R.id.pmr){
                    if(menuItem.isChecked()){
                        menuItem.setChecked(false);
                        mapView.getMapAsync(mapboxMap -> {
                            List<Marker> markersToRemove = new ArrayList<>();
                            for (Marker marker : mapboxMap.getMarkers()) {
                                if (marker.getIcon().getBitmap().sameAs(IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.pmr).getBitmap())) {
                                    markersToRemove.add(marker);
                                }
                            }
                            for (Marker markerToRemove : markersToRemove) {
                                mapboxMap.removeMarker(markerToRemove);
                            }
                        });

                    }else{
                        menuItem.setChecked(true);
                        mapView.getMapAsync(mapboxMap -> {

                                    mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                            .position(new LatLng(43.603529f, 1.442262f))
                                            .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.pmr))
                                    );
                                    MapboxMap.OnMarkerClickListener onMarkerClickListener = new MapboxMap.OnMarkerClickListener() {
                                        @Override
                                        public boolean onMarkerClick(@NonNull Marker marker) {
                                            Toast.makeText(autour_activity.this, "34 Rue de la pomme", Toast.LENGTH_SHORT).show();
                                            return false;
                                        }
                                    };
                        });
                        mapView.getMapAsync(mapboxMap -> {

                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605630f, 1.441683f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.pmr))
                            );
                            MapboxMap.OnMarkerClickListener onMarkerClickListener = new MapboxMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
                                    Toast.makeText(autour_activity.this, "55 Allée de Barcelone Rue de la pomme", Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                            };
                        });
                        /*mapView.getMapAsync(mapboxMap -> {
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.603529f, 1.442262f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.pmr))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605682f, 1.444518f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.pmr))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605714f, 1.444940f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.pmr))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605889f, 1.447436f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.pmr))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.606170f, 1.447397f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.pmr))
                            );
                        });*/
                    }

                }else if(id==R.id.horodateur){
                    if(menuItem.isChecked()){
                        menuItem.setChecked(false);
                        mapView.getMapAsync(mapboxMap -> {
                            List<Marker> markersToRemove = new ArrayList<>();
                            for (Marker marker : mapboxMap.getMarkers()) {
                                if (marker.getIcon().getBitmap().sameAs(IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.horodateurs).getBitmap())) {
                                    markersToRemove.add(marker);
                                }
                            }
                            for (Marker markerToRemove : markersToRemove) {
                                mapboxMap.removeMarker(markerToRemove);
                            }
                        });

                    }else{
                        menuItem.setChecked(true);
                        mapView.getMapAsync(mapboxMap -> {
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605747f, 1.447374f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.horodateurs))
                            );
                        });
                    }
                }else if(id == R.id.selective){
                    if(menuItem.isChecked()){
                        menuItem.setChecked(false);
                        mapView.getMapAsync(mapboxMap -> {
                            List<Marker> markersToRemove = new ArrayList<>();
                            for (Marker marker : mapboxMap.getMarkers()) {
                                if (marker.getIcon().getBitmap().sameAs(IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.selective).getBitmap())) {
                                    markersToRemove.add(marker);
                                }
                            }
                            for (Marker markerToRemove : markersToRemove) {
                                mapboxMap.removeMarker(markerToRemove);
                            }
                        });

                    }else{
                        menuItem.setChecked(true);
                        mapView.getMapAsync(mapboxMap -> {
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605357f, 1.445690f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.selective))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.603517f, 1.445642f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.selective))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605682f, 1.444518f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.selective))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.603546f, 1.442273f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.selective))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.604872f, 1.442645f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.selective))
                            );
                        });
                    }
                }else if(id==R.id.verre){
                    if(menuItem.isChecked()){
                        menuItem.setChecked(false);
                        mapView.getMapAsync(mapboxMap -> {
                            List<Marker> markersToRemove = new ArrayList<>();
                            for (Marker marker : mapboxMap.getMarkers()) {
                                if (marker.getIcon().getBitmap().sameAs(IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.verre).getBitmap())) {
                                    markersToRemove.add(marker);
                                }
                            }
                            for (Marker markerToRemove : markersToRemove) {
                                mapboxMap.removeMarker(markerToRemove);
                            }
                        });

                    }else{
                        menuItem.setChecked(true);
                        mapView.getMapAsync(mapboxMap -> {
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605372f, 1.445960f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.verre))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.603560f, 1.445643f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.verre))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.603549f, 1.442275f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.verre))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.604865f, 1.442647f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.verre))
                            );
                        });
                    }
                }else if(id == R.id.bornes){
                    if(menuItem.isChecked()){
                        menuItem.setChecked(false);
                        mapView.getMapAsync(mapboxMap -> {
                            List<Marker> markersToRemove = new ArrayList<>();
                            for (Marker marker : mapboxMap.getMarkers()) {
                                if (marker.getIcon().getBitmap().sameAs(IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.bornes).getBitmap())) {
                                    markersToRemove.add(marker);
                                }
                            }
                            for (Marker markerToRemove : markersToRemove) {
                                mapboxMap.removeMarker(markerToRemove);
                            }
                        });

                    }else{
                        menuItem.setChecked(true);
                        mapView.getMapAsync(mapboxMap -> {
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.604822f, 1.442671f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.bornes))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.604951f, 1.443701f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.bornes))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.603932f, 1.443868f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.bornes))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.604071f, 1.444741f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.bornes))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.603567f, 1.444829f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.bornes))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.604896f, 1.444557f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.bornes))
                            );
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.605941f, 1.444373f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.bornes))
                            );
                        });
                    }
                }else if(id==R.id.sanisettes){
                    if(menuItem.isChecked()){
                        menuItem.setChecked(false);
                        mapView.getMapAsync(mapboxMap -> {
                            List<Marker> markersToRemove = new ArrayList<>();
                            for (Marker marker : mapboxMap.getMarkers()) {
                                if (marker.getIcon().getBitmap().sameAs(IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.sanisette).getBitmap())) {
                                    markersToRemove.add(marker);
                                }
                            }
                            for (Marker markerToRemove : markersToRemove) {
                                mapboxMap.removeMarker(markerToRemove);
                            }
                        });

                    }else{
                        menuItem.setChecked(true);
                        mapView.getMapAsync(mapboxMap -> {
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(43.604140f, 1.444919f))
                                    .icon(com.mapbox.mapboxsdk.annotations.IconFactory.getInstance(autour_activity.this).fromResource(R.drawable.sanisette))
                            );
                        });
                    }
                }


                // Fermez le menu latéral après la sélection
                drawerLayout.closeDrawers();

                return true; // Retourne true pour indiquer que l'événement a été traité avec succès
            }
        });




    }
    private void enableLocationComponent(Style loadedMapStyle, MapboxMap mapboxMap) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            LocationComponentOptions locationComponentOptions =
                    LocationComponentOptions.builder(this)
                            .foregroundTintColor(Color.parseColor("#FF2751"))
                            .accuracyAlpha(0f)
                            .bearingTintColor(Color.parseColor("#FF783B"))
                            .pulseEnabled(true)
                            .pulseColor(Color.GREEN)
                            .pulseAlpha(.4f)
                            .pulseInterpolator(new BounceInterpolator())
                            .build();

            LocationComponentActivationOptions locationComponentActivationOptions = LocationComponentActivationOptions
                    .builder(this, loadedMapStyle)
                    .locationComponentOptions(locationComponentOptions)
                    .build();

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Activate with a built LocationComponentActivationOptions object
            locationComponent.activateLocationComponent(locationComponentActivationOptions);

            // Enable to make component visible
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

        }
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
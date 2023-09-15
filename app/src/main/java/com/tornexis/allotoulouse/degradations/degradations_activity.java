package com.tornexis.allotoulouse.degradations;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.model.MarkerOptions;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
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
import com.tornexis.allotoulouse.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class degradations_activity extends AppCompatActivity {
    String type_degradation = null;
    String[] adresses = {
            "10 Rue de la République, 31000 Toulouse",
            "25 Avenue des Minimes, 31200 Toulouse",
            "5 Place du Capitole, 31000 Toulouse",
            "14 Rue de Metz, 31000 Toulouse",
            "33 Allée Jean Jaurès, 31000 Toulouse",
            "8 Boulevard de Strasbourg, 31000 Toulouse",
            "72 Rue du Faubourg Bonnefoy, 31500 Toulouse",
            "40 Avenue Camille Pujol, 31500 Toulouse",
            "6 Rue de la Concorde, 31000 Toulouse",
            "17 Rue de la Pomme, 31000 Toulouse",
            "55 Allée de Barcelone, 31000 Toulouse",
            "22 Rue des Filatiers, 31000 Toulouse"
    };
    String date = null;
    private MapView mapView;
    private Marker marker;
    private PermissionsManager permissionsManager;
    private LocationComponent locationComponent;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_degradations);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        AutoCompleteTextView adresse_completeTextView = findViewById(R.id.autocomplete_adresse);
        ArrayAdapter<String> adresse_adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item);





        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mrfreedy/clmg2mx9c01ib01r7hh794z5x"), new Style.OnStyleLoaded() {
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

                adresse_completeTextView.setAdapter(adresse_adapter);

                adresse_completeTextView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // Utilisez Geocoder pour rechercher des adresses en France basées sur le texte entré
                        Geocoder geocoder = new Geocoder(degradations_activity.this, Locale.getDefault());

                        try {
                            double franceNorth = 51.124213; // Latitude nord de la France
                            double franceSouth = 41.343824; // Latitude sud de la France
                            double franceWest = -5.142561;  // Longitude ouest de la France
                            double franceEast = 9.561547;   // Longitude est de la France

                            List<Address> addresses = geocoder.getFromLocationName(
                                    s.toString(),
                                    10, // Limitez les résultats à 10
                                    franceSouth,
                                    franceWest,
                                    franceNorth,
                                    franceEast
                            );

                            List<String> addressSuggestions = new ArrayList<>();
                            for (Address address : addresses) {
                                String addressText = address.getAddressLine(0); // Obtenez la première ligne de l'adresse
                                addressSuggestions.add(addressText);
                            }

                            // Mettez à jour l'adaptateur de l'AutoCompleteTextView avec les suggestions d'adresse
                            adresse_adapter.clear();
                            adresse_adapter.addAll(addressSuggestions);
                            adresse_adapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                adresse_completeTextView.setThreshold(2);
                // Définissez un écouteur de sélection pour l'AutoCompleteTextView
                adresse_completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedAddress = (String) parent.getItemAtPosition(position);

                        // Utilisez Geocoder pour obtenir les coordonnées de l'adresse sélectionnée
                        Geocoder geocoder = new Geocoder(degradations_activity.this);
                        try {
                            List<Address> addresses = geocoder.getFromLocationName(selectedAddress, 1);

                            if (!addresses.isEmpty()) {
                                Address selectedLocation = addresses.get(0);
                                double latitude = selectedLocation.getLatitude();
                                double longitude = selectedLocation.getLongitude();

                                marker = mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                        .position(new LatLng(latitude, longitude))
                                        .title(addresses.get(0).getAddressLine(0)));

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


                mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    public boolean onMapClick(@NonNull LatLng point) {
                        // Obtenez les coordonnées du point cliqué
                        double latitude = point.getLatitude();
                        double longitude = point.getLongitude();

                        if(marker!= null){
                            mapboxMap.removeMarker(marker);
                        }

                        Geocoder geocoder = new Geocoder(degradations_activity.this);
                        try {
                            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                            marker = mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(latitude, longitude))
                                    .title(addresses.get(0).getAddressLine(0)));

                            adresse_completeTextView.setText(addresses.get(0).getAddressLine(0));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }




                        return true;
                    }
                });

            }
        });



        EditText complement_adresse = findViewById(R.id.edit_complement_adresse);

        Spinner spinner_degradations = findViewById(R.id.type_degradations);
        String[] sac = new String[]{
                "Divers", "Mobilier urbain", "Nid-de-poule", "Regard (descellé/bruyant/cassé/absent)"
        };
        ArrayAdapter<String> degradation_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sac);
        degradation_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_degradations.setAdapter(degradation_adapter);

        spinner_degradations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type_degradation = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button next = findViewById(R.id.next_degradations);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!adresse_completeTextView.getText().toString().trim().isEmpty()) {
                    switch (type_degradation) {
                        case "Divers":
                            Intent divers_intent = new Intent(degradations_activity.this, divers_deg_activity.class);
                            startActivity(divers_intent);
                            break;
                        case "Regard (descellé/bruyant/cassé/absent)":
                            Intent regard_intent = new Intent(degradations_activity.this, regard_deg_activity.class);
                            startActivity(regard_intent);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Veuillez sélectionner un type de dégradation", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Veuillez saisir une adresse d'intervention", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton return_accueil_button = findViewById(R.id.return_accueil_button_encombrant);
        return_accueil_button.setOnClickListener(v -> {
            finish();
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
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

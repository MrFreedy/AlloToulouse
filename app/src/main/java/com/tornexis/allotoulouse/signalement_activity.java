package com.tornexis.allotoulouse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class signalement_activity extends AppCompatActivity {
    private MapView mapView;
    private Marker marker;
    private float latitude_1 = 43.607479f;
    private float longitude_1 = 1.428254f;
    private float latitude_2 = 43.602822f;
    private float longitude_2 = 1.446021f;
    private float latitude_3 = 43.609331f;
    private float longitude_3 = 1.458769f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_signalement);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        String id_signalement = getIntent().getStringExtra("id_signalement");
        String type_signalement = getIntent().getStringExtra("type_signalement");
        String date_signalement = getIntent().getStringExtra("date_signalement");
        String description_signalement = getIntent().getStringExtra("description_signalement");
        String adresse_signalement = getIntent().getStringExtra("adresse_signalement");
        String image_signalement = getIntent().getStringExtra("image_signalement");

        TextView id_signalement_text = findViewById(R.id.id_signalement);
        ImageView image_status_signalement_view = findViewById(R.id.image_status_signalement);
        TextView type_signalement_text = findViewById(R.id.type_signalement);
        TextView date_signalement_text = findViewById(R.id.date_signalement);
        TextView description_signalement_text = findViewById(R.id.description_signalement);
        TextView adresse_signalement_text = findViewById(R.id.adresse_signalement);
        HorizontalScrollView photo_horizontalScrollView = findViewById(R.id.photo_horizontalScrollView);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mrfreedy/clmm3eadx000y01qnhoxy4k4v"), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mapboxMap.getUiSettings().setCompassEnabled(false);
                        mapboxMap.getUiSettings().setLogoEnabled(false);
                        mapboxMap.getUiSettings().setAttributionEnabled(false);
                        mapboxMap.getUiSettings().setAllGesturesEnabled(false);

                        if(adresse_signalement==null){
                            mapView.getMapAsync(mapboxMap -> {
                                mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLngZoom(new LatLng(latitude_1,longitude_1), 15));
                            });
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(latitude_1, longitude_1))
                                    .title("55 Allée de Barcelone, 31000 Toulouse")
                            );
                        }else if (adresse_signalement.equals("34 Rue de la Pomme, 31000 Toulouse")){
                            mapView.getMapAsync(mapboxMap -> {
                                mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLngZoom(new LatLng(latitude_2,longitude_2), 15));
                            });
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(latitude_2, longitude_2))
                                    .title("34 Rue de la Pomme, 31000 Toulouse")
                            );
                            id_signalement_text.setText(id_signalement);
                            type_signalement_text.setText(type_signalement);
                            date_signalement_text.setText(date_signalement);
                            description_signalement_text.setText(description_signalement);
                            adresse_signalement_text.setText(adresse_signalement);
                        }else if(adresse_signalement.equals("14 Rue Boubée, 31500 Toulouse")){
                            mapView.getMapAsync(mapboxMap -> {
                                mapboxMap.animateCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLngZoom(new LatLng(latitude_3,longitude_3), 15));
                            });
                            mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                                    .position(new LatLng(latitude_3, longitude_3))
                                    .title("14 Rue Boubée, 31500 Toulouse")
                            );
                            id_signalement_text.setText(id_signalement);
                            type_signalement_text.setText(type_signalement);
                            date_signalement_text.setText(date_signalement);
                            description_signalement_text.setText(description_signalement);
                            adresse_signalement_text.setText(adresse_signalement);
                            image_status_signalement_view.setImageResource(R.drawable.prise_en_compte);
                            LinearLayout photo_layout = findViewById(R.id.photo_layout);
                            TextView text_photo_signalement = findViewById(R.id.text_photo_signalement);
                            photo_layout.removeView(text_photo_signalement);


                            Drawable citroenDrawable = getResources().getDrawable(R.drawable.citroen);
                            Bitmap citroenBitmap = ((BitmapDrawable) citroenDrawable).getBitmap();

                            Bitmap resizedCitroenBitmap = Bitmap.createScaledBitmap(citroenBitmap, 300, 300, true);

                            ImageView citroenImageView = new ImageView(signalement_activity.this);
                            citroenImageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
                            citroenImageView.setImageBitmap(resizedCitroenBitmap);

                            HorizontalScrollView horizontalScrollView = findViewById(R.id.photo_horizontalScrollView);
                            LinearLayout linearLayout = horizontalScrollView.findViewById(R.id.photo_linearlayout);
                            linearLayout.addView(citroenImageView);

                            Drawable renaultDrawable = getResources().getDrawable(R.drawable.citroen);
                            Bitmap renaultBitmap = ((BitmapDrawable) renaultDrawable).getBitmap();

                            Bitmap resizedRenaultBitmap = Bitmap.createScaledBitmap(renaultBitmap, 300, 300, true);

                            ImageView renaultImageView = new ImageView(signalement_activity.this);
                            renaultImageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
                            renaultImageView.setImageBitmap(resizedRenaultBitmap);

                            linearLayout.addView(renaultImageView);

                            citroenImageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent fullscreenIntent = new Intent(signalement_activity.this, FullscreenImageActivity.class);
                                    fullscreenIntent.putExtra("imageResourceId", R.drawable.citroen);
                                    startActivity(fullscreenIntent);
                                }
                            });

                            renaultImageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent fullscreenIntent = new Intent(signalement_activity.this, FullscreenImageActivity.class);
                                    fullscreenIntent.putExtra("imageResourceId", R.drawable.citroen);
                                    startActivity(fullscreenIntent);
                                }
                            });


                        }
                    }
                });
            }
        });













        Button close_signalement = findViewById(R.id.close_signalement);
        close_signalement.setOnClickListener(v->{
            finish();
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

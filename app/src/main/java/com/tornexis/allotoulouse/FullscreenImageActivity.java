package com.tornexis.allotoulouse;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FullscreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);


        ImageView fullscreenImageView = findViewById(R.id.fullscreenImageView);

        // Récupérez l'ID de la ressource de l'image depuis l'intent
        int imageResourceId = getIntent().getIntExtra("imageResourceId", 0);

        if (imageResourceId != 0) {
            // Chargez l'image en plein écran
            fullscreenImageView.setImageResource(imageResourceId);
        }

        ImageButton close_button = findViewById(R.id.close_button);
        close_button.setOnClickListener(v->{
            finish();
        });
    }
}


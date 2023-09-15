package com.tornexis.allotoulouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.tornexis.allotoulouse.degradations.degradations_activity;
import com.tornexis.allotoulouse.dv.dv_activity;
import com.tornexis.allotoulouse.encombrants.encombrant_activity;

public class MainActivity_Toulouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toulouse);

        ImageButton dv_button = findViewById(R.id.dv_button);
        dv_button.setOnClickListener(v -> {
            Intent dv_intent = new Intent(MainActivity_Toulouse.this, dv_activity.class);
            dv_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dv_intent);
        });

        ImageButton encombrant_button = findViewById(R.id.encombrant_button);
        encombrant_button.setOnClickListener(v -> {
            Intent encombrant_intent = new Intent(MainActivity_Toulouse.this, encombrant_activity.class);
            encombrant_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(encombrant_intent);
        });

        ImageButton degradations_button = findViewById(R.id.degradations_button);
        degradations_button.setOnClickListener(v -> {
            Intent degradations_intent = new Intent(MainActivity_Toulouse.this, degradations_activity.class);
            degradations_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(degradations_intent);
        });

        ImageButton return_commune_button = findViewById(R.id.return_commune_button);
        return_commune_button.setOnClickListener(v->{
            finish();
            Intent commune_intent = new Intent(MainActivity_Toulouse.this, main_menu.class);
            commune_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(commune_intent);
        });
    }
}
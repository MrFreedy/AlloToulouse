package com.tornexis.allotoulouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity_Toulouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toulouse);

        ImageButton dv_button = findViewById(R.id.dv_button);
        dv_button.setOnClickListener(v -> {
            Intent add_intent = new Intent(MainActivity_Toulouse.this, dv_activity.class);
            add_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(add_intent);
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
package com.tornexis.allotoulouse;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_Metropole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metropole);

        String commune = getIntent().getStringExtra("commune");

        TextView commune_text = findViewById(R.id.commune_name);
        if(commune.equals("Aigrefeuille") || commune.equals("Aucamville") || commune.equals("Aussonne")){
            commune_text.setText("Commune d'"+commune);
        }else{
            commune_text.setText("Commune de "+commune);
        }

    }
}
package com.tornexis.allotoulouse.degradations;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tornexis.allotoulouse.R;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degradations);

        AutoCompleteTextView adresse_completeTextView = findViewById(R.id.autocomplete_adresse);
        ArrayAdapter<String> adresse_adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item,adresses);
        adresse_completeTextView.setThreshold(2);
        adresse_completeTextView.setAdapter(adresse_adapter);

        EditText complement_adresse = findViewById(R.id.edit_complement_adresse);

        Spinner spinner_degradations = findViewById(R.id.type_degradations);
        String[] sac = new String[]{
                "Divers","Mobilier urbain","Nid-de-poule","Regard (descellé/bruyant/cassé/absent)"
        };
        ArrayAdapter<String> degradation_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sac);
        degradation_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_degradations.setAdapter(degradation_adapter);

        spinner_degradations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type_degradation = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Button next = findViewById(R.id.next_degradations);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!adresse_completeTextView.getText().toString().trim().isEmpty()){
                    switch (type_degradation){
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
                }else{
                    Toast.makeText(getApplicationContext(), "Veuillez saisir une adresse d'intervention", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton return_accueil_button = findViewById(R.id.return_accueil_button_encombrant);
        return_accueil_button.setOnClickListener(v->{
            finish();
        });
    }
}

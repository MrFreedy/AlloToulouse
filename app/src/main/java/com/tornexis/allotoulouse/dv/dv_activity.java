package com.tornexis.allotoulouse.dv;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.tornexis.allotoulouse.autour_activity;

public class dv_activity extends AppCompatActivity {
    Integer num_sac = 0;
    Integer num_fagot = 0;
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
        setContentView(R.layout.activity_dechets_verts);

        AutoCompleteTextView adresse_completeTextView = findViewById(R.id.autocomplete_adresse);
        ArrayAdapter<String> adresse_adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item,adresses);
        adresse_completeTextView.setThreshold(2);
        adresse_completeTextView.setAdapter(adresse_adapter);

        EditText complement_adresse = findViewById(R.id.edit_complement_adresse);

        Spinner spinner_sac = findViewById(R.id.nb_sac_dv);
        String[] sac = new String[]{
                "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
        };
        ArrayAdapter<String> sac_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sac);
        sac_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_sac.setAdapter(sac_adapter);

        spinner_sac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                num_sac = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_fagot = findViewById(R.id.nb_fagot_dv);
        String[] fagot = new String[]{
                "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"
        };
        ArrayAdapter<String> fagot_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, fagot);
        fagot_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_fagot.setAdapter(fagot_adapter);

        spinner_fagot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                num_fagot = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        HorizontalScrollView horizontalScrollView = findViewById(R.id.scrollview_date_dv);
        LinearLayout linearLayout = findViewById(R.id.scrollview_container_date); // L'ID de votre LinearLayout principal

        final View[] previouslySelectedChild = {null}; // Pour conserver une référence à l'enfant précédemment sélectionné

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);

            if (child instanceof LinearLayout) {
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView date_textView = (TextView) ((LinearLayout) child).getChildAt(0);
                        date = date_textView.getText().toString();

                        if (previouslySelectedChild[0] != null) {
                            previouslySelectedChild[0].setBackgroundResource(R.drawable.card); // Remplacez "R.drawable.card" par votre ressource par défaut
                            TextView previousDateTextView = (TextView) ((LinearLayout) previouslySelectedChild[0]).getChildAt(0);
                            previousDateTextView.setTextColor(Color.BLACK);
                        }

                        previouslySelectedChild[0] = child;

                        child.setBackgroundResource(R.drawable.card_selected);
                        date_textView.setTextColor(Color.WHITE);
                    }
                });
            }
        }

        TextView liste_dechet_dv_title = findViewById(R.id.liste_dechet_dv_title);
        LinearLayout liste_dechet_dv_underlline = findViewById(R.id.liste_dechet_dv_underlline);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (270 * getApplicationContext().getResources().getDisplayMetrics().density),
                (int) (3 * getApplicationContext().getResources().getDisplayMetrics().density)

        );


        RadioButton radio_particulier = findViewById(R.id.radio_particulier);
        RadioButton radio_syndic = findViewById(R.id.radio_syndic);

        radio_particulier.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                radio_syndic.setChecked(false);
                liste_dechet_dv_title.setText("Vos déchets verts (1m³ max)");
                liste_dechet_dv_underlline.setLayoutParams(params);

            }
        });

        radio_syndic.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                radio_particulier.setChecked(false);
                liste_dechet_dv_title.setText("Vos déchets verts (2m³ max)");
                liste_dechet_dv_underlline.setLayoutParams(params);

            }
        });


        Button validate = findViewById(R.id.validate_dv);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!adresse_completeTextView.getText().toString().trim().isEmpty()){
                    if(radio_particulier.isChecked() || radio_syndic.isChecked()){
                        if(num_fagot==0 && num_sac==0){
                            Toast.makeText(getApplicationContext(),"Veuillez saisir le nombre de sac et/ou fagots",Toast.LENGTH_SHORT).show();
                        }else{
                            if(date!=null || date ==""){
                                String adresse = adresse_completeTextView.getText().toString() + "\n"+ complement_adresse.getText().toString();
                                Intent popup_dv_intent = new Intent(dv_activity.this, activity_popup_dv.class);
                                popup_dv_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                popup_dv_intent.putExtra("nb_sacs",num_sac);
                                popup_dv_intent.putExtra("nb_fagots",num_fagot);
                                popup_dv_intent.putExtra("adresse",adresse);
                                popup_dv_intent.putExtra("date",date);
                                finish();
                                startActivity(popup_dv_intent);

                            }else{
                                Toast.makeText(getApplicationContext(), "Veuillez sélectionner une date", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Veuillez sélectionner la qualité du demandeur", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Veuillez saisir une adresse d'intervention", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton return_accueil_button = findViewById(R.id.return_accueil_button_dv);
        return_accueil_button.setOnClickListener(v->{
            finish();
        });

        ImageButton return_carte_button_dv = findViewById(R.id.return_carte_button_dv);
        return_carte_button_dv.setOnClickListener(v->{
            finish();
            Intent carte_intent = new Intent(dv_activity.this, autour_activity.class);
            carte_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(carte_intent);
        });
    }
}

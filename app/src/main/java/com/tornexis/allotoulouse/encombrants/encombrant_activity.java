package com.tornexis.allotoulouse.encombrants;

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

public class encombrant_activity extends AppCompatActivity {
    Integer nb_canape = 0;
    Integer nb_fauteuil = 0;
    Integer nb_lit = 0;
    Integer nb_etagere = 0;
    Integer nb_armoire = 0;
    Integer nb_petit_meuble = 0;
    Integer nb_frigo_americain = 0;
    Integer nb_frigo = 0;
    Integer nb_machines = 0;
    Integer nb_electromenager = 0;
    Integer nb_informatique= 0;
    Integer nb_petit_volume = 0;
    Integer nb_moyen_volume = 0;
    Integer nb_gros_volume= 0;
    Integer nb_bricolage = 0;
    Integer nb_deco = 0;
    Integer nb_metallique = 0;
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
        setContentView(R.layout.activity_encombrants);

        AutoCompleteTextView adresse_completeTextView = findViewById(R.id.autocomplete_adresse);
        ArrayAdapter<String> adresse_adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item,adresses);
        adresse_completeTextView.setThreshold(2);
        adresse_completeTextView.setAdapter(adresse_adapter);

        EditText complement_adresse = findViewById(R.id.edit_complement_adresse);


        String[] nb_encombrants = new String[]{
                "0","1","2","3","4","5","6","7","8","9","10"
        };
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, nb_encombrants);
        spinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);


        Spinner spinner_canape = findViewById(R.id.nb_canape);
        spinner_canape.setAdapter(spinner_adapter);
        spinner_canape.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_canape = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_fauteuil = findViewById(R.id.nb_fauteuil);
        spinner_fauteuil.setAdapter(spinner_adapter);
        spinner_fauteuil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_fauteuil = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_lit = findViewById(R.id.nb_lit);
        spinner_lit.setAdapter(spinner_adapter);
        spinner_lit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_lit = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_etageres = findViewById(R.id.nb_etageres);
        spinner_etageres.setAdapter(spinner_adapter);
        spinner_etageres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_etagere = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_armoire = findViewById(R.id.nb_armoire);
        spinner_armoire.setAdapter(spinner_adapter);
        spinner_armoire.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_armoire = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_petit_meuble = findViewById(R.id.nb_petit_meuble);
        spinner_petit_meuble.setAdapter(spinner_adapter);
        spinner_petit_meuble.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_petit_meuble = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_frigo_americain = findViewById(R.id.nb_frigo_americain);
        spinner_frigo_americain.setAdapter(spinner_adapter);
        spinner_frigo_americain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_frigo_americain = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_frigo = findViewById(R.id.nb_frigo);
        spinner_frigo.setAdapter(spinner_adapter);
        spinner_frigo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_frigo = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_machine = findViewById(R.id.nb_machine);
        spinner_machine.setAdapter(spinner_adapter);
        spinner_machine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_machines = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_electromenager = findViewById(R.id.nb_electromenager);
        spinner_electromenager.setAdapter(spinner_adapter);
        spinner_electromenager.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_electromenager = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_informatique = findViewById(R.id.nb_informatique);
        spinner_informatique.setAdapter(spinner_adapter);
        spinner_informatique.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_informatique = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_petit_volume = findViewById(R.id.nb_petit_loisir);
        spinner_petit_volume.setAdapter(spinner_adapter);
        spinner_petit_volume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_petit_volume = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_moyen_volume = findViewById(R.id.nb_moyen_loisir);
        spinner_moyen_volume.setAdapter(spinner_adapter);
        spinner_moyen_volume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_moyen_volume = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_gros_volume = findViewById(R.id.nb_gros_loisir);
        spinner_gros_volume.setAdapter(spinner_adapter);
        spinner_gros_volume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_gros_volume = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_bricolage = findViewById(R.id.nb_bricolage);
        spinner_bricolage.setAdapter(spinner_adapter);
        spinner_bricolage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_bricolage = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_decoration = findViewById(R.id.nb_decoration);
        spinner_decoration.setAdapter(spinner_adapter);
        spinner_decoration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_deco = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Spinner spinner_metallique = findViewById(R.id.nb_metallique);
        spinner_metallique.setAdapter(spinner_adapter);
        spinner_metallique.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nb_metallique = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });




        HorizontalScrollView horizontalScrollView = findViewById(R.id.scrollview_date_encombrant);
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
                            previouslySelectedChild[0].setBackgroundResource(R.drawable.card);
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

        TextView liste_encombrant_title = findViewById(R.id.liste_encombrant_title);
        LinearLayout liste_encombrant_underlline = findViewById(R.id.liste_encombrant_underlline);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (270 * getApplicationContext().getResources().getDisplayMetrics().density),
                (int) (3 * getApplicationContext().getResources().getDisplayMetrics().density)

        );


        RadioButton radio_particulier = findViewById(R.id.radio_particulier);
        RadioButton radio_syndic = findViewById(R.id.radio_syndic);

        radio_particulier.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                radio_syndic.setChecked(false);
                liste_encombrant_title.setText("Vos encombrants (2m³ max)");
                liste_encombrant_underlline.setLayoutParams(params);

            }
        });

        radio_syndic.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                radio_particulier.setChecked(false);
                liste_encombrant_title.setText("Vos encombrants (5m³ max)");
                liste_encombrant_underlline.setLayoutParams(params);

            }
        });


        Button validate = findViewById(R.id.validate_dv);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!adresse_completeTextView.getText().toString().trim().isEmpty()){
                    if(radio_particulier.isChecked() || radio_syndic.isChecked()){
                        if(nb_armoire==0 && nb_bricolage==0 && nb_canape==0 && nb_deco==0 && nb_electromenager==0 && nb_etagere==0 && nb_fauteuil==0 && nb_frigo==0 && nb_frigo_americain==0 && nb_gros_volume==0 && nb_informatique==0 && nb_lit==0 && nb_machines==0 && nb_metallique==0 && nb_moyen_volume==0 && nb_petit_meuble==0 && nb_petit_volume==0){
                            Toast.makeText(getApplicationContext(),"Veuillez saisir vos encombrants",Toast.LENGTH_SHORT).show();
                        }else{
                            if(date!=null || date ==""){
                                String adresse = adresse_completeTextView.getText().toString() + "\n"+ complement_adresse.getText().toString();
                                Intent popup_dv_intent = new Intent(encombrant_activity.this, encombrant_popup.class);
                                popup_dv_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                popup_dv_intent.putExtra("nb_armoire",nb_armoire);
                                popup_dv_intent.putExtra("nb_bricolage",nb_bricolage);
                                popup_dv_intent.putExtra("nb_canape",nb_canape);
                                popup_dv_intent.putExtra("nb_deco",nb_deco);
                                popup_dv_intent.putExtra("nb_electromenager",nb_electromenager);
                                popup_dv_intent.putExtra("nb_etagere",nb_etagere);
                                popup_dv_intent.putExtra("nb_fauteuil",nb_fauteuil);
                                popup_dv_intent.putExtra("nb_frigo",nb_frigo);
                                popup_dv_intent.putExtra("nb_frigo_americain",nb_frigo_americain);
                                popup_dv_intent.putExtra("nb_gros_volume",nb_gros_volume);
                                popup_dv_intent.putExtra("nb_informatique",nb_informatique);
                                popup_dv_intent.putExtra("nb_lit",nb_lit);
                                popup_dv_intent.putExtra("nb_machines",nb_machines);
                                popup_dv_intent.putExtra("nb_metallique",nb_metallique);
                                popup_dv_intent.putExtra("nb_moyen_volume",nb_moyen_volume);
                                popup_dv_intent.putExtra("nb_petit_meuble",nb_petit_meuble);
                                popup_dv_intent.putExtra("nb_petit_volume",nb_petit_volume);
                                popup_dv_intent.putExtra("nb_moyen_volume",nb_moyen_volume);
                                popup_dv_intent.putExtra("nb_gros_volume",nb_gros_volume);
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

        ImageButton return_accueil_button = findViewById(R.id.return_accueil_button_encombrant);
        return_accueil_button.setOnClickListener(v->{
            finish();
        });
    }
}

package com.tornexis.allotoulouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        ImageButton carte_button = findViewById(R.id.carte_button);
        carte_button.setOnClickListener(v->{
            Intent carte_intent = new Intent(MainActivity_Toulouse.this, autour_activity.class);
            carte_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(carte_intent);
        });

        HorizontalScrollView horizontalScrollView = findViewById(R.id.demandes_scrollview);
        LinearLayout linearLayout = findViewById(R.id.scrollview_container_demandes);

        final View[] previouslySelectedChild = {null}; // Pour conserver une référence à l'enfant précédemment sélectionné

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);

            if (child instanceof LinearLayout) {
                TextView typeTextView = (TextView) ((LinearLayout) child).getChildAt(2);
                String typeText = typeTextView.getText().toString();

                // Comparez le texte et effectuez une action en fonction de ce que vous trouvez
                if ("Lampe en panne".equals(typeText)) {
                    // Faites quelque chose en fonction du texte trouvé
                    child.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent signalement_intent = new Intent(MainActivity_Toulouse.this, signalement_activity.class);
                            signalement_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(signalement_intent);
                        }
                    });
                }else if("Détritus".equals(typeText)){
                    child.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent signalement_intent = new Intent(MainActivity_Toulouse.this, signalement_activity.class);
                            signalement_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            signalement_intent.putExtra("id_signalement", "Signalement\nn°102-125983");
                            signalement_intent.putExtra("type_signalement", "Détritus");
                            signalement_intent.putExtra("date_signalement", "Mercredi 06 Septembre à 08h13");
                            signalement_intent.putExtra("adresse_signalement", "34 Rue de la Pomme, 31000 Toulouse");
                            signalement_intent.putExtra("description_signalement", "Pleins d'emballages de fast-foods sont jetés dans la rue");
                            signalement_intent.putExtra("image_status_signalement", "reclamation");
                            startActivity(signalement_intent);
                        }
                    });
                }else if ("Stationnement abusif".equals(typeText)){
                    child.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent signalement_intent = new Intent(MainActivity_Toulouse.this, signalement_activity.class);
                            signalement_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            signalement_intent.putExtra("id_signalement", "Signalement\nn°110-281975");
                            signalement_intent.putExtra("type_signalement", "Stationnement abusif");
                            signalement_intent.putExtra("date_signalement", "Mardi 05 Septembre à 14h14");
                            signalement_intent.putExtra("adresse_signalement", "14 Rue Boubée, 31500 Toulouse");
                            signalement_intent.putExtra("description_signalement", "Une voiture ne bouge plus depuis plus de 15 jours.\nCitroën C3 Noire, immatriculée AB123CD");
                            signalement_intent.putExtra("image_status_signalement", "@drawable/prise_en_compte");
                            signalement_intent.putExtra("text_status_signalement", "Demande prise en compte");
                            signalement_intent.putExtra("image_signalement", "citroen");
                            startActivity(signalement_intent);
                        }
                    });
                }
            }
        }
    }
}
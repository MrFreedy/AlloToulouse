package com.tornexis.allotoulouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tornexis.allotoulouse.dv.dv_activity;
import com.tornexis.allotoulouse.encombrants.encombrant_activity;

import java.util.HashMap;

public class rechercher extends AppCompatActivity {
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher);



        HashMap<String,String> mots_cles = new HashMap<>();
        mots_cles.put("verts","déchets verts");
        mots_cles.put("déchets verts","déchets verts");
        mots_cles.put("herbes","déchets verts");
        mots_cles.put("herbe","déchets verts");
        mots_cles.put("garer","stationnement");
        mots_cles.put("garé","stationnement");
        mots_cles.put("garée","stationnement");
        mots_cles.put("garées","stationnement");
        mots_cles.put("garés","stationnement");
        mots_cles.put("garage","stationnement");
        mots_cles.put("garages","stationnement");
        mots_cles.put("stationner","stationnement");
        mots_cles.put("stationné","stationnement");
        mots_cles.put("stationnée","stationnement");
        mots_cles.put("stationnées","stationnement");
        mots_cles.put("stationnés","stationnement");
        mots_cles.put("stationnement","stationnement");
        mots_cles.put("encombrants","encombrants");
        mots_cles.put("encombrant","encombrants");
        mots_cles.put("encombrante","encombrants");
        mots_cles.put("encombrantes","encombrants");
        mots_cles.put("encombré","encombrants");
        mots_cles.put("encombrée","encombrants");
        mots_cles.put("meubles","encombrants");
        mots_cles.put("meuble","encombrants");

        EditText edit_text_rechercher = findViewById(R.id.edit_text_rechercher);

        edit_text_rechercher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Avant que le texte change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Pendant que le texte change

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Après que le texte a changé

                String query = editable.toString().toLowerCase();

                // Vérifiez si le texte est vide
                if (query.isEmpty()) {
                    // Effacez le LinearLayout des suggestions
                    LinearLayout suggestionsLayout = findViewById(R.id.suggestions);
                    suggestionsLayout.removeAllViews();
                    return; // Sortez de la méthode car il n'y a rien d'autre à faire
                }

                // Recherchez dans votre liste de mots-clés pour trouver des correspondances
                if (mots_cles.containsKey(query)) {
                    String theme = mots_cles.get(query);
                    if ("déchets verts".equals(theme)) {
                        // Créez un LinearLayout horizontal parent
                        LinearLayout horizontalLayout = new LinearLayout(context);
                        horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                200
                        ));
                        horizontalLayout.setPadding(20, 20, 20, 20);
                        horizontalLayout.setBackground(getResources().getDrawable(R.drawable.rechercher_background));
                        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

                        ImageView imageView = new ImageView(context);
                        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                                120, // largeur en pixels
                                120 // hauteur en pixels
                        );
                        imageParams.gravity = Gravity.CENTER_VERTICAL; // Centrer verticalement
                        imageView.setLayoutParams(imageParams);
                        imageView.setPadding(10, 0, 10, 0); // Marge à droite
                        imageView.setBackgroundResource(R.drawable.button_dechet_vert);
                        imageView.setImageResource(R.drawable.feuille);

                        TextView textView = new TextView(context);
                        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        textParams.gravity = Gravity.CENTER_VERTICAL; // Centrer verticalement
                        textView.setLayoutParams(textParams);
                        textView.setPadding(10, 0, 10, 0); // Marge à droite
                        Typeface customTypeface = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            customTypeface = getResources().getFont(R.font.montserrat_semibold);
                        }
                        textView.setTypeface(customTypeface);
                        textView.setTextSize(20);
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView.setText("Déchets verts");



                        // Ajoutez l'ImageView et le TextView au LinearLayout horizontal parent
                        horizontalLayout.addView(imageView);
                        horizontalLayout.addView(textView);

                        horizontalLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent dv_intent = new Intent(rechercher.this, dv_activity.class);
                                dv_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                finish();
                                startActivity(dv_intent);
                            }
                        });
                        LinearLayout suggestionsLayout = findViewById(R.id.suggestions);
                        suggestionsLayout.addView(horizontalLayout);
                    }else if("encombrants".equals(theme)){
                        // Créez un LinearLayout horizontal parent
                        LinearLayout horizontalLayout = new LinearLayout(context);
                        horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                200
                        ));
                        horizontalLayout.setPadding(20, 20, 20, 20);
                        horizontalLayout.setBackground(getResources().getDrawable(R.drawable.rechercher_background));
                        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

                        ImageView imageView = new ImageView(context);
                        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                                120, // largeur en pixels
                                120 // hauteur en pixels
                        );
                        imageParams.gravity = Gravity.CENTER_VERTICAL; // Centrer verticalement
                        imageView.setLayoutParams(imageParams);
                        imageView.setPadding(10, 0, 10, 0); // Marge à droite
                        imageView.setBackgroundResource(R.drawable.button_encombrants);
                        imageView.setImageResource(R.drawable.garde_robe);

                        TextView textView = new TextView(context);
                        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        textParams.gravity = Gravity.CENTER_VERTICAL; // Centrer verticalement
                        textView.setLayoutParams(textParams);
                        textView.setPadding(10, 0, 10, 0); // Marge à droite
                        Typeface customTypeface = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            customTypeface = getResources().getFont(R.font.montserrat_semibold);
                        }
                        textView.setTypeface(customTypeface);
                        textView.setTextSize(20);
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView.setText("Encombrants");



                        // Ajoutez l'ImageView et le TextView au LinearLayout horizontal parent
                        horizontalLayout.addView(imageView);
                        horizontalLayout.addView(textView);

                        horizontalLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent stationnement_intent = new Intent(rechercher.this, encombrant_activity.class);
                                stationnement_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                finish();
                                startActivity(stationnement_intent);
                            }
                        });
                        LinearLayout suggestionsLayout = findViewById(R.id.suggestions);
                        suggestionsLayout.addView(horizontalLayout);
                    }

                }
            }


        });

    }
}

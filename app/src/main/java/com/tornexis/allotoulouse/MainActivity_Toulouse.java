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
        LinearLayout linearLayout = findViewById(R.id.scrollview_container_demandes); // L'ID de votre LinearLayout principal

        final View[] previouslySelectedChild = {null}; // Pour conserver une référence à l'enfant précédemment sélectionné

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);

            if (child instanceof LinearLayout) {
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent signalement_intent = new Intent(MainActivity_Toulouse.this, signalement_activity.class);
                        signalement_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(signalement_intent);

                        /*if (previouslySelectedChild[0] != null) {
                            previouslySelectedChild[0].setBackgroundResource(R.drawable.card);
                            TextView previousDateTextView = (TextView) ((LinearLayout) previouslySelectedChild[0]).getChildAt(0);
                            previousDateTextView.setTextColor(Color.BLACK);
                        }

                        previouslySelectedChild[0] = child;

                        child.setBackgroundResource(R.drawable.card_selected);*/
                    }
                });
            }
        }
    }
}
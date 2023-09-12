package com.tornexis.allotoulouse;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dechets_verts);

        AutoCompleteTextView adresse_completeTextView = findViewById(R.id.autocomplete_adresse);
        ArrayAdapter<String> adresse_adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item,adresses);
        adresse_completeTextView.setThreshold(2);
        adresse_completeTextView.setAdapter(adresse_adapter);

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
                        String date = date_textView.getText().toString();

                        if (previouslySelectedChild[0] != null) {
                            previouslySelectedChild[0].setBackgroundResource(R.drawable.card); // Remplacez "R.drawable.card" par votre ressource par défaut
                            TextView previousDateTextView = (TextView) ((LinearLayout) previouslySelectedChild[0]).getChildAt(0);
                            previousDateTextView.setTextColor(Color.BLACK);
                        }

                        previouslySelectedChild[0] = child;

                        child.setBackgroundResource(R.drawable.card_selected);
                        date_textView.setTextColor(Color.WHITE);

                        System.out.println(date);
                    }
                });
            }
        }


    }
}

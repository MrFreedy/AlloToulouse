package com.tornexis.allotoulouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class main_menu extends AppCompatActivity {
    private String commune = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Spinner spinner = (Spinner) findViewById(R.id.commune_spinner);
        String[] communes = new String[]{
          "Commune...","Toulouse","Aigrefeuille","Aucamville","Aussonne","Balma","Beaupuy","Beauzelle","Blagnac","Brax","Bruguières","Castelginest","Colomiers","Cornebarrieu","Cugnaux","Drémil-Lafage","Fenouillet","Flourens","Fonbeauzard","Gagnac-sur-Garonne","Gratentour","Launaguet","Lespinasse","Mondonville","Mondouzil","Mons","Montrabé","Pibrac","Pin-Balma","Quint-Fonsegrives","Saint-Alban","Saint-Jean","Saint-Jory","Saint-Orens-de-Gameville","Seilh","Tournefeuille","L'Union","Villeneuve-Tolosane"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, communes);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                commune = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        LinearLayout next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commune !=null && !commune.equals("Commune...")){
                    if(commune.equals("Toulouse")){
                        Intent add_intent = new Intent(main_menu.this, MainActivity_Toulouse.class);
                        add_intent.putExtra("commune",commune);
                        add_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(add_intent);
                    }else{
                        Intent add_intent = new Intent(main_menu.this, MainActivity_Metropole.class);
                        add_intent.putExtra("commune",commune);
                        add_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(add_intent);
                    }
                }else{
                    Toast.makeText(main_menu.this, "Veuillez choisir une commune", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

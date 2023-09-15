package com.tornexis.allotoulouse.encombrants;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tornexis.allotoulouse.R;

import java.util.Calendar;

public class encombrant_popup extends AppCompatActivity {
    public static int month;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_encombrant);
        setFinishOnTouchOutside(false);

        TextView date_dv_value = findViewById(R.id.confirmation_rdv_encombrant);
        String date = getIntent().getStringExtra("date");
        date_dv_value.setText(date);

        String adresse = getIntent().getStringExtra("adresse");
        TextView adresse_dv_value = findViewById(R.id.adresse_encombrant_value);
        adresse_dv_value.setText(adresse);

        Integer nb_armoire = getIntent().getIntExtra("nb_armoire", 0);
        Integer nb_bricolage = getIntent().getIntExtra("nb_bricolage",0);
        Integer nb_canape = getIntent().getIntExtra("nb_canape",0);
        Integer nb_deco = getIntent().getIntExtra("nb_deco",0);
        Integer nb_electromenager = getIntent().getIntExtra("nb_electromenager",0);
        Integer nb_etagere = getIntent().getIntExtra("nb_etagere",0);
        Integer nb_fauteuil = getIntent().getIntExtra("nb_fauteuil",0);
        Integer nb_frigo = getIntent().getIntExtra("nb_frigo",0);
        Integer nb_frigo_americain = getIntent().getIntExtra("nb_frigo_americain",0);
        Integer nb_gros_volume = getIntent().getIntExtra("nb_gros_volume",0);
        Integer nb_informatique = getIntent().getIntExtra("nb_informatique",0);
        Integer nb_lit = getIntent().getIntExtra("nb_lit",0);
        Integer nb_machines = getIntent().getIntExtra("nb_machines",0);
        Integer nb_metallique = getIntent().getIntExtra("nb_metallique",0);
        Integer nb_moyen_volume = getIntent().getIntExtra("nb_moyen_volume",0);
        Integer nb_petit_meuble = getIntent().getIntExtra("nb_petit_meuble",0);
        Integer nb_petit_volume = getIntent().getIntExtra("nb_petit_volume",0);

        TextView encombrant_value = findViewById(R.id.encombrant_value);
        StringBuilder encombrantText = new StringBuilder();
        if(nb_canape !=0){
            encombrantText.append("Canapé :"+nb_canape+"\n");
        }
        if(nb_fauteuil !=0){
            encombrantText.append("Fauteuil :"+nb_fauteuil+"\n");
        }
        if(nb_lit !=0 ){
            encombrantText.append("Matelas/Sommier/Lit :"+nb_lit+"\n");
        }
        if(nb_etagere !=0){
            encombrantText.append("Etagères / Table démontée :"+nb_etagere+"\n");
        }
        if(nb_armoire !=0){
            encombrantText.append("Armoirev/ Bibliothèque :"+nb_armoire+"\n");
        }
        if(nb_petit_meuble !=0){
            encombrantText.append("Petit meuble :"+nb_petit_meuble+"\n");
        }
        if(nb_frigo_americain !=0){
            encombrantText.append("Réfrigérateur américain :"+nb_frigo_americain+"\n");
        }
        if(nb_frigo !=0){
            encombrantText.append("Réfrigérateur :"+nb_frigo+"\n");
        }
        if(nb_machines !=0){
            encombrantText.append("Machine à laver/ Lave-linge / Four... :"+nb_machines+"\n");
        }
        if(nb_electromenager !=0){
            encombrantText.append("Electroménager moyen:"+nb_electromenager+"\n");
        }
        if(nb_informatique !=0){
            encombrantText.append("Equipement informatique,audio,vidéo,... :"+nb_informatique+"\n");
        }
        if(nb_petit_volume !=0){
            encombrantText.append("Activités de loisir / Petits volumes :"+nb_petit_volume+"\n");
        }
        if(nb_moyen_volume !=0){
            encombrantText.append("Activités de loisir / Moyens volumes :"+nb_moyen_volume+"\n");
        }
        if(nb_gros_volume !=0){
            encombrantText.append("Activités de loisir / Gros volumes :"+nb_gros_volume+"\n");
        }
        if(nb_bricolage !=0){
            encombrantText.append("Equipement de bricolage :"+nb_bricolage+"\n");
        }
        if(nb_deco !=0){
            encombrantText.append("Décoration intérieure :"+nb_deco+"\n");
        }
        if(nb_metallique !=0){
            encombrantText.append("Mobilier métallique :"+nb_metallique+"\n");
        }
        if(encombrantText.length()>0) {
            encombrant_value.setText(encombrantText.toString());
        }else{
            encombrant_value.setText("Aucun encombrant sélectionné");
        }

        Button close = findViewById(R.id.close_encombrant_popup);
        close.setOnClickListener(v->{
            finish();
        });

        String[] date_temp = date.split("[ \n]");

        ImageButton agenda = findViewById(R.id.agenda_link_button);
        agenda.setOnClickListener(v->{

            switch (date_temp[2]) {
                case "Janvier":
                    month = 0;
                    break;
                case "Février":
                    month = 1;
                    break;
                case "Mars":
                    month = 2;
                    break;
                case "Avril":
                    month = 3;
                    break;
                case "Mai":
                    month = 4;
                    break;
                case "Juin":
                    month = 5;
                    break;
                case "Juillet":
                    month = 6;
                    break;
                case "Août":
                    month = 7;
                    break;
                case "Septembre":
                    month = 8;
                    break;
                case "Octobre":
                    month = 9;
                    break;
                case "Novembre":
                    month = 10;
                    break;
                case "Décembre":
                    month = 11;
                    break;
                default:
                    month = -1;
            }


            Calendar beginTime = Calendar.getInstance();
            beginTime.set(Integer.parseInt(date_temp[3]), month, Integer.parseInt(date_temp[1]), 5, 00);
            Calendar endTime = Calendar.getInstance();
            endTime.set(Integer.parseInt(date_temp[3]), month, Integer.parseInt(date_temp[1]), 16, 00);
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                    .putExtra(CalendarContract.Events.TITLE, "RDV Encombrants")
                    .putExtra(CalendarContract.Events.DESCRIPTION, encombrantText.toString())
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, adresse)
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            startActivity(intent);
        });
    }
}

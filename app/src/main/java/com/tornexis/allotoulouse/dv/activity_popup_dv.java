package com.tornexis.allotoulouse.dv;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tornexis.allotoulouse.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class activity_popup_dv extends AppCompatActivity {
    public static int month;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_dv);
        setFinishOnTouchOutside(false);

        TextView date_dv_value = findViewById(R.id.confirmation_rdv_dv);
        String date = getIntent().getStringExtra("date");
        date_dv_value.setText(date);

        String adresse = getIntent().getStringExtra("adresse");
        TextView adresse_dv_value = findViewById(R.id.adresse_dv_value);
        adresse_dv_value.setText(adresse);

        Integer num_sac = getIntent().getIntExtra("nb_sacs", 0);
        Integer num_fagot = getIntent().getIntExtra("nb_fagots",0);

        TextView dechet_dv_value = findViewById(R.id.dechet_dv_value);
        if(num_sac!=0 && num_fagot !=0 ){
            dechet_dv_value.setText(num_sac+" sacs\n"+num_fagot+" fagots");

        }else if(num_sac!=0 && num_fagot ==0){
            dechet_dv_value.setText(num_sac+" sacs");
        }else {
            dechet_dv_value.setText(num_fagot+" sacs");
        }

        Button close = findViewById(R.id.close_dv_popup);
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
                    .putExtra(CalendarContract.Events.TITLE, "RDV Déchets Verts")
                    .putExtra(CalendarContract.Events.DESCRIPTION, num_sac+"sacs et "+num_fagot+" fagots")
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, adresse)
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            startActivity(intent);
        });
    }
}

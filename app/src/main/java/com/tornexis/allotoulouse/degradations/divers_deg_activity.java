package com.tornexis.allotoulouse.degradations;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.tornexis.allotoulouse.R;

import java.io.IOException;

public class divers_deg_activity extends AppCompatActivity {
    String description = null;
    private ActivityResultLauncher<String> pickImageLauncher;
    private ActivityResultLauncher<Intent> captureImageLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deg_divers);
        Intent parentIntent = NavUtils.getParentActivityIntent(this);
        Context context = this;

        pickImageLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null) {
                            ImageView deg_divers_photo_1_image = findViewById(R.id.deg_divers_photo_1_image);
                            deg_divers_photo_1_image.setImageURI(result);
                        }
                    }
                });

        // Initialisation du lanceur pour la capture de photo avec la caméra
        captureImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if(data!=null){
                                ContentResolver contentResolver = context.getContentResolver();
                                Uri imageUri = data.getData();
                                Bitmap bitmap = null;
                                Bitmap resizedBitmap = null;
                                try {

                                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri);
                                    resizedBitmap = Bitmap.createScaledBitmap(bitmap, 270, 400, true);

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                ImageView deg_divers_photo_1_image = findViewById(R.id.deg_divers_photo_1_image);
                                deg_divers_photo_1_image.setImageBitmap(resizedBitmap);

                            }
                            Bundle extras = result.getData().getExtras();
                            if (extras != null) {
                                Bitmap imageBitmap = (Bitmap) extras.get("data");
                                if (imageBitmap != null) {
                                    ImageView deg_divers_photo_1_image = findViewById(R.id.deg_divers_photo_1_image);
                                    deg_divers_photo_1_image.setImageBitmap(imageBitmap);
                                }
                            }
                        }
                    }
        });


        Button next = findViewById(R.id.validate_deg_divers);
        EditText description_deg_divers = findViewById(R.id.description_deg_divers);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description = description_deg_divers.getText().toString();
                if(!description.isEmpty()){
                    if (parentIntent != null) {
                        startActivity(parentIntent);
                        finish();
                        Toast.makeText(divers_deg_activity.this, "Votre signalement a été transmis", Toast.LENGTH_SHORT).show();
                    } else {
                        finish();
                    }


                }else{
                    Toast.makeText(divers_deg_activity.this, "Veuillez décrire la dégradation", Toast.LENGTH_SHORT).show();

                }
            }
        });

        ImageButton return_accueil_button = findViewById(R.id.return_accueil_button_encombrant);
        return_accueil_button.setOnClickListener(v->{
            if (parentIntent != null) {
                startActivity(parentIntent);
                finish();
            } else {
                finish();
            }
        });
    }

    public void onPhotoClick(View view) {
        Intent chooseImageIntent = new Intent();
        chooseImageIntent.setType("image/*");
        chooseImageIntent.setAction(Intent.ACTION_GET_CONTENT); // Pour choisir depuis la galerie

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooserIntent = Intent.createChooser(chooseImageIntent, "Choisissez une image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePhotoIntent, chooseImageIntent});


        captureImageLauncher.launch(chooserIntent);

    }

}

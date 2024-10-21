package com.example.SPQRisiko;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FirstPageActivity extends AppCompatActivity {

    private EditText editTextName1, editTextName2, editTextName3, editTextName4, editTextName5;
    private Button buttonSubmit, buttonColorPicker;
    private int selectedColor = Color.BLACK; // Colore predefinito

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        editTextName1 = findViewById(R.id.editTextName1);
        editTextName2 = findViewById(R.id.editTextName2);
        editTextName3 = findViewById(R.id.editTextName3);
        editTextName4 = findViewById(R.id.editTextName4);
        editTextName5 = findViewById(R.id.editTextName5);
        buttonSubmit = findViewById(R.id.buttonSubmit);
//        buttonColorPicker = findViewById(R.id.buttonColorPicker); // Aggiungi questo

  //      buttonColorPicker.setOnClickListener(new View.OnClickListener() {
    //        @Override
      //      public void onClick(View v) {
        //        showColorPickerDialog();
          //  }
       // });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFieldsFilled(editTextName1, editTextName2, editTextName3, editTextName4, editTextName5)) {
                    String name1 = editTextName1.getText().toString();
                    String name2 = editTextName2.getText().toString();
                    String name3 = editTextName3.getText().toString();
                    String name4 = editTextName4.getText().toString();
                    String name5 = editTextName5.getText().toString();

                    Intent intent = new Intent(FirstPageActivity.this, GenerationOfRandomProvincesActivity.class);
                    intent.putExtra("name1", name1);
                    intent.putExtra("name2", name2);
                    intent.putExtra("name3", name3);
                    intent.putExtra("name4", name4);
                    intent.putExtra("name5", name5);
              //      intent.putExtra("selectedColor", selectedColor); // Invia il colore selezionato
                    startActivity(intent);
                } else {
                    Toast.makeText(FirstPageActivity.this, getString(R.string.please_enter_all_names), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Metodo per verificare se tutti i campi di testo sono riempiti
    private boolean isAllFieldsFilled(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().isEmpty()) {
                return false;  // Se uno dei campi è vuoto, ritorna false
            }
        }
        return true;  // Se tutti i campi sono pieni, ritorna true
    }

    // Mostra il dialogo per la selezione del colore
    private void showColorPickerDialog() {
        final String[] colors = {"Rosso", "Giallo", "Verde", "Blu", "Nero"};
        final int[] colorValues = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.BLACK};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scegli un Colore")
                .setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Salva il colore selezionato
                        selectedColor = colorValues[which];
                        // Facoltativo: Mostra un messaggio per confermare il colore selezionato
                        Toast.makeText(FirstPageActivity.this, "Colore selezionato: " + colors[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}

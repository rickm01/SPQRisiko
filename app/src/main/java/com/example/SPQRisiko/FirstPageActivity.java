package com.example.SPQRisiko;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstPageActivity extends AppCompatActivity {

    private EditText editTextName1, editTextName2, editTextName3, editTextName4, editTextName5;
    private Button buttonSubmit, buttonReset;
    private Map<Integer, String> buttonColorMap; // Mappa per tenere traccia del colore di ciascun pulsante
    private List<String> availableColors; // Lista dei colori disponibili

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
        buttonReset = findViewById(R.id.buttonReset); // Inizializzazione del pulsante Reset

        // Inizializzazione della mappa per tenere traccia dei colori dei pulsanti
        buttonColorMap = new HashMap<>();
        // Inizializzazione della lista dei colori disponibili
        availableColors = new ArrayList<>();
        availableColors.add("Rosso");
        availableColors.add("Verde");
        availableColors.add("Blu");
        availableColors.add("Giallo");
        availableColors.add("Nero");

        // Imposta gli OnClickListener per ogni pulsante colore
        setupColorButton(R.id.colorButton1);
        setupColorButton(R.id.colorButton2);
        setupColorButton(R.id.colorButton3);
        setupColorButton(R.id.colorButton4);
        setupColorButton(R.id.colorButton5);

        // Listener per il pulsante di invio
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFieldsFilled(editTextName1, editTextName2, editTextName3, editTextName4, editTextName5)) {
                    String name1 = editTextName1.getText().toString();
                    String name2 = editTextName2.getText().toString();
                    String name3 = editTextName3.getText().toString();
                    String name4 = editTextName4.getText().toString();
                    String name5 = editTextName5.getText().toString();

                    // Recupera i colori selezionati
                    String color1 = buttonColorMap.get(R.id.colorButton1);
                    String color2 = buttonColorMap.get(R.id.colorButton2);
                    String color3 = buttonColorMap.get(R.id.colorButton3);
                    String color4 = buttonColorMap.get(R.id.colorButton4);
                    String color5 = buttonColorMap.get(R.id.colorButton5);

                    Intent intent = new Intent(FirstPageActivity.this, GenerationOfRandomProvincesActivity.class);
                    intent.putExtra("name1", name1);
                    intent.putExtra("name2", name2);
                    intent.putExtra("name3", name3);
                    intent.putExtra("name4", name4);
                    intent.putExtra("name5", name5);

                    // Passa i colori selezionati all'intent
                    intent.putExtra("color1", color1);
                    intent.putExtra("color2", color2);
                    intent.putExtra("color3", color3);
                    intent.putExtra("color4", color4);
                    intent.putExtra("color5", color5);

                    startActivity(intent);
                } else {
                    Toast.makeText(FirstPageActivity.this, getString(R.string.please_enter_all_names), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener per il pulsante di reset
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields(); // Chiamata al metodo di reset
            }
        });
    }

    // Imposta l'OnClickListener per i pulsanti colore
    private void setupColorButton(int buttonId) {
        findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorPopup(v, buttonId);
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

    private void showColorPopup(View view, int buttonId) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        // Aggiunge solo i colori disponibili al popup
        for (String color : availableColors) {
            popupMenu.getMenu().add(color);
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String selectedColor = item.getTitle().toString();
                Toast.makeText(FirstPageActivity.this, "Hai scelto: " + selectedColor, Toast.LENGTH_SHORT).show();

                // Cambia il colore di sfondo del pulsante in base alla selezione usando i valori esadecimali
                int colorValue = getColorValue(selectedColor);
                view.setBackgroundColor(colorValue);

                // Ripristina il colore precedente, se presente
                String previousColor = buttonColorMap.get(buttonId);
                if (previousColor != null) {
                    availableColors.add(previousColor); // Aggiunge il colore precedente alla lista
                }

                // Salva il colore selezionato nella mappa e rimuovilo dalla lista dei disponibili
                buttonColorMap.put(buttonId, selectedColor);
                availableColors.remove(selectedColor); // Rimuove il colore dalla lista dei disponibili

                return true;
            }
        });

        // Mostra il popup
        popupMenu.show();
    }

    // Metodo per ottenere il valore esadecimale del colore selezionato
    private int getColorValue(String colorName) {
        switch (colorName) {
            case "Rosso":
                return 0xFFFF0000; // Rosso
            case "Verde":
                return 0xFF00FF00; // Verde
            case "Blu":
                return 0xFF0000FF; // Blu
            case "Giallo":
                return 0xFFFFFF00; // Giallo
            case "Nero":
                return 0xFF000000; // Nero
            default:
                return Color.TRANSPARENT; // Colore trasparente di default
        }
    }

    // Metodo per ripristinare tutti i campi
    private void resetFields() {
        editTextName1.setText("");
        editTextName2.setText("");
        editTextName3.setText("");
        editTextName4.setText("");
        editTextName5.setText("");

        // Ripristina i colori dei pulsanti
        for (int buttonId : buttonColorMap.keySet()) {
            View colorButton = findViewById(buttonId);
            colorButton.setBackgroundColor(Color.TRANSPARENT); // Imposta il colore di sfondo trasparente
        }

        // Ripristina la lista dei colori disponibili
        availableColors.clear();
        availableColors.add("Rosso");
        availableColors.add("Verde");
        availableColors.add("Blu");
        availableColors.add("Giallo");
        availableColors.add("Nero");

        // Pulisce la mappa dei colori
        buttonColorMap.clear();
    }
}

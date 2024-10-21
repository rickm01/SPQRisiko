package com.example.SPQRisiko;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondPageActivity extends AppCompatActivity {

   // private View colorBox; // Box per il colore selezionato
    private View colorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        colorButton = findViewById(R.id.colorButton);
     //   colorBox = findViewById(R.id.colorBox);

        // Imposta un listener sul pulsante
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorPopup(v);
            }
        });
    }

    private void showColorPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenu().add("Rosso");
        popupMenu.getMenu().add("Verde");
        popupMenu.getMenu().add("Blu");
        popupMenu.getMenu().add("Giallo");
        popupMenu.getMenu().add("Nero");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String colorName = item.getTitle().toString();
                Toast.makeText(SecondPageActivity.this, "Hai scelto: " + colorName, Toast.LENGTH_SHORT).show();

                // Cambia il colore di sfondo del pulsante in base alla selezione usando i valori esadecimali
                switch (colorName) {
                    case "Rosso":
                        colorButton.setBackgroundColor(0xFFFF0000); // Rosso
                        break;
                    case "Verde":
                        colorButton.setBackgroundColor(0xFF00FF00); // Verde
                        break;
                    case "Blu":
                        colorButton.setBackgroundColor(0xFF0000FF); // Blu
                        break;
                    case "Giallo":
                        colorButton.setBackgroundColor(0xFFFFFF00); // Giallo
                        break;
                    case "Nero":
                        colorButton.setBackgroundColor(0xFF000000); // Nero
                        break;
                }

                return true;
            }
        });

        // Mostra il popup
        popupMenu.show();
    }
}
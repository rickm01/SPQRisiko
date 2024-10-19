package com.example.SPQRisiko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Imposta la lingua in base alle preferenze salvate
        setLocale(getSavedLocale());

        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button buttonSettings = findViewById(R.id.buttonSettings);

        // Imposta il comportamento per Button 1
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FirstPageActivity.class);
            startActivity(intent);
        });

        // Imposta il comportamento per Button 2
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondPageActivity.class);
            startActivity(intent);
        });

        // Imposta il comportamento per il pulsante delle impostazioni
        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingPageActivity.class);
            startActivityForResult(intent, 1); // Usa startActivityForResult per aspettare il risultato
        });
    }

    // Ricevi il risultato dalla SettingPageActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            boolean languageChanged = data.getBooleanExtra("LanguageChanged", false);
            if (languageChanged) {
                Log.d("MainActivity", "Language changed, recreating activity...");
                recreate();  // Forza la ricreazione immediata della MainActivity
            }
        }
    }


    private void setLocale(String lang) {
        LocaleHelper.setLocale(this, lang);
    }

    private String getSavedLocale() {
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String savedLanguage = preferences.getString("Language", "it");
        Log.d("MainActivity", "Saved language: " + savedLanguage);
        return savedLanguage;
    }


}

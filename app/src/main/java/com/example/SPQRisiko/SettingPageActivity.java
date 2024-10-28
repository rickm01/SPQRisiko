package com.example.SPQRisiko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class SettingPageActivity extends AppCompatActivity {

    private RadioGroup radioGroupLanguages;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioGroupLanguages = findViewById(R.id.radioGroupLanguages);
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);

        // Carica la lingua salvata
        String savedLanguage = sharedPreferences.getString("Language", "it");

        // Seleziona il RadioButton in base alla lingua salvata
        if (savedLanguage.equals("it")) {
            radioGroupLanguages.check(R.id.radioItalian);
        } else {
            radioGroupLanguages.check(R.id.radioEnglish);
        }

        // Imposta listener per il RadioGroup
        radioGroupLanguages.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String selectedLanguage = radioButton.getText().toString();

            // Aggiungi un log per vedere quale lingua è stata selezionata
            Log.d("SettingPageActivity", "Selected language: " + selectedLanguage);

            // Salva la lingua selezionata nelle SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String langCode = selectedLanguage.contains("Italian") ? "it" : "en";
            Log.d("TAG", "langCode="+ langCode);
            editor.putString("Language", langCode);
            editor.apply();

            // Cambia subito la lingua
            LocaleHelper.setLocale(this, langCode);

            // Restituisci il risultato alla MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("LanguageChanged", true);
            setResult(RESULT_OK, resultIntent);
            Log.d("SettingPageActivity", "Language changed to: " + langCode);

            // Chiudi l'attività e torna alla MainActivity
            finish();
        });
    }
}

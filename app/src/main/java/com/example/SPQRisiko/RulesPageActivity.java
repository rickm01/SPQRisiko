package com.example.SPQRisiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import io.noties.markwon.Markwon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RulesPageActivity extends AppCompatActivity {
    private TextView textViewRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        textViewRules = findViewById(R.id.textViewRules);

        // Ottieni la lingua selezionata
        String language = getSelectedLanguage(); // implementa questo metodo
        String filename = language.equals("it") ? "rules-it.md" : "rules-en.md";

        // Carica e visualizza il file Markdown
        String rulesText = loadMarkdownFile(filename);
        Markwon markwon = Markwon.create(this);
        markwon.setMarkdown(textViewRules, rulesText);
    }

    private String loadMarkdownFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = getAssets().open(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private String getSelectedLanguage() {
        // Implementa la logica per ottenere la lingua selezionata (es. dal tuo sistema di impostazioni)
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String savedLanguage = preferences.getString("Language", "it");
        Log.d("LanguageSetted", savedLanguage);
        return savedLanguage; // Imposta la lingua predefinita
    }
}

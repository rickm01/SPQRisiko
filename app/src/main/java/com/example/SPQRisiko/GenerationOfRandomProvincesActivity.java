package com.example.SPQRisiko;

import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerationOfRandomProvincesActivity extends AppCompatActivity {

    // Definizione delle TextView per i nomi e le province dei giocatori
    private TextView player1Name, player2Name, player3Name, player4Name, player5Name;
    private TextView player1Provinces, player2Provinces, player3Provinces, player4Provinces, player5Provinces;

    // Definizione delle province disponibili
    private String[] allProvinces = {
            "Bithinya", "Ponto", "Armenia", "Cappadocia", "Galatia", "Asia", "Cilicia", "Syria", "Mesopotamia", "Judea", "Arabia", "Aegyptus", "Cyrenaica", "Numidia", "Africa", "Mauretania",
            "Britannia", "Galicia", "Baetica", "Lusitania", "Terraconensis", "Aquitania", "Narbonensis", "Lugdunense", "Belgica", "GermaniaInferior", "Raetia", "Cisalpina", "Italia", "Baleares",
            "Corsica", "Sardinia", "Sicilia", "Creta", "Cipro", "Noricum", "Illiria", "Dalmazia", "Pannonia", "Dacia", "Moesia", "Thracia", "Macedonia", "Epirus", "Achaia"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showConfirmationDialog();
            }
        });

        // Collegamento delle TextView del layout
        player1Name = findViewById(R.id.player1Name);
        player2Name = findViewById(R.id.player2Name);
        player3Name = findViewById(R.id.player3Name);
        player4Name = findViewById(R.id.player4Name);
        player5Name = findViewById(R.id.player5Name);

        player1Provinces = findViewById(R.id.player1Provinces);
        player2Provinces = findViewById(R.id.player2Provinces);
        player3Provinces = findViewById(R.id.player3Provinces);
        player4Provinces = findViewById(R.id.player4Provinces);
        player5Provinces = findViewById(R.id.player5Provinces);

        String title = "";

        // Ricevi i nomi e i colori dall'activity precedente
        String name1 = title + getIntent().getStringExtra("name1");
        String name2 = title + getIntent().getStringExtra("name2");
        String name3 = title + getIntent().getStringExtra("name3");
        String name4 = title + getIntent().getStringExtra("name4");
        String name5 = title + getIntent().getStringExtra("name5");

        String color1 = getIntent().getStringExtra("color1");
        String color2 = getIntent().getStringExtra("color2");
        String color3 = getIntent().getStringExtra("color3");
        String color4 = getIntent().getStringExtra("color4");
        String color5 = getIntent().getStringExtra("color5");

        // Imposta i nomi nei TextView
        player1Name.setText(getString(R.string.provinces_of_player, name1));
        player2Name.setText(getString(R.string.provinces_of_player, name2));
        player3Name.setText(getString(R.string.provinces_of_player, name3));
        player4Name.setText(getString(R.string.provinces_of_player, name4));
        player5Name.setText(getString(R.string.provinces_of_player, name5));

        // Imposta i colori delle card
        setCardColor(R.id.color1, color1);
        setCardColor(R.id.color2, color2);
        setCardColor(R.id.color3, color3);
        setCardColor(R.id.color4, color4);
        setCardColor(R.id.color5, color5);

        // Ottieni le province corrispondenti ai nomi
        List<String> provinces = getProvinces();

        // Divide le province per ogni giocatore e le imposta nei rispettivi TextView
        setProvincesForPlayer(player1Provinces, provinces.subList(0, 9));
        setProvincesForPlayer(player2Provinces, provinces.subList(9, 18));
        setProvincesForPlayer(player3Provinces, provinces.subList(18, 27));
        setProvincesForPlayer(player4Provinces, provinces.subList(27, 36));
        setProvincesForPlayer(player5Provinces, provinces.subList(36, 45));
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.confirm_exit_title))
                .setMessage(getString(R.string.confirm_exit_message))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Chiudi l'activity se l'utente conferma
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.no), null)  // Annulla l'azione se preme "No"
                .show();
    }

    // Funzione per assegnare le province a un giocatore
    private void setProvincesForPlayer(TextView playerProvincesView, List<String> playerProvinces) {
        StringBuilder provincesString = new StringBuilder();
        for (String province : playerProvinces) {
            provincesString.append(province).append("\n");
        }
        playerProvincesView.setText(provincesString.toString());
    }

    // Funzione per generare le province casuali
    private List<String> getProvinces() {
        List<String> provinces = new ArrayList<>();
        List<String> allProvincesTemp = new ArrayList<>(Arrays.asList(allProvinces));

        // Utilizza un seed basato sul tempo per randomizzare l'ordine delle province
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        // Mescola e assegna le province
        while (!allProvincesTemp.isEmpty()) {
            int randomPos = random.nextInt(allProvincesTemp.size());
            provinces.add(allProvincesTemp.get(randomPos));
            allProvincesTemp.remove(randomPos);
        }

        int j = 0;
        for (int i = 0; i < provinces.size(); i++) {
            if (j == 9) {
                j = 0;
            }
            provinces.set(i, j + 1 + ". " + provinces.get(i));
            j++;
        }
        return provinces;
    }

    // Metodo per impostare il colore della card
    private void setCardColor(int cardViewId, String color) {
        View cardView = findViewById(cardViewId);
        if (cardView != null && color != null) {
            int colorValue = getColorValue(color);
            cardView.setBackgroundColor(colorValue);
        }
    }

    // Metodo per convertire il nome del colore in un valore intero
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
                return 0x00000000; // Colore trasparente di default
        }
    }
}

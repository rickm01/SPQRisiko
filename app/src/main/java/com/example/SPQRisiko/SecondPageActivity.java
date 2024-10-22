package com.example.SPQRisiko;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondPageActivity extends AppCompatActivity {

    private TextView challengeRed, challengeBlue, challengeGreen, challengeYellow, challengeBlack;
    private View showBlackChallengeButton, showRedChallengeButton, showBlueChallengeButton, showGreenChallengeButton, showYellowChallengeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showConfirmationDialog();
            }
        });
        showBlackChallengeButton = findViewById(R.id.showBlackChallengeButton);
        showRedChallengeButton = findViewById(R.id.showRedChallengeButton);
        showBlueChallengeButton = findViewById(R.id.showBlueChallengeButton);
        showGreenChallengeButton = findViewById(R.id.showGreenChallengeButton);
        showYellowChallengeButton = findViewById(R.id.showYellowChallengeButton);


        challengeBlack = findViewById(R.id.challenge_black);
        challengeRed = findViewById(R.id.challenge_red);
        challengeBlue = findViewById(R.id.challenge_blue);
        challengeGreen = findViewById(R.id.challenge_green);
        challengeYellow = findViewById(R.id.challenge_yellow);

        // ArrayList per le sfide già selezionate
        ArrayList<String> toDelete = new ArrayList<>();

        // Seleziona una sfida casuale per ogni colore e aggiungi alla lista da eliminare
        String temp;

        temp = getRandomChallenge(toDelete);
        challengeRed.setText(temp);
        toDelete.add(temp);

        temp = getRandomChallenge(toDelete);
        challengeBlue.setText(temp);
        toDelete.add(temp);

        temp = getRandomChallenge(toDelete);
        challengeGreen.setText(temp);
        toDelete.add(temp);

        temp = getRandomChallenge(toDelete);
        challengeYellow.setText(temp);
        toDelete.add(temp);

        temp = getRandomChallenge(toDelete);
        challengeBlack.setText(temp);
        toDelete.add(temp);

// Imposta i listener per i clic sui pulsanti
        showBlackChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (challengeBlack.getVisibility() == View.VISIBLE) {
                    challengeBlack.setVisibility(View.GONE);
                    ((Button) showBlackChallengeButton).setText(getString(R.string.show_challenge));
                } else {
                    challengeBlack.setVisibility(View.VISIBLE);
                    ((Button) showBlackChallengeButton).setText(getString(R.string.hide_challenge));
                }
            }
        });

        showRedChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (challengeRed.getVisibility() == View.VISIBLE) {
                    challengeRed.setVisibility(View.GONE);
                    ((Button) showRedChallengeButton).setText(getString(R.string.show_challenge));
                } else {
                    challengeRed.setVisibility(View.VISIBLE);
                    ((Button) showRedChallengeButton).setText(getString(R.string.hide_challenge));
                }
            }
        });

        showBlueChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (challengeBlue.getVisibility() == View.VISIBLE) {
                    challengeBlue.setVisibility(View.GONE);
                    ((Button) showBlueChallengeButton).setText(getString(R.string.show_challenge));
                } else {
                    challengeBlue.setVisibility(View.VISIBLE);
                    ((Button) showBlueChallengeButton).setText(getString(R.string.hide_challenge));
                }
            }
        });

        showGreenChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (challengeGreen.getVisibility() == View.VISIBLE) {
                    challengeGreen.setVisibility(View.GONE);
                    ((Button) showGreenChallengeButton).setText(getString(R.string.show_challenge));
                } else {
                    challengeGreen.setVisibility(View.VISIBLE);
                    ((Button) showGreenChallengeButton).setText(getString(R.string.hide_challenge));
                }
            }
        });

        showYellowChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (challengeYellow.getVisibility() == View.VISIBLE) {
                    challengeYellow.setVisibility(View.GONE);
                    ((Button) showYellowChallengeButton).setText(getString(R.string.show_challenge));
                } else {
                    challengeYellow.setVisibility(View.VISIBLE);
                    ((Button) showYellowChallengeButton).setText(getString(R.string.hide_challenge));
                }
            }
        });

    }

    private String getRandomChallenge(ArrayList<String> toDelete) {
        List<String> allChallenges = new ArrayList<>();

        allChallenges.add(getString(R.string.C1_dominate_the_empire));
        allChallenges.add(getString(R.string.C2_control_the_mediterranean));
        allChallenges.add(getString(R.string.C3_control_the_atlantic));
        allChallenges.add(getString(R.string.C4_roman_legacy));
        allChallenges.add(getString(R.string.C5_scipio_africanus));
        allChallenges.add(getString(R.string.C6_julius_caesar));
        allChallenges.add(getString(R.string.C7_paella_and_baguette));
        allChallenges.add(getString(R.string.C8_return_of_the_gladiator));
        allChallenges.add(getString(R.string.C9_mediterranean_islander));
        allChallenges.add(getString(R.string.C10_greek_emperor));
        allChallenges.add(getString(R.string.C11_great_sultan));
        allChallenges.add(getString(R.string.C12_the_trinity));
        allChallenges.add(getString(R.string.C13_the_pirate));
        allChallenges.add(getString(R.string.C14_the_warlord));
        allChallenges.add(getString(R.string.C15_mark_antony));

        // Rimuovi le sfide già selezionate
        allChallenges.removeAll(toDelete);

        // Controlla se ci sono sfide disponibili
        if (allChallenges.isEmpty()) {
            return "Nessuna sfida disponibile"; // Gestisci il caso in cui non ci siano sfide
        }

        Random random = new Random(System.currentTimeMillis());
        int index = random.nextInt(allChallenges.size()); // Ottiene un indice casuale
        return allChallenges.get(index); // Restituisce la sfida casuale
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

}

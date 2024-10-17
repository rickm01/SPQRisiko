package com.example.SPQRisiko;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerationOfRandomProvincesActivity extends AppCompatActivity {

    private TextView textViewProvinces;
    private String[] allProvinces = {
            "Bithinya", "Ponto", "Armenia", "Cappadocia", "Galatia", "Asia", "Cilicia", "Syria", "Mesopotamia", "Judea", "Arabia", "Aegyptus", "Cyrenaica", "Numidia", "Africa", "Mauretania", "Britannia", "Galicia", "Baetica", "Lusitania", "Terraconensis", "Aquitania", "Narbonensis", "Lugdunense", "Belgica", "GermaniaInferior", "Raetia", "Cisalpina", "Italia", "Baleares", "Corsica", "Sardinia", "Sicilia", "Creta", "Cipro", "Noricum", "Illiria", "Dalmazia", "Pannonia", "Dacia", "Moesia", "Thracia", "Macedonia", "Epirus", "Achaia"    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);

        textViewProvinces = findViewById(R.id.textViewProvinces);

        // Ricevi i nomi dall'activity precedente
        String name1 = getIntent().getStringExtra("name1");
        String name2 = getIntent().getStringExtra("name2");
        String name3 = getIntent().getStringExtra("name3");
        String name4 = getIntent().getStringExtra("name4");
        String name5 = getIntent().getStringExtra("name5");
        List<String> names = new ArrayList<String>();
        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);

        // Ottieni le province corrispondenti ai nomi
        List<String> provinces = getProvinces();

        // Mostra le province in un TextView
        StringBuilder provincesString = new StringBuilder();

        for(int i=0; i<5;i++){
            StringBuilder provinceGiocatore = new StringBuilder();
            provinceGiocatore.append("Province di ").append(names.get(i)).append("\n");
            for(int j=0;j<9;j++){
                provinceGiocatore.append(provinces.get(j+i*9)).append("\n");
            }
            provinceGiocatore.append("\n");
            provincesString.append(provinceGiocatore);
        }
/*        for (int i=0; i<provinces.size(); i++) {
            if(i%9==0){
                provincesString.append()
            }
            provincesString.append(provinces.get(i)).append("\n");
        }
  */
        textViewProvinces.setText(provincesString.toString());
    }

    private List<String> getProvinces() {
        List<String> provinces = new ArrayList<>();
        List<String> allProvincesTemp = new ArrayList<String>(Arrays.asList(allProvinces));
//        Log.d("TAG", "Dim = " + allProvincesTemp.size());
        //      Log.d("TAG", allProvincesTemp.toString());
        //    Log.d("TAG", Arrays.toString(allProvinces));

        long seed = System.currentTimeMillis(); // Ottieni il tempo attuale in millisecondi
        Random random = new Random(seed);

        while (!allProvincesTemp.isEmpty()){
            int randomPos = random.nextInt(allProvincesTemp.size());
            provinces.add(allProvincesTemp.get(randomPos));
            Log.d("TAG", allProvincesTemp.get(randomPos));
            allProvincesTemp.remove(randomPos);
            Log.d("TAG", allProvincesTemp.toString());
        }
        return provinces;
    }
}


     /* for (int baseIndex=0; baseIndex<5; baseIndex++) {
            //int baseIndex;//positions[j];//provinces.size() % positions.length] * 5; // Assicurati di ciclarli
            for (int i = 0; i < 9; i++) {
                provinces.add(allProvinces[baseIndex + i*5]);

            }
        }
*/

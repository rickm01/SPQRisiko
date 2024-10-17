package com.example.SPQRisiko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FirstPageActivity extends AppCompatActivity {

    private EditText editTextName1, editTextName2, editTextName3, editTextName4, editTextName5;
    private Button buttonSubmit;

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

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                startActivity(intent);
            }
        });
    }
}
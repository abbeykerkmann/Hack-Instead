package com.example.hackinstead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackinstead.R;

public class Results extends AppCompatActivity {

    Button save,home;
    TextView[] entries = new TextView[10];

    int excitement, intensity, nausea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String rideType = Enter_values.ride.getType().toLowerCase().replace(" ", "_");

        Cursor data = databaseAccess.getRideValuesfromType(rideType);
        if (data.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"Database is empty", Toast.LENGTH_SHORT).show();
        } else {
            excitement = data.getInt(1);
            intensity = data.getInt(2);
            nausea = data.getInt(3);
        }

        int rideValue = Calculation.getRideValue(Enter_values.ride.getExcitement(), excitement, Enter_values.ride.getIntensity(), intensity, Enter_values.ride.getNausea(), nausea);
        int finalRideValue = Calculation.getAdmissionValue(Enter_values.ride.getEntryFee(), Calculation.getExistingRideTypeValue(Enter_values.ride.getSameRideType(), rideValue));
        int[] ridePrices = Calculation.getAgeValue(finalRideValue);

        save = findViewById(R.id.button);
        home = findViewById(R.id.button3);

        entries[0] = findViewById(R.id.entry0);
        entries[1] = findViewById(R.id.entry1);
        entries[2] = findViewById(R.id.entry2);
        entries[3] = findViewById(R.id.entry3);
        entries[4] = findViewById(R.id.entry4);
        entries[5] = findViewById(R.id.entry5);
        entries[6] = findViewById(R.id.entry6);
        entries[7] = findViewById(R.id.entry7);
        entries[8] = findViewById(R.id.entry8);
        entries[9] = findViewById(R.id.entry9);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
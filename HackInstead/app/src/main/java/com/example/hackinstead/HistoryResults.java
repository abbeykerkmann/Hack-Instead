package com.example.hackinstead;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryResults extends AppCompatActivity {

    Button delete, home;
    TextView[] entries = new TextView[10];

    List<Integer[]> listOfModifiers = new ArrayList<Integer[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_results);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String rideType = Enter_values.ride.getType().toLowerCase().replace(" ", "_");
        Log.i("INFO", rideType);

        Cursor data = databaseAccess.getRideValuesFromType(rideType);
        if (data.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"Database is empty", Toast.LENGTH_SHORT).show();
        } else {
            while(data.moveToNext()) {
                listOfModifiers.add(new Integer[] {
                        Integer.parseInt(data.getString(0)),
                        Integer.parseInt(data.getString(1)),
                        Integer.parseInt(data.getString(2))
                });
            }
        }

        int rideValue = Calculation.getRideValue(Enter_values.ride.getExcitement(), listOfModifiers.get(0)[0], Enter_values.ride.getIntensity(), listOfModifiers.get(0)[1], Enter_values.ride.getNausea(), listOfModifiers.get(0)[2]);
        Log.i("rideValue", String.valueOf(rideValue));
        int finalRideValue = Calculation.getAdmissionValue(Enter_values.ride.getEntryFee(), Calculation.getExistingRideTypeValue(Enter_values.ride.getSameRideType(), rideValue));
        Log.i("finalRideValue", String.valueOf(finalRideValue));
        int[] ridePrices = Calculation.getAgeValue(finalRideValue);

        double[] updatedRidePrices = new double[ridePrices.length];

        for(int i = 0; i < ridePrices.length; i++) {
            updatedRidePrices[i] = Calculation.getMaxPrice(ridePrices[i]);
            Log.i("updatedRidePrices", String.valueOf(updatedRidePrices[i]));
        }

        delete = findViewById(R.id.delete_button);
        home = findViewById(R.id.home_button);

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

        for(int i = 0; i < entries.length; i++) {
            Log.i("INFO", String.valueOf(updatedRidePrices[i]));
            entries[i].setText(String.valueOf(updatedRidePrices[i]));
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFromDatabase();
                goToMainActivity();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void removeFromDatabase() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        boolean deleted = databaseAccess.deleteRideData(Enter_values.ride.getName());
        if(!deleted) {
            Toast.makeText(getApplicationContext(), "Unable to delete from the database, please try again.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
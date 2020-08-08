package com.example.hackinstead;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Rating;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enter_values extends AppCompatActivity {

    private String rideTypeValue = "";
    private double excitementValue = 0, intensityValue = 0, nauseaValue = 0;
    private boolean isSameRide = false, isEntryFee = false;

    public Rides ride;

    List<String> rideNames = new ArrayList<>();

    Button back, submit;
    CheckBox sameRide, entryFee;
    EditText excitement, intensity, nausea;
    Spinner rideType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_values);

        rideType = findViewById(R.id.ride_type);

        back = findViewById(R.id.back_button);
        submit = findViewById(R.id.submit_button);

        sameRide = findViewById(R.id.same_ride);
        entryFee = findViewById(R.id.entry_fee);

        excitement = findViewById(R.id.excitement);
        intensity = findViewById(R.id.intensity);
        nausea = findViewById(R.id.nausea);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Cursor data = databaseAccess.getRideNames();
        if (data.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"Database is empty", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                rideNames.add(data.getString(0));
                databaseAccess.close();
            }
        }

        for(int i = 0; i < rideNames.size(); i++) {
            String current = rideNames.get(i);
            String[] updated = current.split("_");
            for(int j = 0; j < updated.length; j++)
                updated[j] = updated[j].substring(0, 1).toUpperCase() + updated[j].substring(1);
            rideNames.set(i, TextUtils.join(" ", updated));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, rideNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rideType.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToResults();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToResults() {
        if(rideType.getSelectedItem() == null) {
            Toast.makeText(getApplicationContext(), "Ride type cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            rideTypeValue = rideType.getSelectedItem().toString();
        if(excitement.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Excitement cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            excitementValue = Double.parseDouble(excitement.getText().toString());
        if(intensity.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Intensity cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            intensityValue = Double.parseDouble(intensity.getText().toString());
        if(nausea.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nausea cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            nauseaValue = Double.parseDouble(nausea.getText().toString());
        if(sameRide.isChecked())
            isSameRide = true;
        if(entryFee.isChecked())
            isEntryFee = true;

        ride = new Rides(rideTypeValue, excitementValue, intensityValue, nauseaValue, isSameRide, isEntryFee);

        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }
}
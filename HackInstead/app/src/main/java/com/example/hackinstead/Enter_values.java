package com.example.hackinstead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Enter_values extends AppCompatActivity {

    public String rideTypeValue = "";
    public double excitementValue = 0, intensityValue = 0, nauseaValue = 0;
    public boolean isSameRide = false, isEntryFee = false;

    List<String> listOfRides = new ArrayList<>();

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

        Cursor data = databaseAccess.getRides();
        if (data.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"Database is empty", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                listOfRides.add(data.getString(0));
                databaseAccess.close();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listOfRides);
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
        if(excitement.getText().toString() == "") {
            Toast.makeText(getApplicationContext(), "Excitement cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            excitementValue = Double.parseDouble(excitement.getText().toString());
        if(intensity.getText() == null) {
            Toast.makeText(getApplicationContext(), "Intensity cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            intensityValue = Double.parseDouble(intensity.getText().toString());
        if(nausea.getText() == null) {
            Toast.makeText(getApplicationContext(), "Nausea cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            nauseaValue = Double.parseDouble(nausea.getText().toString());
        if(sameRide.isChecked())
            isSameRide = true;
        if(entryFee.isChecked())
            isEntryFee = true;
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }
}
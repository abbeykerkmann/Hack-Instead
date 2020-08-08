package com.example.hackinstead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class Enter_values extends AppCompatActivity {

    public String rideTypeValue = "";
    public double excitementValue = 0, intensityValue = 0, nauseaValue = 0;
    public boolean isSameRide = false, isEntryFee = false;

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
        //rideTypeValue = rideType.getSelectedItem().toString();
        excitementValue = Double.parseDouble(excitement.getText().toString());
        intensityValue = Double.parseDouble(intensity.getText().toString());
        nauseaValue = Double.parseDouble(nausea.getText().toString());
        if(sameRide.isChecked())
            isSameRide = true;
        if(entryFee.isChecked())
            isEntryFee = true;
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }
}
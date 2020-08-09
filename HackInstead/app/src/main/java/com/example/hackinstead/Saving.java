package com.example.hackinstead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Saving extends AppCompatActivity {

    EditText name;

    Button submit;

    String savedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        name = findViewById(R.id.custom_name);

        submit = findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDatabase();
                goToMainActivity();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void saveToDatabase() {
        if(name.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Name cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            savedName = name.getText().toString();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Cursor data = databaseAccess.findExistingName(savedName);
        if (data.getCount() > 0) {
            Toast.makeText(getApplicationContext(), "This save name already exists, please enter a different name.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean saved = databaseAccess.saveRideData(savedName, Enter_values.ride.getType(), Enter_values.ride.getExcitement(), Enter_values.ride.getIntensity(), Enter_values.ride.getNausea(), Enter_values.ride.getSameRideType(), Enter_values.ride.getEntryFee());
        if(!saved) {
            Toast.makeText(getApplicationContext(), "Unable to save to the database, please try again.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
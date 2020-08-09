package com.example.hackinstead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity implements RidesRVAdapter.ItemClickListener{

    Button back;

    private RecyclerView recyclerView;
    private RidesRVAdapter ridesRVAdapter;

    List<String> listOfRides = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor data = databaseAccess.getHistory();
        if (data.getCount() == 0){
            Toast.makeText(getApplicationContext(),"No records exist!", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()){
                listOfRides.add(data.getString(0));
                Log.i("Ride", String.valueOf(listOfRides.size()));
            }
        }
        databaseAccess.close();

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ridesRVAdapter = new RidesRVAdapter(this, listOfRides);
        ridesRVAdapter.setClickListener(this);
        recyclerView.setAdapter(ridesRVAdapter);
    }
    public void onItemClick(View view, int position) {
        String name = ridesRVAdapter.getItem(position);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor data = databaseAccess.getHistoryFromName(name);
        if(data.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"Record doesn't exist", Toast.LENGTH_SHORT).show();
        }
        else {
            while(data.moveToNext()) {
                Enter_values.ride = new Rides(data.getString(0), data.getString(1), Double.parseDouble(data.getString(2)), Double.parseDouble(data.getString(3)), Double.parseDouble(data.getString(4)), Boolean.parseBoolean(data.getString(5)), Boolean.getBoolean(data.getString(6)));
                goToHistoryResults();
            }
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToHistoryResults() {
        Intent intent = new Intent(this, HistoryResults.class);
        startActivity(intent);
    }
}
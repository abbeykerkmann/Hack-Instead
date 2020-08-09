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

public class History extends AppCompatActivity {

    Button back;

    private RecyclerView recyclerView;
    private RidesRVAdapter RidesRVAdapter;
    List<String> listOfRides = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToMainActivity();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        RidesRVAdapter = new RidesRVAdapter(listOfRides);
        RecyclerView.LayoutManager serviceLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(serviceLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(RidesRVAdapter);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor data = databaseAccess.getHistory();
        if (data.getCount() == 0){
            Toast.makeText(getApplicationContext(),"No records exist!", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()){
                listOfRides.add(data.getString(0));
                RidesRVAdapter.notifyDataSetChanged();
                Log.i("Ride", String.valueOf(listOfRides.size()));
            }
        }
        databaseAccess.close();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
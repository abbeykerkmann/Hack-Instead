package com.example.hackinstead;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button close_button,save,calculator;
    ImageButton question_mark;
    LinearLayout mycontent,overbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = (Button) findViewById(R.id.save_rides_button);
        calculator = (Button) findViewById(R.id.calculator_button);

        close_button = (Button) findViewById(R.id.close_button);
        question_mark = (ImageButton) findViewById(R.id.question_mark);

        mycontent = (LinearLayout) findViewById(R.id.mycontent);
        overbox = (LinearLayout) findViewById(R.id.overbox);

        mycontent.setAlpha(0);
        overbox.setAlpha(0);

        question_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycontent.setAlpha(1);
                overbox.setAlpha(1);
                save.setEnabled(false);
                calculator.setEnabled(false);
            }
        });
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycontent.setAlpha(0);
                overbox.setAlpha(0);
                save.setEnabled(true);
                calculator.setEnabled(true);
            }
        });

    }
}

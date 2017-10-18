package com.example.dell.tastifai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RateDish extends AppCompatActivity {
    DatabaseHelper myDB;
    RatingBar rating;
    TextView rateDish;
    Button btnRate;
    String dish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_dish);
        rating = (RatingBar)findViewById(R.id.ratingBar);
        rateDish = (TextView)findViewById(R.id.txtRateDish);
        btnRate= (Button) findViewById(R.id.btnRate);
        myDB = new DatabaseHelper(this);
        dish = getIntent().getStringExtra("dish");

        rateDish.setText("Please rate " + dish);
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float newEntry = rating.getRating();
                AddData(dish, newEntry);
                //Toast.makeText(RateDish.this, "Thanks for your time!, you rated " + newEntry + " stars", Toast.LENGTH_LONG).show();

            }
        });

    }
    public void AddData(String dish, float newEntry){
        //Toast.makeText(RateDish.this, "Thanks for your time!, you rated " + newEntry + " stars", Toast.LENGTH_LONG).show();
        boolean insertData = myDB.addData(dish, newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }
}

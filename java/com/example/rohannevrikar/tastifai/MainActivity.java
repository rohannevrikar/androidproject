package com.example.rohannevrikar.tastifai;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;

    Button btn;
    DatabaseHelper myDB;
    int num1 = 5;
    int num2 = 10;
    float avgRating;
    String dish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unlocked_cafe, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
        myDB = new DatabaseHelper(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextSize(20);
        dish = parent.getItemAtPosition(position).toString();
        TextView t = (TextView)findViewById(R.id.txtShowRating);
        Cursor cursor = myDB.getDishRating(dish);
        if(cursor.moveToFirst()){
            //Log.d("Debug", Arrays.toString(cursor.getColumnNames()));
            //Log.d("Rating : ",new DecimalFormat("##.##").format(cursor.getString(0)));
            if(cursor.getFloat(0)==0) //If the dish has no rating
                t.setText("No ratings yet");
            else
                t.setText(new DecimalFormat("#.##").format(cursor.getFloat(0)));
        }


        //Toast.makeText(parent.getContext(), "Selected: " + dish, Toast.LENGTH_LONG).show();
        btn = (Button)findViewById(R.id.btnNext);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,RateDish.class);
                intent.putExtra("dish",dish);
                startActivity(intent);
            }
        });
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}

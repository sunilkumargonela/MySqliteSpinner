package com.example.mysqlitespinner;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    MydbHandler dbhandler;
    Spinner spinner;
    EditText editText;
    ArrayList<String> fine;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhandler = new MydbHandler(this);
        editText = (EditText) findViewById(R.id.editText);

    }

    public void buttonClicked(View view) {

        String enteredText = editText.getText().toString();

        if (enteredText.isEmpty()){

            Toast.makeText(this, "enter something", Toast.LENGTH_LONG).show();
        }else {

            adding(enteredText);

        }
            settinSpinner();
    }


    public void adding(String foodName){
        boolean insert =  dbhandler.addData(foodName);

        if (insert){

            Toast.makeText(this, "data inserted successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "something went wrong ", Toast.LENGTH_LONG).show();
        }
    }

    public void settinSpinner(){

            fine = new ArrayList<>();
            Cursor cursor = dbhandler.getData();
            while (cursor.moveToNext()) {

                fine.add(cursor.getString(1));
            }

            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fine);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinner.setAdapter(adapter);

            editText.setText("");


    }

//   you cac delete records with this
    public void deleteButtonClicked(View view) {

        dbhandler.deleteDB();
    }
}

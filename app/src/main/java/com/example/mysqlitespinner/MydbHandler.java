package com.example.mysqlitespinner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MydbHandler extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "SPINNER_TABLE";
    private static final String DATABASE_NAME = "SPINNER_DATABASE.db";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ONE = "FOOD_NAME";

    public MydbHandler(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, FOOD_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String  name){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ONE, name);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1){

            return false;
        }else{

            return true;
        }
    }


    public Cursor getData(){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    //if you want to retrieve muliple values

    public ArrayList<String> getValues(){

        ArrayList<String> value = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        while (cursor.moveToNext()){

           value.add(cursor.getString(cursor.getColumnIndex(COLUMN_ONE)));
            db.close();
            Log.d("one", String.valueOf(value));
        }

        return value;
    }

    public void deleteDB() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);
    }

}

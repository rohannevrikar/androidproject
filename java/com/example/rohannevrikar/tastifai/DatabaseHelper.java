package com.example.rohannevrikar.tastifai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Rohan Nevrikar on 23-10-2017.
 */

class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "my_rating.db";
    public static final String TABLE_NAME = "rating_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "DISH";
    public static final String COL3 = "RATING";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " DISH TEXT, "+
                " RATING FLOAT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String dish, float newEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, dish);
        contentValues.put(COL3, newEntry);
        Log.d("Values", contentValues.toString());
        long result = db.insert(TABLE_NAME, null, contentValues);

        Log.d("Result", result + "");

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getDishRating(String dish){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT AVG(RATING) FROM " + TABLE_NAME + " WHERE DISH = '" + dish + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}

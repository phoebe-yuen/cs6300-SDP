package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

// The "WeightDataBaseHelper" Java class is used to create and maintain the weight database
public class WeightDataBaseHelper extends SQLiteOpenHelper {

    // Initialize string variables for table and column names
    public static final String WEIGHT_TABLE = "WEIGHT_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_AYSWEIGHT = "AYSWeight";
    public static final String COLUMN_AYBWEIGHT = "AYBWeight";
    public static final String COLUMN_RBPWEIGHT = "RBPWeight";
    public static final String COLUMN_LTWEIGHT = "LTWeight";
    public static final String COLUMN_RWTWEIGHT = "RWTWeight";

    public WeightDataBaseHelper(@Nullable Context context) {
        super(context, "weight.db", null, 1);
    }

    // Called the first time a database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + WEIGHT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_AYSWEIGHT + " INT, " + COLUMN_AYBWEIGHT + " INT, " + COLUMN_RBPWEIGHT + " INT, " + COLUMN_LTWEIGHT + " INT, "  + COLUMN_RWTWEIGHT + " INT)";
        db.execSQL(createTableStatement);
    }

    // Called if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Check if the weight table is empty
    public boolean checkEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + WEIGHT_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return false;
        }
        else {
            cursor.close();
            return true;
        }
    }

    // Add weights to the weight table
    public void addWeight(Weight weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AYSWEIGHT, weight.getAYSWeight());
        cv.put(COLUMN_AYBWEIGHT, weight.getAYBWeight());
        cv.put(COLUMN_RBPWEIGHT, weight.getRBPWeight());
        cv.put(COLUMN_LTWEIGHT, weight.getLTWeight());
        cv.put(COLUMN_RWTWEIGHT, weight.getRWTWeight());
        db.insert(WEIGHT_TABLE, null, cv);
    }

    // Update weights in the weight table
    public void updateWeight(Weight weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + WEIGHT_TABLE + " SET " + COLUMN_AYSWEIGHT + " = " + weight.getAYSWeight() + ", "+ COLUMN_AYBWEIGHT + " = " + weight.getAYBWeight() + ", "+ COLUMN_RBPWEIGHT + " = " + weight.getRBPWeight() + ", "+ COLUMN_LTWEIGHT + " = " + weight.getLTWeight() + ", " + COLUMN_RWTWEIGHT + " = " + weight.getRWTWeight() + " WHERE " + COLUMN_ID + " = 1";
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        cursor.close();
    }

    // Get weights from the weight table
    public Weight getWeight() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + WEIGHT_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        int ID = cursor.getInt(0);
        int AYSWeight = cursor.getInt(1);
        int AYBWeight = cursor.getInt(2);
        int RBPWeight = cursor.getInt(3);
        int LTWeight = cursor.getInt(4);
        int RWTWeight = cursor.getInt(5);
        cursor.close();
        return new Weight(ID, AYSWeight, AYBWeight, RBPWeight, LTWeight, RWTWeight);
    }
}

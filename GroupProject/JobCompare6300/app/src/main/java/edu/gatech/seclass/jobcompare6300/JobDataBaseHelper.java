package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

// The "JobDataBaseHelper" Java class is used to create and maintain the job database
public class JobDataBaseHelper extends SQLiteOpenHelper{

    // Initialize string variables for table and column names
    public static final String JOB_TABLE = "JOB_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_title = "title";
    public static final String COLUMN_company = "company";
    public static final String COLUMN_location = "location";
    public static final String COLUMN_costOfLivingIndex = "costOfLivingIndex";
    public static final String COLUMN_yearlySalary = "yearlySalary";
    public static final String COLUMN_yearlyBonus = "yearlyBonus";
    public static final String COLUMN_remoteWorkTime = "remoteWorkTime";
    public static final String COLUMN_retirementBenefitsPercentage = "retirementBenefitsPercentage";
    public static final String COLUMN_leaveTime = "leaveTime";
    public static final String COLUMN_adjustedYearlySalary = "adjustedYearlySalary";
    public static final String COLUMN_adjustedYearlyBonus = "adjustedYearlyBonus";
    public static final String COLUMN_isCurrentJob = "isCurrentJob";

    public JobDataBaseHelper(@Nullable Context context) {
        super(context, "job.db", null, 1);
    }

    // Called the first time a database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + JOB_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_title + " TEXT, " + COLUMN_company + " TEXT, " + COLUMN_location + " TEXT, " + COLUMN_costOfLivingIndex + " INT, " + COLUMN_yearlySalary + " REAL, " + COLUMN_yearlyBonus + " REAL, " + COLUMN_remoteWorkTime + " INT, " + COLUMN_retirementBenefitsPercentage + " REAL, " + COLUMN_leaveTime + " INT, " + COLUMN_adjustedYearlySalary + " REAL, " + COLUMN_adjustedYearlyBonus + " REAL, " + COLUMN_isCurrentJob + " INT)";
        db.execSQL(createTableStatement);
    }

    // Called if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Check if the job table has at least 2 jobs
    public boolean hasJobs() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + JOB_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.getCount() >= 2) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    // Check if a current job is stored in the job table
    public boolean hasCurrentJob() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + JOB_TABLE + " WHERE " + COLUMN_isCurrentJob + " = 1";
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.getCount() == 1) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    // Find the current job in the job table
    public Job findCurrentJob() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + JOB_TABLE + " WHERE " + COLUMN_isCurrentJob + " = 1";
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        int ID = cursor.getInt(0);
        String title = cursor.getString(1);
        String company = cursor.getString(2);
        String location = cursor.getString(3);
        int costOfLivingIndex = cursor.getInt(4);
        double yearlySalary = cursor.getDouble(5);
        double yearlyBonus = cursor.getDouble(6);
        int remoteWorkTime = cursor.getInt(7);
        double retirementBenefitsPercentage = cursor.getDouble(8);
        int leaveTime = cursor.getInt(9);
        int isCurrentJob = cursor.getInt(12);
        cursor.close();
        return new Job(ID, title, company, location, costOfLivingIndex, yearlySalary, yearlyBonus, remoteWorkTime, retirementBenefitsPercentage, leaveTime, isCurrentJob);
    }

    // Find the ID of the current job in the job table
    public int findCurrentJobID() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + JOB_TABLE + " WHERE " + COLUMN_isCurrentJob + " = 1";
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        int ID = cursor.getInt(0);
        cursor.close();
        return ID;
    }

    // Find a job using its ID in the job table
    public Job findJobByID(int key) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + JOB_TABLE + " WHERE " + COLUMN_ID + " = " + key;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        int ID = cursor.getInt(0);
        String title = cursor.getString(1);
        String company = cursor.getString(2);
        String location = cursor.getString(3);
        int costOfLivingIndex = cursor.getInt(4);
        double yearlySalary = cursor.getDouble(5);
        double yearlyBonus = cursor.getDouble(6);
        int remoteWorkTime = cursor.getInt(7);
        double retirementBenefitsPercentage = cursor.getDouble(8);
        int leaveTime = cursor.getInt(9);
        int isCurrentJob = cursor.getInt(12);
        cursor.close();
        if (isCurrentJob == 1) {
            title = "*" + title;
        }
        return new Job(ID, title, company, location, costOfLivingIndex, yearlySalary, yearlyBonus, remoteWorkTime, retirementBenefitsPercentage, leaveTime, isCurrentJob);
    }

    // Add a job to the job table, returns the ID of the job added
    public int addJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_title, job.getTitle());
        cv.put(COLUMN_company, job.getCompany());
        cv.put(COLUMN_location, job.getLocation());
        cv.put(COLUMN_costOfLivingIndex, job.getCostOfLivingIndex());
        cv.put(COLUMN_yearlySalary, job.getYearlySalary());
        cv.put(COLUMN_yearlyBonus, job.getYearlyBonus());
        cv.put(COLUMN_remoteWorkTime, job.getRemoteWorkTime());
        cv.put(COLUMN_retirementBenefitsPercentage, job.getRetirementBenefitsPercentage());
        cv.put(COLUMN_leaveTime, job.getLeaveTime());
        cv.put(COLUMN_adjustedYearlySalary, job.getAdjustedYearlySalary());
        cv.put(COLUMN_adjustedYearlyBonus, job.getAdjustedYearlyBonus());
        cv.put(COLUMN_isCurrentJob, job.getIsCurrentJob());
        db.insert(JOB_TABLE, null, cv);
        String queryString = "SELECT * FROM " + JOB_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToLast();
        int ID = cursor.getInt(0);
        cursor.close();
        return ID;
    }

    // Update the job details of the current job
    public void updateCurrentJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + JOB_TABLE + " SET " + COLUMN_title + " = '" + job.getTitle() + "', " + COLUMN_company + " = '" + job.getCompany() + "', " + COLUMN_location + " = '" + job.getLocation() + "', " + COLUMN_costOfLivingIndex + " = " + job.getCostOfLivingIndex() + ", " + COLUMN_yearlySalary + " = " + job.getYearlySalary() + ", " + COLUMN_yearlyBonus + " = " + job.getYearlyBonus() + ", " + COLUMN_remoteWorkTime + " = " + job.getRemoteWorkTime() + ", " + COLUMN_retirementBenefitsPercentage + " = " + job.getRetirementBenefitsPercentage() + ", " + COLUMN_leaveTime + " = " + job.getLeaveTime() + ", " + COLUMN_adjustedYearlySalary + " = " + job.getAdjustedYearlySalary() + ", " + COLUMN_adjustedYearlyBonus + " = " + job.getAdjustedYearlyBonus() + " WHERE " + COLUMN_isCurrentJob + " = 1";
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        cursor.close();
    }

    // Return all the jobs stored in the job table in a list
    public List<Job> getJobs() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + JOB_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        List<Job> returnList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int ID = cursor.getInt(0);
                Job job = findJobByID(ID);
                returnList.add(job);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnList;
    }
}
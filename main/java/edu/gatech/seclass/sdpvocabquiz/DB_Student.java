package edu.gatech.seclass.sdpvocabquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Student extends SQLiteOpenHelper {
    private static final String TABLE_NAME="Student";
    private static final String USERNAME="username";
    private static final String REALNAME="realName";
    private static final String MAJOR="major";
    private static final String SENIORITYLEVEL="seniorityLevel";
    private static final String EMAIL="email";

    public DB_Student(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE " + TABLE_NAME + "("+USERNAME+" TEXT PRIMARY KEY, "+REALNAME+" TEXT, "+MAJOR+" TEXT, "+SENIORITYLEVEL+" TEXT, "+EMAIL+" TEXT); ";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String username, String realName, String major, String seniorityLevel, String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(USERNAME, username);
        cv.put(REALNAME, realName);
        cv.put(MAJOR, major);
        cv.put(SENIORITYLEVEL,seniorityLevel);
        cv.put(EMAIL, email);
        long result=db.insert(TABLE_NAME, null, cv);
        if (result==-1){
            return false;
        }
        return true;

    }
    public Cursor getData(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " WHERE "+ USERNAME + " = '" +username+"'";
        Cursor data=db.rawQuery(query,null);
        return data;
    }
}

package com.example.patientlogin.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static final String DB_NAME = "userInfo.db";
    public static final String TABLE_NAME = "userInfo";


    public database(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE userInfo (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "username TEXT, " +
                "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String name, String pw) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password", pw);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result;
    }

    public boolean checkPair (String name, String pw) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "username=? and password=?";
        String[] selectionArgs = {name, pw};
        Cursor result = db.query(TABLE_NAME, new String[]{"ID"}, selection, selectionArgs, null, null, null);
        int count = result.getCount();
        db.close();
        if (count > 0) {
            return true;
        }
        return false;
    }
}

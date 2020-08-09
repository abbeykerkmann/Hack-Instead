package com.example.hackinstead;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);


    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public Cursor getRideNames() {
        open();
        Cursor data = db.rawQuery("SELECT RIDE_NAME FROM RIDES ORDER BY RIDE_NAME", new String[]{});
        return data;
    }

    public Cursor getRideValuesFromType(String type) {
        open();
        Cursor data = db.rawQuery("SELECT EXCITEMENT, INTENSITY, NAUSEA FROM RIDES WHERE RIDE_NAME = ?", new String[]{type});
        return data;
    }

    public Cursor findExistingName(String name) {
        open();
        Cursor data = db.rawQuery("SELECT NAME FROM SAVES WHERE NAME = ?", new String[]{name});
        return data;
    }

    public boolean saveRideData(String name, String type, double excitement, double intensity, double nausea, boolean sameRideType, boolean entryFee) {
        open();
        ContentValues content = new ContentValues();
        content.put("NAME", name);
        content.put("TYPE", type);
        content.put("EXCITEMENT", excitement);
        content.put("INTENSITY", intensity);
        content.put("NAUSEA", nausea);
        content.put("FIRST_RIDE_TYPE", sameRideType);
        content.put("ENTRY_FEE", entryFee);
        long result = db.insert("SAVES", null, content);
        close();
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getHistory(){
        open();
        Cursor data = db.rawQuery("SELECT * FROM SAVES", new String[]{});
        return data;
    }

    public Cursor getHistoryFromName(String name){
        open();
        Cursor data = db.rawQuery("SELECT * FROM SAVES WHERE NAME = ?", new String[]{name});
        return data;
    }

    public boolean deleteRideData(String name) {
        open();
        long result = db.delete("SAVES", "NAME = ?", new String[]{name});
        close();
        if(result == -1)
            return false;
        else
            return true;
    }
}
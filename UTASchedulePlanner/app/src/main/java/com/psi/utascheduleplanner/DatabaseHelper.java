package com.psi.utascheduleplanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name,CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //This will be called when no database exists
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDataBaseAdapter.DATABASE_CREATE);
    }

    //This is called when version types of the databases do not match and need to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Create a log about the version upgrade
        //Will destroy old data
        Log.w("DBAdapter", " upgrading from " + oldVersion + " to " + newVersion + ".");
        db.execSQL("Drop table if it exists");
        onCreate(db);
    }
}
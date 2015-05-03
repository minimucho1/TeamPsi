package com.psi.utascheduleplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by laosd_000 on 5/1/2015.
 */

public class UserDataBaseAdapter {
    static final String DATABASE_NAME = "users.db";

    static final int DATABASE_VERSION = 1;

    public static final int USER_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text); ";

    public SQLiteDatabase sqDB;
    private final Context context;
    private DatabaseHelper dbHelper;

    public UserDataBaseAdapter(Context thatContext) {
        context = thatContext;
        dbHelper = new DatabaseHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Method opens database
    public UserDataBaseAdapter open() throws SQLException {
        sqDB = dbHelper.getWritableDatabase();
        return this;
    }

    //Method will close database
    public void close() {
        sqDB.close();
    }

    //Method will return instance of the database
    public SQLiteDatabase getDatabaseInstance() {
        return sqDB;
    }

    //Method will insert the new user info into the database for creation
    public void insertEntry(String username, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", username);
        newValues.put("PASSWORD", password);

        //Inserting the row into table here
        sqDB.insert("LOGIN",null,newValues);
    }

    //Method to delete a user
    public boolean delUser(String username) {
        String where = "USERNAME=?";
        //int entryNum= sqDB.delete("LOGIN", where, new String[]{username}) ;
        return sqDB.delete("LOGIN", where, new String[]{username}) > 0;
    }

    //Method to return a password of a user
    public String getPassword(String username) {
        Cursor cursor = sqDB.query("LOGIN", null, "USERNAME=?", new String[]{username}, null, null, null);
        //If username does not exist
        if(cursor.getCount()<1)
            return "NOT EXIST";
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        return password;
    }

    //Method to update user info, only change password for now
    public void changePassword(String username, String password) {
        ContentValues updateValues = new ContentValues();

        updateValues.put("PASSWORD", password);

        String where="USERNAME = ?";
        sqDB.update("LOGIN", updateValues, where, new String[]{username});
    }
}

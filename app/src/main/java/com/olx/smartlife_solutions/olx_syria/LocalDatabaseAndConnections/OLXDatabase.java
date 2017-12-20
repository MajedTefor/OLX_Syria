package com.olx.smartlife_solutions.olx_syria.LocalDatabaseAndConnections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Crazy ITer on 12/16/2017.
 */

public class OLXDatabase extends SQLiteOpenHelper {
    Context context;
    private static final String dbName = "DatabaseLocalOLX";
    public OLXDatabase(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name VARCHAR," +
                " email VARCHAR, phone VARCHAR, password VARCHAR, country VARCHAR, token VARCHAR)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS users";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertUser(String name, String email, String phone, String password, String country, String token) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password",password);
        contentValues.put("country", country);
        contentValues.put("token", token);
        db.insert("users", null, contentValues);
    }

    public boolean loginSQL(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM users WHERE email = '"+email+"' and password = '"+password+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return !cursor.isAfterLast();
    }
}

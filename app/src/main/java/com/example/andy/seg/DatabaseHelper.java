package com.example.andy.seg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by clover on 2016/2/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String DATABSE_NAME = "notepad";
    private final static int DATABASE_VERSION = 1;
    public final static String DATABSE_TABLE = "diary";

    private final static String DATABASE_CREATE = "create table " + DATABSE_TABLE + " (_id integer primary key autoincrement,"
            + "title text not null, body text not null, created text not null);";
    public DatabaseHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABSE_TABLE);
        onCreate(db);
    }
}
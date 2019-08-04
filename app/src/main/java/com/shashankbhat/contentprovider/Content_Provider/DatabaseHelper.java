package com.shashankbhat.contentprovider.Content_Provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.shashankbhat.contentprovider.Content_Provider.DatabaseContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "myContacts";
    static final int DATABASE_VERSION = 1;

    DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_DB_TABLE = " CREATE TABLE " + TABLE_NAME +
                " ( "+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + " name TEXT NOT NULL);";

        db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
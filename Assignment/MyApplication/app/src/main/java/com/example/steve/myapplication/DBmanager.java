package com.example.steve.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;


public class DBmanager
{
    //name database and table
    public static final String DATABASE_NAME = "Christmas";
    public static final String DATABASE_TABLE = "Christmas_List";
    public static final int DATABASE_VERSION = 1;
    //Names columns in table
    public static final String LIST_ID = "_id";
    public static final String LIST_NAME = "NAME";
    public static final String LIST_GIFT = "GIFT";
    public static final String LIST_PRICE = "PRICE";
    public static final String LIST_STORE = "STORE";
    public static final String LIST_BOUGHT = "BOUGHT";
    //creates table
    public static final String DATABASE_CREATE =
            "CREATE TABLE " + DATABASE_TABLE + " (" + LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LIST_NAME + " TEXT NOT NULL, " + LIST_GIFT + " TEXT NOT NULL, "
                    + LIST_PRICE + " TEXT NOT NULL, " + LIST_STORE + " TEXT NOT NULL, "
                    + LIST_BOUGHT + " TEXT NOT NULL);";


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    //constructor
    public DBmanager(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    //create helper class
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    //opens database
    public DBmanager open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    //used long because boolean wouldnt work
    public long insertGift(String NAME, String GIFT, String PRICE, String STORE, String BOUGHT)
    {
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, NAME);
        values.put(LIST_GIFT, GIFT);
        values.put(LIST_PRICE, PRICE);
        values.put(LIST_STORE, STORE);
        values.put(LIST_BOUGHT, BOUGHT);

        return db.insert(DATABASE_TABLE, null, values);
    }

    public Cursor displayList()
    {
        Cursor mcursor = db.rawQuery("Select * from " + DATABASE_TABLE, null);

        if (mcursor != null)
        {
            mcursor.moveToFirst();
        }

        return mcursor;
    }

    public Cursor GetInfo(String row_id)
    {
      String query1 = ("Select * from " + DATABASE_TABLE + " where " + LIST_ID + " = " + row_id);
        Cursor lcursor = db.rawQuery(query1,null);

        if (lcursor != null)
        {
            lcursor.moveToFirst();
        }
        return lcursor;
    }

    //delete function
    public boolean deleteRow(String rowId)
    {
        return db.delete(DATABASE_TABLE, LIST_ID + "=" + rowId, null) > 0;
    }

    //update function
    public long updateGift(String ID, String NAME, String GIFT, String PRICE, String STORE, String BOUGHT)

    {
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, NAME);
        values.put(LIST_GIFT, GIFT);
        values.put(LIST_PRICE, PRICE);
        values.put(LIST_STORE, STORE);
        values.put(LIST_BOUGHT, BOUGHT);

        return db.update(DATABASE_TABLE, values, "_id = ? ",new String[]{ID});


    }



}




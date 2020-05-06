package com.hitesh.todopod;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Users.db";
    public static final String DB_TABLE = "Users_Table";
    public static final String ID = "ID";
    public static final String NAME = "NAME";


    public DatabaseHelper(Context context){
        super(context, DB_NAME, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                + DB_TABLE + " ("
                + "_id"
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME
                + " TEXT " + "); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }
    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);

        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }

    public int delete(String itemID){

        SQLiteDatabase db = this.getWritableDatabase();
         return db.delete(DB_TABLE, "NAME=?",  new String[]{String.valueOf(itemID)});
    }

    public Cursor loadDataa(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}

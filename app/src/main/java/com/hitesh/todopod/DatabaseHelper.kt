package com.hitesh.todopod

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE "
                + DB_TABLE + " ("
                + "_id"
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME
                + " TEXT " + "); ")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $DB_TABLE")
        onCreate(db)
    }

    fun insertData(name: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, name)
        val result = db.insert(DB_TABLE, null, contentValues)
        return result != -1L
    }

    fun delete(itemID: String): Int {
        val db = this.writableDatabase
        return db.delete(DB_TABLE, "NAME=?", arrayOf(itemID))
    }

    fun loadDataa(): Cursor {
        val db = this.readableDatabase
        val query = "Select * from $DB_TABLE"
        return db.rawQuery(query, null)
    }

    companion object {
        const val DB_NAME = "Users.db"
        const val DB_TABLE = "Users_Table"
        const val ID = "ID"
        const val NAME = "NAME"
    }
}
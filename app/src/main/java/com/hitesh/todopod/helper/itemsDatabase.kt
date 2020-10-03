package com.hitesh.todopod.helper

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hitesh.todopod.daos.ItemsDAO
import com.hitesh.todopod.items

@Database(entities = [items::class], version = 1)
abstract class itemsDatabase : RoomDatabase() {
    abstract fun itemsDAO(): ItemsDAO
    private class PopulateDbAsyncTask(db: itemsDatabase?) : AsyncTask<Void?, Void?, Void?>() {

        init {
            val itemsDao: ItemsDAO = db!!.itemsDAO()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            return null
        }
    }

    companion object {
        private var instance: itemsDatabase? = null
        @Synchronized
        fun getInstance(context: Context): itemsDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        itemsDatabase::class.java, "note_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
            }
            return instance
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }
    }
}
package com.hitesh.todopod

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class itemsRepository {
    var itemsDao: itemsDAO
    var allitemss: LiveData<List<items>>

    constructor(application: Application?) {
        var database = itemsDatabase.getInstance(application)
        itemsDao = database.itemsDAO()
        allitemss = itemsDao.allNotes
    }

    fun insert(items: items?) {
        val insertTask = InsertitemsAsyncTask(itemsDao)
        insertTask?.execute(items)
    }

    fun update(items: items?) {
        val update = itemsDao?.let { UpdateitemsAsyncTask(it) }
                update?.execute(items)
    }

    fun delete(items: items?) {
        val delete = itemsDao?.let { DeleteitemsAsyncTask(it) }
                delete?.execute(items)
    }

    fun deleteAllitemss() {
       val deleteAll = itemsDao?.let { DeleteAllitemssAsyncTask(it) }
               deleteAll?.execute()
    }

    private class InsertitemsAsyncTask(itemsDao: itemsDAO) : AsyncTask<items?, Void?, Void?>() {
        private var itemsDao: itemsDAO? = null
        init {
            this.itemsDao = itemsDao
        }

        override fun doInBackground(vararg itemss: items?): Void? {
            itemsDao?.insert(itemss[0])
            return null
        }

    }

    private class UpdateitemsAsyncTask(itemsDao: itemsDAO) : AsyncTask<items?, Void?, Void?>() {
        private var itemsDao: itemsDAO? = null

        init {
            this.itemsDao = itemsDao
        }

        override fun doInBackground(vararg itemss: items?): Void? {
            itemsDao?.update(itemss[0])
            return null
        }
    }

    private class DeleteitemsAsyncTask(itemsDao: itemsDAO) : AsyncTask<items?, Void?, Void?>() {
        private var itemsDao: itemsDAO? = null

        init {
            this.itemsDao = itemsDao
        }

        override fun doInBackground(vararg itemss: items?): Void? {
            itemsDao?.delete(itemss[0])
            return null
        }
    }

    private class DeleteAllitemssAsyncTask(itemsDao: itemsDAO) : AsyncTask<items?, Void?, Void?>() {
        private var itemsDao: itemsDAO? = null

        init {
            this.itemsDao = itemsDao
        }

        override fun doInBackground(vararg itemss: items?): Void? {
            itemsDao?.deleteAllNotes()
            return null
        }
    }
}
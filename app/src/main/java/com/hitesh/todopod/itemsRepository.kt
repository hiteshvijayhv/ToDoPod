package com.hitesh.todopod

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class itemsRepository {
    var itemsDao: itemsDAO
    var allitemss: LiveData<List<items>>

    constructor(application: Application?) {
        var database = itemsDatabase.getInstance(application)
        itemsDao = database.itemsDAO()
        allitemss = itemsDao.allNotes
    }

    fun insert(items: items?) {
        insertItemCoroutine(itemsDao, items) }

    fun update(items: items?) {
        updateItemCoroutine(itemsDao, items)
    }

    fun delete(items: items?) {
        DeleteItemsCoroutine(itemsDao, items)
    }

    fun deleteAllitemss() {
       DeleteAllItemsCoroutine(itemsDao)
    }

    fun insertItemCoroutine(itemsDao: itemsDAO?, itemss: items?){
      val itemsDao: itemsDAO? = null
        if (itemsDao != null) this.itemsDao = itemsDao
        
        CoroutineScope(IO).launch { insertItem(itemss) }
    }
    suspend fun insertItem(vararg itemss: items?): Void?{
        withContext(IO){
            itemsDao?.insert(itemss[0])
        }
        return null
    }

    fun updateItemCoroutine(itemsDao: itemsDAO?, itemss: items?){
        val itemsDao: itemsDAO? = null
        if (itemsDao != null) this.itemsDao = itemsDao

        CoroutineScope(IO).launch { updateItem(itemss) }
    }
    suspend fun updateItem(vararg itemss: items?): Void?{
        withContext(IO){
            itemsDao?.update(itemss[0])
        }
        return null
    }

    fun DeleteItemsCoroutine(itemsDao: itemsDAO?, itemss: items?){
        val itemsDao: itemsDAO? = null
        if (itemsDao != null) this.itemsDao = itemsDao

        CoroutineScope(IO).launch { deleteItem(itemss) }
    }
    suspend fun deleteItem(vararg itemss: items?): Void?{
        withContext(IO){
            itemsDao?.delete(itemss[0])
        }
        return null
    }

    fun DeleteAllItemsCoroutine(itemsDao: itemsDAO?){
        val itemsDao: itemsDAO? = null
        if (itemsDao != null) this.itemsDao = itemsDao

        CoroutineScope(IO).launch { deleteAllItem() }
    }
    suspend fun deleteAllItem(vararg itemss: items?): Void?{
        withContext(IO){
            itemsDao?.deleteAllNotes()
        }
        return null
    }
}
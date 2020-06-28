package com.hitesh.todopod.helper

import android.app.Application
import androidx.lifecycle.LiveData
import com.hitesh.todopod.items
import com.hitesh.todopod.itemsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class itemsRepository {
    var itemsDao: itemsDAO
    var allitemss: LiveData<List<items>>
    var callMethod: Int? = null

    constructor(application: Application?) {
        var database = itemsDatabase.getInstance(application)
        itemsDao = database.itemsDAO()
        allitemss = itemsDao.allNotes
    }

    fun insert(items: items?) {
        performOperation(itemsDao, items, 0)
    }

    fun update(items: items?) {
        performOperation(itemsDao, items, 1)
    }

    fun delete(items: items?) {
        performOperation(itemsDao, items, 2)
    }

    fun deleteAllitemss() {
       performOperation(itemsDao, null, 3)
    }

    fun performOperation(itemsDao: itemsDAO?, items: items?, callMethod: Int?){
        var itemsDao: itemsDAO? = null
        if (itemsDao != null) this.itemsDao = itemsDao
        CoroutineScope(IO).launch {
            when(callMethod){
                0 -> insertItem(items)
                1 -> updateItem(items)
                2 -> deleteItem(items)
                3 -> deleteAllitemss()
            }
        }
    }

    suspend fun insertItem(vararg itemss: items?){
        withContext(IO){
            itemsDao?.insert(itemss[0])
        }
    }

    suspend fun updateItem(vararg itemss: items?){
        withContext(IO){
            itemsDao?.update(itemss[0])
        }
    }

    suspend fun deleteItem(vararg itemss: items?){
        withContext(IO){
            itemsDao?.delete(itemss[0])
        }
    }

    suspend fun deleteAllItem(vararg itemss: items?){
        withContext(IO){
            itemsDao?.deleteAllNotes()
        }
    }
}
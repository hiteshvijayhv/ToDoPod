package com.hitesh.todopod.helper

import android.app.Application
import androidx.lifecycle.LiveData
import com.hitesh.todopod.items
import com.hitesh.todopod.daos.ItemsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsRepository(application: Application?) {
    private lateinit var itemsDao: ItemsDAO
    private lateinit var allitems: LiveData<List<items>>
    var callMethod: Int? = null

    init {
        val database = application?.let { itemsDatabase.getInstance(it) }
        if (database != null) {
            itemsDao = database.itemsDAO()
        }
        allitems = itemsDao.allNotes
    }

    fun getAllitems(): LiveData<List<items>> {
        return allitems
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

    fun deleteAllitems() {
       performOperation(itemsDao, null, 3)
    }

    fun performOperation(itemsDao: ItemsDAO?, items: items?, callMethod: Int?){
        var itemsDao: ItemsDAO? = null
        if (itemsDao != null) this.itemsDao = itemsDao
        CoroutineScope(IO).launch {
            when(callMethod){
                0 -> insertItem(items)
                1 -> updateItem(items)
                2 -> deleteItem(items)
                3 -> deleteAllitems()
            }
        }
    }

    suspend fun insertItem(vararg itemss: items?){
        withContext(IO){
            itemsDao.insert(itemss[0])
        }
    }

    suspend fun updateItem(vararg itemss: items?){
        withContext(IO){
            itemsDao.update(itemss[0])
        }
    }

    suspend fun deleteItem(vararg itemss: items?){
        withContext(IO){
            itemsDao.delete(itemss[0])
        }
    }

    suspend fun deleteAllItem(vararg itemss: items?){
        withContext(IO){
            itemsDao.deleteAllNotes()
        }
    }
}
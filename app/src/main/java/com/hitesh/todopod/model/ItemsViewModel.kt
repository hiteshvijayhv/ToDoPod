package com.hitesh.todopod.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hitesh.todopod.helper.ItemsRepository
import com.hitesh.todopod.items

class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ItemsRepository = ItemsRepository(application)
    val allitems: LiveData<List<items>>

    fun insert(items: items?) {
        repository.insert(items)
    }

    fun update(items: items?) {
        repository.update(items)
    }

    fun delete(items: items?) {
        repository.delete(items)
    }

    fun deleteAllitems() {
        repository.deleteAllitems()
    }

    init {
        allitems = repository.getAllitems()
    }
}
package com.hitesh.todopod.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hitesh.todopod.items
import com.hitesh.todopod.itemsRepository

class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: itemsRepository
    val allitemss: LiveData<List<items>>
    fun insert(items: items?) {
        repository.insert(items)
    }

    fun update(items: items?) {
        repository.update(items)
    }

    fun delete(items: items?) {
        repository.delete(items)
    }

    fun deleteAllitemss() {
        repository.deleteAllitemss()
    }

    init {
        repository = itemsRepository(application)
        allitemss = repository.allitemss
    }
}
package com.hitesh.todopod.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hitesh.todopod.items

@Dao
interface ItemsDAO {
    @Insert
    fun insert(items: items?)

    @Update
    fun update(items: items?)

    @Delete
    fun delete(items: items?)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @get:Query("SELECT * FROM note_table")
    val allNotes: LiveData<List<items>>
}
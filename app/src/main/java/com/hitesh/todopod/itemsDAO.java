package com.hitesh.todopod;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface itemsDAO {

    @Insert
    void insert(items note);

    @Update
    void update(items note);

    @Delete
    void delete(items note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table")
    LiveData<List<items>> getAllNotes();
}

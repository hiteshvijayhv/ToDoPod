package com.hitesh.todopod;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class items {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private int priority;
    public items(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public int getPriority() {
        return priority;
    }
}
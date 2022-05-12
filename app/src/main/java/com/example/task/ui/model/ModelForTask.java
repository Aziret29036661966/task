package com.example.task.ui.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class ModelForTask {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String created;

    public ModelForTask(String title, String created) {
        this.title = title;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
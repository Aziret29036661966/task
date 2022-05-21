package com.example.task.ui.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class ModelForTask {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String created;


    @Ignore
    public ModelForTask(String title, String created) {
        this.title = title;
        this.created = created;
    }
    public ModelForTask(int id, String title, String created) {
        this.id = id;
        this.title = title;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
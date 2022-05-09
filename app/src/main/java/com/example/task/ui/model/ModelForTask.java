package com.example.task.ui.model;

import java.util.Date;

public class ModelForTask {
    private String title;
    private Date created;

    public ModelForTask(String title, Date created) {
        this.title = title;
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

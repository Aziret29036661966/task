package com.example.task.data.common.appDatabase;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.task.ui.model.ModelForTask;

@Database(entities = {ModelForTask.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO dao();
}

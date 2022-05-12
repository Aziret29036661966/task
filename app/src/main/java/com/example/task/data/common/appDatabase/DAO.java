package com.example.task.data.common.appDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.task.ui.model.ModelForTask;

import java.util.List;

@Dao
public interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createInsert(ModelForTask insert);

    @Query("SELECT * FROM modelfortask")
    List<ModelForTask> getAllList();
}

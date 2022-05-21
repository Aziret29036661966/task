package com.example.task.data.common.appDatabase;

import android.graphics.ColorSpace;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.task.ui.model.ModelForTask;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM modelfortask ORDER BY title ASC")
    List<ModelForTask> getAllTasksByAlphabet();

    @Query("SELECT * FROM modelfortask ORDER BY created ASC")
    List<ModelForTask> getAllTasksByDate();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createInsert(ModelForTask insert);

    @Delete
    void delete(ModelForTask delete);

    @Update
    void update(ModelForTask update);

    @Query("SELECT * FROM modelfortask")
    List<ModelForTask> getAllList();
}

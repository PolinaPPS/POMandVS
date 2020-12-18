package ru.startandroid.myapplication.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.startandroid.myapplication.model.Photo;
@Dao
public interface PhotosDao {
    @Query("SELECT * FROM Photo")
    public List<Photo> LoadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPhoto(Photo photo);

    @Delete
    public void deletePhoto(Photo photo);

}

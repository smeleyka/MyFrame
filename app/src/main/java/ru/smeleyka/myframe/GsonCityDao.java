package ru.smeleyka.myframe;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by smeleyka on 03.10.17.
 */

@Dao
public interface GsonCityDao {
    @Query("SELECT * FROM gsoncity")
    List<GsonCity> getAll();


    @Query("SELECT * FROM gsoncity WHERE name LIKE :cityName LIMIT 1")
    GsonCity findByName(String cityName);

    @Query("SELECT * FROM gsoncity WHERE id LIKE :id")
    GsonCity findById(int id);

    @Insert
    void insertAll(GsonCity... city);

    @Delete
    void delete(GsonCity city);

}

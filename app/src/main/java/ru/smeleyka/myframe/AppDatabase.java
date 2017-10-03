package ru.smeleyka.myframe;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by smeleyka on 03.10.17.
 */



@Database(entities = {GsonCity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract GsonCityDao getCityDao();
}


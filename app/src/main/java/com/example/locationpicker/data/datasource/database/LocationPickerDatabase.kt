package com.example.locationpicker.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.locationpicker.data.datasource.database.dao.LocationDao
import com.example.locationpicker.data.datasource.database.entity.LocationEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [LocationEntity::class],
    autoMigrations = [],
)
abstract class LocationPickerDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}
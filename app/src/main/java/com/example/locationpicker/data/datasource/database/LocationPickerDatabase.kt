package com.example.locationpicker.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Perform necessary schema changes here
        database.execSQL("ALTER TABLE items ADD COLUMN new_column INTEGER NOT NULL DEFAULT 0")
    }
}

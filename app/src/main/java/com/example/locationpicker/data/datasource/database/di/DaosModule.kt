package com.example.locationpicker.data.datasource.database.di

import com.example.locationpicker.data.datasource.database.LocationPickerDatabase
import com.example.locationpicker.data.datasource.database.dao.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun provideLocationDao(db: LocationPickerDatabase) : LocationDao {
        return db.locationDao()
    }
}
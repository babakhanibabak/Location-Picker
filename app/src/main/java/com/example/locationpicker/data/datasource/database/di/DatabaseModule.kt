package com.example.locationpicker.data.datasource.database.di

import android.content.Context
import androidx.room.Room
import com.example.locationpicker.data.datasource.database.LocationPickerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideLocationPickerDatabase(
        @ApplicationContext context: Context,
    ): LocationPickerDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = LocationPickerDatabase::class.java,
            name = "location_picker",
        ).build()
    }
}
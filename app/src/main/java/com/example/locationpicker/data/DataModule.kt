package com.example.locationpicker.data

import com.example.locationpicker.data.reposirory.LocationRepositoryImpl
import com.example.locationpicker.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository
}
package com.example.locationpicker.data.reposirory

import com.example.locationpicker.data.datasource.database.dao.LocationDao
import com.example.locationpicker.data.datasource.database.entity.LocationEntity
import com.example.locationpicker.data.datasource.database.entity.toDomainModel
import com.example.locationpicker.domain.model.LocationListItemModel
import com.example.locationpicker.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao,
) : LocationRepository {

    override suspend fun getLocationList(): List<LocationListItemModel> {
        return locationDao.getAllLocations().map { it.toDomainModel() }
    }

    override suspend fun insertLocation(location: LocationListItemModel) {
        val entity = LocationEntity(
            lat = location.lat,
            lng = location.lng,
            comment = location.comment,
            isFavorite = location.isFavorite
        )
        locationDao.insertLocations(listOf(entity))
    }
}
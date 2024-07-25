package com.example.locationpicker.data.reposirory

import com.example.locationpicker.data.datasource.local.LocationDataProvider
import com.example.locationpicker.domain.model.LocationListItemModel
import com.example.locationpicker.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDataProvider: LocationDataProvider
) : LocationRepository {
    override suspend fun getLocationList(): List<LocationListItemModel> {
        return locationDataProvider.getLocationList()
    }
}
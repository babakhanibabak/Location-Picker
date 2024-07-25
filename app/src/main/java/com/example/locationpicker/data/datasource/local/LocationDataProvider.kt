package com.example.locationpicker.data.datasource.local

import com.example.locationpicker.domain.model.LocationListItemModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class LocationDataProvider @Inject constructor() {

    suspend fun getLocationList(): List<LocationListItemModel>{
        delay(1000)
        return locationList
    }

    private val locationList = listOf(
        LocationListItemModel(id = 1, lat = 3456.584848, lng = 3456.584848, comment = "My first location"),
    )
}
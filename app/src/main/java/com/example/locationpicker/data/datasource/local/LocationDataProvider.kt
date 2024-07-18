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
        LocationListItemModel(1.0, 1.0),
        LocationListItemModel(2.0, 2.0),
        LocationListItemModel(3.0, 3.0),
        LocationListItemModel(4.0, 4.0),
        LocationListItemModel(5.0, 5.0),
        LocationListItemModel(6.0, 6.0),
    )
}
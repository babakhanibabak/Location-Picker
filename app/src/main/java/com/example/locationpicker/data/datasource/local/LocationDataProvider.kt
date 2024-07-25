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
        LocationListItemModel("tehran",1.0, 1.0),
        LocationListItemModel("shiraz",2.0, 2.0),
        LocationListItemModel("tabriz",3.0, 3.0),
        LocationListItemModel("karaj",4.0, 4.0),
        LocationListItemModel("esfahan",5.0, 5.0),
        LocationListItemModel("kurdestan",6.0, 6.0),
    )
}
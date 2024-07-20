package com.example.locationpicker.ui.locationList.mapper

import com.example.locationpicker.domain.model.LocationListItemModel
import com.example.locationpicker.ui.locationList.model.LocationUiModel
import javax.inject.Inject

class LocationListUiMapper @Inject constructor() {
    fun mapToUiModel(locationList: List<LocationListItemModel>): List<LocationUiModel> {
        return locationList.map {
            LocationUiModel(
                name = it.name,
                lat = it.lat,
                lng = it.lng
            )
        }
    }
}
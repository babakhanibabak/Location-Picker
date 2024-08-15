package com.example.locationpicker.ui.locationlist.mapper

import com.example.locationpicker.domain.model.LocationListItemModel
import com.example.locationpicker.ui.locationlist.model.LocationUiModel
import javax.inject.Inject

class LocationListUiMapper @Inject constructor() {
    fun mapToUiModel(locationList: List<LocationListItemModel>): List<LocationUiModel> {
        return locationList.map {
            LocationUiModel(
                id = it.id,
                lat = it.lat,
                lng = it.lng,
                comment = it.comment,
                isFavorite = it.isFavorite
            )
        }
    }
}
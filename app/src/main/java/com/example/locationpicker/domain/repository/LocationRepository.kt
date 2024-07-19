package com.example.locationpicker.domain.repository

import com.example.locationpicker.domain.model.LocationListItemModel

interface LocationRepository {
    suspend fun getLocationList(): List<LocationListItemModel>

}
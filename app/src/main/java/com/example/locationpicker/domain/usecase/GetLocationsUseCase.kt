package com.example.locationpicker.domain.usecase

import com.example.locationpicker.domain.model.LocationListItemModel
import com.example.locationpicker.domain.repository.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend fun execute(): List<LocationListItemModel> {
        return locationRepository.getLocationList()
    }
}
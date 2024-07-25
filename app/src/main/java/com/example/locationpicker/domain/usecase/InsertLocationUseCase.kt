package com.example.locationpicker.domain.usecase

import com.example.locationpicker.domain.model.LocationListItemModel
import com.example.locationpicker.domain.repository.LocationRepository
import javax.inject.Inject

class InsertLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
) {
    suspend fun execute(location: LocationListItemModel) {
        locationRepository.insertLocation(location)
    }
}
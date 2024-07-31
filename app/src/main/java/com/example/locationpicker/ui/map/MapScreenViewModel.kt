package com.example.locationpicker.ui.map

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor() :ViewModel(){

    private val _uiState = MutableStateFlow(
        MapScreenState(
            uiSettings = MapUiSettings(),
            mapProperties = MapProperties(),
            currentLocation = LatLng(0.0, 0.0)
        )
    )
    val uiState by lazy {
        updateCurrentLocation(LatLng(3456.584848, 3456.584848))
        _uiState.asStateFlow()
    }

    fun updateCurrentLocation(location: LatLng) {
        _uiState.update {
            it.copy(currentLocation = location)
        }

    }
}

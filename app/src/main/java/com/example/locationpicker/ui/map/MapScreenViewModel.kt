package com.example.locationpicker.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationpicker.domain.model.LocationListItemModel
import com.example.locationpicker.domain.usecase.InsertLocationUseCase
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val insertLocationUseCase: InsertLocationUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MapScreenState(
            uiSettings = MapUiSettings(),
            mapProperties = MapProperties(),
            currentLocation = LatLng(35.3095143, 47.0278781)
        )
    )

    val uiState by lazy {
        _uiState.asStateFlow()
    }

    fun setOnLocationLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLocationLoading = isLoading) }
    }

    fun onSaveCurrentLocation() {
        viewModelScope.launch {
            runCatching {
                insertLocationUseCase.execute(
                    location = LocationListItemModel(
                        id = 1,
                        lat = _uiState.value.currentLocation.latitude,
                        lng = _uiState.value.currentLocation.longitude,
                        comment = "My first location",
                        isFavorite = false
                    )
                )
                toggleShowDialog(show = false)
            }
        }
    }

    fun toggleShowDialog(show: Boolean) {
        _uiState.update { it.copy(showDialog = show) }
    }

    fun onCurrentLocationUpdate(latLng: LatLng) {
        _uiState.update { it.copy(currentLocation = latLng) }
    }
}

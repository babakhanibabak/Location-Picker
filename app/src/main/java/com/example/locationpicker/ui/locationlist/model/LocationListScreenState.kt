package com.example.locationpicker.ui.locationlist.model

sealed class LocationListScreenState {
    data object Loading : LocationListScreenState()
    data class Success(val locations: List<LocationUiModel>) : LocationListScreenState()
    data class Error(val message: String) : LocationListScreenState()
}
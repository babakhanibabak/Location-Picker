package com.example.locationpicker.ui.locationList.model

sealed class LocationListScreenState {
    data object loading : LocationListScreenState()
    data class success(val locations: List<LocationUiModel>) : LocationListScreenState()
    data class error(val message: String) : LocationListScreenState()

}
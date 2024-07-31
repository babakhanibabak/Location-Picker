package com.example.locationpicker.ui.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

data class MapScreenState (
    val uiSettings: MapUiSettings,
    val mapProperties: MapProperties,
    val currentLocation : LatLng
)
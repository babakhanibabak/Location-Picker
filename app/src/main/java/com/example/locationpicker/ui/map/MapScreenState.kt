package com.example.locationpicker.ui.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings

data class MapScreenState(
    val uiSettings: MapUiSettings = MapUiSettings(zoomControlsEnabled = true),
    val mapProperties: MapProperties = MapProperties(mapType = MapType.SATELLITE),
    val currentLocation: LandLong = LandLong()
)

data class LandLong (
        val lat: Double=0.0,
        val lon: Double=0.0,
        ) {
    fun latlong(): LatLng {
        return LatLng(lat, lon)

    }
}
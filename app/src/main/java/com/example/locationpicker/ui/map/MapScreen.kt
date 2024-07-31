package com.example.locationpicker.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.locationpicker.ui.theme.LocationPickerTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {
    // TODO: MapView here
    MapScreenContent()
}

@Composable
fun MapScreenContent(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(uiState.currentLocation, 10f)
    }
    Box {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = CameraPositionState(),
            onMapClick = {
                viewModel.updateCurrentLocation(it)
            },
            uiSettings = MapUiSettings(),
            properties = MapProperties(),
            onMapLoaded = {
                cameraPositionState.position =
                    CameraPosition.fromLatLngZoom(uiState.currentLocation, 10f)
            },

            ) {
            Marker(
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
                state = MarkerState(position = uiState.currentLocation)
            )
        }
    }
}


@Preview
@Composable
private fun MapScreenContentPreview() {
    LocationPickerTheme {
        MapScreenContent()
    }
}
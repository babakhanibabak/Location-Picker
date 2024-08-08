package com.example.locationpicker.ui.map

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.example.locationpicker.ui.theme.LocationPickerTheme
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.streetview.StreetView
import com.google.maps.android.ktx.MapsExperimentalFeature

@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MapScreenContent(uiState, viewModel::onSaveCurrentLocation)
}

@OptIn(MapsExperimentalFeature::class)
@Composable
fun MapScreenContent(
    uiState: MapScreenState,
    onSaveCurrentLocation: () -> Unit = {},
) {


    Box(modifier = Modifier.fillMaxSize()) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(uiState.currentLocation.latlong(), 15f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = true),
            properties = MapProperties(mapType = MapType.SATELLITE)
        ) {
            MarkerInfoWindow(
                state = rememberMarkerState(position = uiState.currentLocation.latlong()),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            ) {
                Column(
                    modifier = Modifier
                        .border(BorderStroke(1.dp, Color.Black))
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .background(Color.Blue)
                        .padding(20.dp)
                ) {
                    Text(text = "Title", fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "Description", fontWeight = FontWeight.Medium, color = Color.White)
                }
            }
            StreetView(
                streetViewPanoramaOptionsFactory = {
                    StreetViewPanoramaOptions().position(uiState.currentLocation. latlong())
                },
                isPanningGesturesEnabled = true,
                isZoomGesturesEnabled = true,
                isStreetNamesEnabled = true,
                isUserNavigationEnabled = true
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 32.dp)
                .align(Alignment.TopCenter),
            onClick = onSaveCurrentLocation,
        ) {
            Text(text = "Save Current Location")
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
        }
    }
}


@Preview
@Composable
private fun MapScreenContentPreview() {
    LocationPickerTheme {
        MapScreenContent(MapScreenState())
    }
}
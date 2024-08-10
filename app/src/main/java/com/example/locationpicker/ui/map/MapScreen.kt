package com.example.locationpicker.ui.map

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.example.locationpicker.ui.theme.LocationPickerTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MapScreenContent(uiState, viewModel::toggleShowDialog)
}

@Composable
fun MapScreenContent(
    uiState: MapScreenState,
    onToggleShowDialog: (Boolean) -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(uiState.currentLocation, 15f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = true),
            properties = MapProperties(mapType = MapType.SATELLITE)
        ) {
            MarkerInfoWindow(
                state = rememberMarkerState(position = uiState.currentLocation),
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
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 32.dp)
                .align(Alignment.TopCenter),
            onClick = { onToggleShowDialog(true) },
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

        if (uiState.showDialog) {
            Dialog(onDismissRequest = {  }) {
                Column(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Enter your comment:",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    TextField(value = "", onValueChange = {})
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {  }) {
                            Text(text = "Save")
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { onToggleShowDialog(false) }
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MapScreenContentPreview() {
    LocationPickerTheme {
        MapScreenContent(
            MapScreenState(
                currentLocation = LatLng(35.2996598, 46.984572),
            )
        )
    }
}
package com.example.locationpicker.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.example.locationpicker.ui.theme.LocationPickerTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
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

    MapScreenContent(
        uiState = uiState,
        onToggleShowDialog = viewModel::toggleShowDialog,
        onCurrentLocationLoading = viewModel::setOnLocationLoading,
        onCurrentLocationUpdate = viewModel::onCurrentLocationUpdate,
        onSaveLocation = viewModel::onSaveCurrentLocation,
    )
}

@Composable
fun MapScreenContent(
    uiState: MapScreenState,
    onToggleShowDialog: (Boolean) -> Unit = {},
    onCurrentLocationLoading: (Boolean) -> Unit = {},
    onCurrentLocationUpdate: (LatLng) -> Unit = {},
    onSaveLocation: () -> Unit = {},
) {
    val context = LocalContext.current

    RequestLocationPermission(
        onPermissionGranted = {
            onCurrentLocationLoading(true)
            getCurrentLocation(
                context = context,
                onGetCurrentLocationSuccess = { location ->
                    onCurrentLocationLoading(false)
                    onCurrentLocationUpdate(LatLng(location.first, location.second))
                },
                onGetCurrentLocationFailed = { exception ->
                    onCurrentLocationLoading(false)
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                },
            )
        },
        onPermissionDenied = { /*TODO*/ },
        onPermissionsRevoked = {}
    )

    Box(modifier = Modifier.fillMaxSize()) {
        val cameraPositionState = rememberCameraPositionState(
            key = uiState.currentLocation.toString()
        ) {
            position = CameraPosition.fromLatLngZoom(uiState.currentLocation, 15f)
        }
        val markerState = rememberMarkerState(position = uiState.currentLocation)

        LaunchedEffect(key1 = uiState.currentLocation) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(uiState.currentLocation, 17f, 0f, 0f)
                ),
                durationMs = 3000
            )
            markerState.position = uiState.currentLocation
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = true),
            properties = MapProperties(mapType = MapType.SATELLITE)
        ) {
            MarkerInfoWindow(
                state = markerState,
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
            onClick = {
                if (!uiState.isLocationLoading) {
                    onCurrentLocationLoading(true)
                    getCurrentLocation(context = context,
                        onGetCurrentLocationSuccess = { location ->
                            onCurrentLocationLoading(false)
                            onCurrentLocationUpdate(LatLng(location.first, location.second))
                        },
                        onGetCurrentLocationFailed = { exception ->
                            onCurrentLocationLoading(false)
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        ) {
            when {
                uiState.isLocationLoading -> {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
                else -> Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
            }
        }

        if (uiState.showDialog) {
            Dialog(onDismissRequest = { }) {
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
                            onClick = onSaveLocation,
                        ) {
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermission(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    onPermissionsRevoked: () -> Unit,
) {
    // Initialize the state for managing multiple location permissions.
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    // Use LaunchedEffect to handle permissions logic when the composition is launched.
    LaunchedEffect(key1 = permissionState) {
        // Check if all previously granted permissions are revoked.
        val allPermissionsRevoked =
            permissionState.permissions.size == permissionState.revokedPermissions.size

        // Filter permissions that need to be requested.
        val permissionsToRequest = permissionState.permissions.filter {
            !it.status.isGranted
        }

        // If there are permissions to request, launch the permission request.
        if (permissionsToRequest.isNotEmpty()) permissionState.launchMultiplePermissionRequest()

        // Execute callbacks based on permission status.
        if (allPermissionsRevoked) {
            onPermissionsRevoked()
        } else {
            if (permissionState.allPermissionsGranted) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
        }
    }
}

@SuppressLint("MissingPermission")
private fun getCurrentLocation(
    context: Context,
    onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
    onGetCurrentLocationFailed: (Exception) -> Unit,
    priority: Boolean = true,
) {
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    // Determine the accuracy priority based on the 'priority' parameter
    val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
    else Priority.PRIORITY_BALANCED_POWER_ACCURACY

    // Check if location permissions are granted
    if (areLocationPermissionsGranted(context)) {
        // Retrieve the current location asynchronously
        fusedLocationProviderClient.getCurrentLocation(
            accuracy, CancellationTokenSource().token,
        ).addOnSuccessListener { location ->
            location?.let {
                // If location is not null, invoke the success callback with latitude and longitude
                onGetCurrentLocationSuccess(Pair(it.latitude, it.longitude))
            }
        }.addOnFailureListener { exception ->
            // If an error occurs, invoke the failure callback with the exception
            onGetCurrentLocationFailed(exception)
        }
    }
}

private fun areLocationPermissionsGranted(context: Context): Boolean {
    return (ActivityCompat.checkSelfPermission(
        context, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
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
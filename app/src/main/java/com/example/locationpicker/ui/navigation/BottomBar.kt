package com.example.locationpicker.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Button

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onMapClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onPointsClick: () -> Unit = {}
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = Color.Blue.copy(0.5f),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onMapClick) { Text("Map") }
            Button(onClick = onPointsClick) { Text("Points") }
            Button(onClick = onFavoriteClick) { Text("Favorites") }
        }
    }
}
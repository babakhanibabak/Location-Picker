package com.example.locationpicker.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Text
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun FavoritesScreen() {
    FavoritesScreenContent()
}

@Composable
fun FavoritesScreenContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Favorites Screen", color = Color.Black)
    }
}


@Preview
@Composable
private fun FavoritesScreenContentPreview() {
    LocationPickerTheme {
        FavoritesScreenContent()
    }
}
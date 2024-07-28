package com.example.locationpicker.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import com.example.locationpicker.ui.theme.LocationPickerTheme

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
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonDefaults( onClick = onMapClick, text = "Map")
            DividerDefaults()
            ButtonDefaults( onClick = onPointsClick, text = "Points")
            DividerDefaults()
            ButtonDefaults( onClick = onFavoriteClick, text = "Favorites")
        }
    }
}

@Composable
fun ButtonDefaults(
    modifier: Modifier = Modifier,
    text: String,
    onClick: ()->Unit
) {
    Button(modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Composable
fun DividerDefaults(modifier: Modifier = Modifier) {
    VerticalDivider(
        modifier = modifier.fillMaxHeight(),
        thickness = 2.dp,
        color = Color.LightGray
    )
}


@Preview
@Composable
private fun BottomBarPreview() {
    LocationPickerTheme {
        BottomBar()

    }
}
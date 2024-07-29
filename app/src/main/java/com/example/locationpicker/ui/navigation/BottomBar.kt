package com.example.locationpicker.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onMapClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onLocationClick: () -> Unit = {}
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = Color.LightGray,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyButtons(modifier=Modifier.weight(0.33f), onClick = onMapClick, text = "Map")
            DividerDefaults()
            MyButtons( modifier=Modifier.weight(0.33f), onClick = onLocationClick, text = "Location")
            DividerDefaults()
            MyButtons( modifier=Modifier.weight(0.33f), onClick = onFavoriteClick, text = "Favorites")
        }
    }
}

@Composable
fun MyButtons(
    modifier: Modifier = Modifier,
    text: String,
    onClick: ()->Unit
) {
    Button(modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RectangleShape
    ) {
        Text(text = text)
    }
}

@Composable
fun DividerDefaults(modifier: Modifier = Modifier) {
    VerticalDivider(
        modifier = modifier.fillMaxHeight(),
        thickness = 2.dp,
        color = Color.Black.copy(0.2f)
    )
}


@Preview
@Composable
private fun BottomBarPreview() {
    LocationPickerTheme {
        BottomBar()

    }
}
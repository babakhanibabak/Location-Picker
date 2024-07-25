package com.example.locationpicker.ui.locationlist.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.locationpicker.ui.locationlist.model.LocationUiModel
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun LocationListItem(
    modifier: Modifier = Modifier,
    model: LocationUiModel,
    onClick: (LocationUiModel) -> Unit = {}
) {
    Card(
        modifier = modifier.padding(8.dp)
            .fillMaxWidth()
            .border(shape = RoundedCornerShape(4.dp), width = 2.dp, color = Color.Black)
            .clickable { onClick(model) },
        ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxSize()) {
                androidx.wear.compose.material.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = model.lat.toString(),
                    fontSize = 30.sp
                )
                androidx.wear.compose.material.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = model.lng.toString(),
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun LocationListItemPreview() {
    LocationPickerTheme {
        LocationListItem(
            model = LocationUiModel("tehran", 1.0, 1.0),
        )
    }
}
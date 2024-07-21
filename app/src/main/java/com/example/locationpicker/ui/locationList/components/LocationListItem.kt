package com.example.locationpicker.ui.locationList.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.locationpicker.ui.locationList.model.LocationUiModel
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun LocationListItem(
    modifier: Modifier = Modifier,
    model: LocationUiModel,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier.padding(8.dp)
            .fillMaxWidth()
            .border(shape = RoundedCornerShape(4.dp), width = 2.dp, color = Color.Black)
            .clickable { onClick(model.name) },
        ) {
        Text(modifier = Modifier.padding(8.dp),text = model.name)
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
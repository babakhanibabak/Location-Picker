package com.example.locationpicker.ui.locationlist.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    onClick: (LocationUiModel) -> Unit = {},
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                shape = RoundedCornerShape(topStart = 50.dp, bottomEnd = 50.dp),
                width = 2.dp, color = Color.Magenta
            )
            .clickable { onClick(model) },
        colors = CardColors(
            containerColor = Color.Cyan,
            contentColor = Color.Black,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Magenta
        )
    ) {
        var isFavorite by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(0.9f)
            ) {
                androidx.wear.compose.material.Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = model.lat.toString(),
                    fontSize = 30.sp
                )
                androidx.wear.compose.material.Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = model.lng.toString(),
                    fontSize = 30.sp
                )
            }
            IconButton(onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { onClick(model) }
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = if (isFavorite) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder,
                    tint = Color.Blue,
                    contentDescription = ""
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
            model = LocationUiModel(
                id = 1,
                lat = 3456.584848,
                lng = 3456.584848,
                comment = "My first location",
            ),
        )
    }
}
package com.example.locationpicker.ui.locationlist.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.locationPicker.R
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun CircleImage(
    modifier: Modifier = Modifier,
    imageId: Int,
    size: DpSize = DpSize(150.dp, 150.dp)
) {
    Image(
        modifier = modifier.size(size).
        clip(CircleShape).
        border(2.dp, Color.Black, CircleShape),
        painter = painterResource(id = imageId),
        contentDescription = ""
    )
}

@Preview
@Composable
private fun CircleImagePreview() {
    LocationPickerTheme {
        CircleImage(
            imageId = R.drawable.images,
            size =DpSize(100.dp, 100.dp)
        )
    }
}
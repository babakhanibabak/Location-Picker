package com.example.locationpicker.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.example.locationPicker.R
import com.example.locationpicker.ui.locationlist.components.CircleImage

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Profile Screen",
            color = Color.Black,
        )
        CircleImage(imageId = R.drawable.images, size = DpSize(100.dp, 100.dp))
        Text(text = "Place name")
    }
}
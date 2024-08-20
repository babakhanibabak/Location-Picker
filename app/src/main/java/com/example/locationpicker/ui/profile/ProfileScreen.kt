package com.example.locationpicker.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.example.locationPicker.R
import com.example.locationpicker.ui.locationlist.components.CircleImage
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun ProfileScreen() {
    NavigationRail {
        ExtendedFab()
    }
  Column(modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally) {


      Text(
            text = "Profile Screen",
            color = Color.Black,
        )
      Spacer(modifier = Modifier.size(10.dp))
        CircleImage(imageId = R.drawable.images, size = DpSize(100.dp, 100.dp))
      Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Place name" ,color = Color.Black)
    }
}

@Composable
fun ExtendedFab(modifier: Modifier=Modifier) {
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(8.dp),
        containerColor = Color.Blue,
        contentColor = Color.White,
        modifier = modifier,
        content = {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            Text(text = "Compose")
        }
    )
}


@Preview
@Composable
private fun ProfileScreenPreview() {
    LocationPickerTheme {
        ProfileScreen()
    }
}


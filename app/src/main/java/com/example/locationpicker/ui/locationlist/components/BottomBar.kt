package com.example.locationpicker.ui.locationlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Button

@Composable
fun BottomBar(modifier: Modifier = Modifier,
onMapClick: () -> Unit = {},
              onFavoriteClick: () -> Unit = {},
              onPointsClick: () -> Unit = {}
) {
    Scaffold (modifier=modifier.padding()){padding->
        BottomAppBar (
            modifier=Modifier.padding(padding),
            windowInsets = windowInsets,
            containerColor = Color.Blue.copy(0.5f),
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(onClick = onMapClick){Text("Map")}
                Button(onClick = onPointsClick){Text("Points")}
                Button(onClick = onFavoriteClick){Text("Favorites")}

            }
        }
    }

}
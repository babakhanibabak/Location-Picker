package com.example.locationpicker.ui.favorites

import android.location.Location
import android.media.RouteListingPreference.Item
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.data.EmptyGroup.location
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import androidx.wear.compose.material.Text
import com.example.locationpicker.data.datasource.database.LocationPickerDatabase
import com.example.locationpicker.data.datasource.database.entity.LocationEntity
import com.example.locationpicker.ui.locationlist.components.LocationListItem
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun FavoritesScreen() {
    FavoritesScreenContent()
}

@Composable
fun FavoritesScreenContent(
    modifier: Modifier = Modifier,
) {
    val context= LocalContext.current
    val db= Room.databaseBuilder(
        context = context,
        LocationPickerDatabase::class.java, "location"

    ).build()
    val favoriteItems= remember {
        mutableSetOf(listOf<LocationEntity>())
    }
    LaunchedEffect(key1 = Unit) {
        favoriteItems.value =db.locationDao().getFavoriteLocations()
    }

    Column {
        LazyColumn {
            items(favoriteItems.value){
LocationListItem(location ->

    )
            }
        }

    }
}


@Preview
@Composable
private fun FavoritesScreenContentPreview() {
    LocationPickerTheme {
        FavoritesScreenContent()
    }
}
package com.example.locationpicker.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarDestination(val route: String) {
    data object Map : BottomBarDestination("map_screen")
    data object Points : BottomBarDestination("points_screen")
    data object Favorites : BottomBarDestination("favorites_screen")
    data object Profile : BottomBarDestination("profile_screen")
}

data class BottomNavigationItem(
    val selectedIcon: ImageVector = Icons.Filled.LocationOn,
    val unSelectedIcon: ImageVector = Icons.Filled.LocationOn,
    val text: String = "",
    val route: String = "",
) {
    fun bottomNavigationItems() = listOf(
        BottomNavigationItem(
            selectedIcon = Icons.Filled.LocationOn,
            unSelectedIcon = Icons.Outlined.LocationOn,
            text = "Map",
            route = BottomBarDestination.Map.route
        ),
        BottomNavigationItem(
            selectedIcon = Icons.AutoMirrored.Filled.List,
            unSelectedIcon = Icons.AutoMirrored.Outlined.List,
            text = "Points",
            route = BottomBarDestination.Points.route
        ),
        BottomNavigationItem(
            selectedIcon = Icons.Filled.Favorite,
            unSelectedIcon = Icons.Outlined.FavoriteBorder,
            text = "Favorites",
            route = BottomBarDestination.Favorites.route
        ),
        BottomNavigationItem(
            selectedIcon = Icons.Filled.AccountCircle,
            unSelectedIcon = Icons.Outlined.AccountCircle,
            text = "Profile",
            route = BottomBarDestination.Profile.route
        ),
    )
}

package com.example.locationpicker.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Text
import com.example.locationpicker.ui.favorites.FavoritesScreen
import com.example.locationpicker.ui.locationlist.LocationListScreen
import com.example.locationpicker.ui.map.MapScreen
import com.example.locationpicker.ui.profile.ProfileScreen
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun NavigationScreen() {
    NavigationScreenContent()
}

@Composable
private fun NavigationScreenContent() {
    val navController = rememberNavController()
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems()
                    .forEachIndexed { index, item ->
                        val selected = index == selectedItemIndex
                        NavigationBarItem(
                            selected = selected,
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unSelectedIcon,
                                    contentDescription = null,
                                )
                            },
                            label = {
                                Text(text = item.text, color = Color.Black)
                            },
                            onClick = {
                                if (selectedItemIndex != index) {
                                    selectedItemIndex = index
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            inclusive = true
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                        )
                    }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = navController,
            startDestination = BottomBarDestination.Map.route,
        ) {
            composable(BottomBarDestination.Map.route) {
                MapScreen()
            }
            composable(BottomBarDestination.Points.route) {
                LocationListScreen()
            }
            composable(BottomBarDestination.Favorites.route) {
                FavoritesScreen()
            }
            composable(BottomBarDestination.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Preview
@Composable
private fun NavigationScreenPreview() {
    LocationPickerTheme {
        NavigationScreenContent()
    }
}
package com.example.locationpicker.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha
import com.example.locationpicker.ui.locationlist.LocationListScreen
import com.example.locationpicker.ui.theme.LocationPickerTheme

@Composable
fun NavigationScreen(
    onLocationClick: () -> Unit ={}
) {
    NavigationScreenContent(onLocationClick = onLocationClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavigationScreenContent(
    onSaveClick: () -> Unit = {},
    onLocationClick: () -> Unit = {}
) {
    val bottomScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                windowInsets = windowInsets,
                containerColor = Color.LightGray,
                scrollBehavior = bottomScrollBehavior
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    BottomBar(onLocationClick = onLocationClick)
                }

            }

        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Bottom
        ) {

            // TODO: NavHost here

            MyButtons(modifier = Modifier.padding(15.dp), text = "Save", onClick = onSaveClick)

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    NavigationScreen(
                        onLocationClick = { navController.navigate("LocationListScreen") }
                    )
                }
                composable("LocationListScreen"){
                    LocationListScreen()
                }
            }

            // TODO: BottomBar here

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
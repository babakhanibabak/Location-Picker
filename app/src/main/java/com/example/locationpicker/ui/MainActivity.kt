package com.example.locationpicker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.locationpicker.ui.locationlist.LocationListScreen
import com.example.locationpicker.ui.theme.LocationPickerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocationPickerTheme {

                LocationListScreen()
            }
        }
    }
}


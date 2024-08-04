package com.example.locationpicker.ui.locationlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import com.example.locationpicker.ui.locationlist.components.LocationListItem
import com.example.locationpicker.ui.locationlist.model.LocationListScreenState
import com.example.locationpicker.ui.locationlist.model.LocationUiModel

@Composable
fun LocationListScreen(
    viewModel: LocationListScreenViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LocationListScreenContent(
        uiState = uiState,
        onItemClick = onItemClick,
    )
}

@Composable
fun LocationListScreenContent(
    uiState: LocationListScreenState,
    onItemClick: (String) -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is LocationListScreenState.Loading -> {
                Loading()
            }

            is LocationListScreenState.Error -> {
                ShowError(uiState.message)
            }

            is LocationListScreenState.Success -> {
                ShowData(uiState.locations, onItemClick)

            }
        }

    }
}


@Composable
private fun Loading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier.size(32.dp))
}

@Composable
private fun ShowData(
    locationList: List<LocationUiModel>,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(0.dp),
    ) {
        items(locationList) {
            LocationListItem(model = it, onClick = { model -> onItemClick(model.comment) })
        }
    }
}

@Composable
private fun ShowError(message: String) {
    Text(
        text = message,
        color = Color.Red,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}
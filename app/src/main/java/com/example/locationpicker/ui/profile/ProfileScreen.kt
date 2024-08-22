package com.example.locationpicker.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.example.locationPicker.R
import com.example.locationpicker.ui.locationlist.components.CircleImage
import com.example.locationpicker.ui.theme.LocationPickerTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    androidx.compose.material3.Text(
                        text = "Drawer Title",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                HorizontalDivider()
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    ExtendedFab()
                }


            }
        },
        gesturesEnabled = true
    )
    {Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.LightGray),
                title = { Text(text = "Profile",
                textAlign = TextAlign.Center) },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu, contentDescription = ""
                        )
                    }
            })
        }
    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val gradientColors = listOf(Cyan, Blue.copy(alpha = 0.5f), Red.copy(alpha = 0.5f))
            val offset = Offset(5f, 5f)

            Text(
                text = buildAnnotatedString {
                    append("Profile")
                    withStyle(style = SpanStyle(brush = Brush.linearGradient(colors = gradientColors))) {}
                    append("Screen")
                },
                color = Color.LightGray,
                style = TextStyle(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shadow = Shadow(
                        offset = offset,
                        blurRadius = 3f
                    )
                ),
            )
            Spacer(modifier = Modifier.size(10.dp))
            CircleImage(imageId = R.drawable.images, size = DpSize(100.dp, 100.dp))
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Place name", color = Color.Black)
        }
    }
}
}

@Composable
fun ExtendedFab(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Search", "Settings")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)

    Column {
        NavigationRail(
            containerColor = Color.Gray,
            contentColor = Color.White,
            modifier = Modifier.weight(1f)
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = { Icon(icons[index], contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                    }
                )
            }
        }

        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            containerColor = Blue,
            contentColor = Color.White,
            modifier = modifier,
            content = {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "Compose")
            })
    }
}


@Preview
@Composable
private fun ProfileScreenPreview() {
    LocationPickerTheme {
        ProfileScreen()
    }
}


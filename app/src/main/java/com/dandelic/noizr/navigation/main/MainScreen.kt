package com.dandelic.noizr.navigation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.dandelic.noizr.core.Constants

sealed class MainScreen(val route: String, val icon: ImageVector) {
    object Home: MainScreen(Constants.HOME_SCREEN, Icons.Filled.Home)
    object History: MainScreen(Constants.HISTORY_SCREEN, Icons.Filled.History)
    object NoiseMap: MainScreen(Constants.NOISE_MAP_SCREEN, Icons.Filled.Map)
    object Tips: MainScreen(Constants.TIPS_SCREEN, Icons.Filled.Lightbulb)
    object Profile: MainScreen(Constants.PROFILE_SCREEN, Icons.Filled.Person)
}

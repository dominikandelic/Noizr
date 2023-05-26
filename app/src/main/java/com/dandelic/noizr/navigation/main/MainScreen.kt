package com.dandelic.noizr.navigation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.dandelic.noizr.core.Constants

sealed class MainScreen(val route: String, val icon: ImageVector) {
    object Home: MainScreen(Constants.HOME_SCREEN, Icons.Filled.Home)
    object Profile: MainScreen(Constants.PROFILE_SCREEN, Icons.Filled.Person)
}

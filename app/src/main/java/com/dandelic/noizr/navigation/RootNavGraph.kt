package com.dandelic.noizr.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dandelic.noizr.navigation.auth.authNavGraph
import com.dandelic.noizr.navigation.main.mainNavGraph

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "AUTH",
    ) {
        authNavGraph(navController)
        mainNavGraph(navController)
    }
}
package com.dandelic.noizr.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.dandelic.noizr.presentation.history.HistoryScreen
import com.dandelic.noizr.presentation.noise_meter.NoiseMeterScreen
import com.dandelic.noizr.presentation.map.NoiseMapScreen
import com.dandelic.noizr.presentation.profile.ProfileScreen
import com.dandelic.noizr.presentation.tips.TipsScreen

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
        navigation(startDestination = MainScreen.Home.route, route = "MAIN") {
            composable(
                route = MainScreen.Home.route
            ) {
                NoiseMeterScreen(navController = navController)
            }
            composable(
                route = MainScreen.History.route
            ) {
                HistoryScreen(navController = navController)
            }
            composable(
                route = MainScreen.NoiseMap.route
            ) {
                NoiseMapScreen(navController = navController)
            }
            composable(
                route = MainScreen.Tips.route
            ) {
                TipsScreen(navController = navController)
            }
            composable(
                route = MainScreen.Profile.route
            ) {
                ProfileScreen(
                    navigateToHome = {
                        navController.navigate(MainScreen.Home.route) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    },
                    navController = navController
                )
            }
        }
}
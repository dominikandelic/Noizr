package com.dandelic.noizr.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.dandelic.noizr.presentation.home.HomeScreen
import com.dandelic.noizr.presentation.profile.ProfileScreen

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
        navigation(startDestination = MainScreen.Home.route, route = "MAIN") {
            composable(
                route = MainScreen.Home.route
            ) {
                HomeScreen(navController = navController)
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
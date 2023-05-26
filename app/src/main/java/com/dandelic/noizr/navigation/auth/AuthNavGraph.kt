package com.dandelic.noizr.navigation.auth

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.dandelic.noizr.navigation.main.MainScreen
import com.dandelic.noizr.presentation.forgot_password.ForgotPasswordScreen
import com.dandelic.noizr.presentation.sign_in.SignInScreen
import com.dandelic.noizr.presentation.sign_up.SignUpScreen
import com.dandelic.noizr.presentation.verify_email.VerifyEmailScreen

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(startDestination = AuthScreen.SignIn.route, route = "AUTH") {
        composable(route = AuthScreen.SignIn.route) {
            SignInScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(AuthScreen.ForgotPassword.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(AuthScreen.SignUp.route)
                }
            )
        }
        composable(
            route = AuthScreen.ForgotPassword.route
        ) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AuthScreen.SignUp.route
        ) {
            SignUpScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AuthScreen.VerifyEmail.route
        ) {
            VerifyEmailScreen(
                navigateToProfileScreen = {
                    navController.navigate(MainScreen.Profile.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
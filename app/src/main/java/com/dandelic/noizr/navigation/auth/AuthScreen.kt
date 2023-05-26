package com.dandelic.noizr.navigation.auth

import com.dandelic.noizr.core.Constants

sealed class AuthScreen(val route: String) {
    object SignIn: AuthScreen(Constants.SIGN_IN_SCREEN)
    object ForgotPassword: AuthScreen(Constants.FORGOT_PASSWORD_SCREEN)
    object SignUp: AuthScreen(Constants.SIGN_UP_SCREEN)
    object VerifyEmail: AuthScreen(Constants.VERIFY_EMAIL_SCREEN)
}

package com.dandelic.noizr.core

import com.dandelic.noizr.navigation.main.MainScreen

object Constants {
    //App
    const val APP_NAME = "Noizr"
    const val TAG = "AppTag"

    //Buttons
    const val SIGN_IN = "Sign in"
    const val RESET_PASSWORD = "Reset"
    const val UPDATE_PASSWORD = "Update"
    const val SIGN_UP = "Sign up"
    const val SIGN_OUT = "Sign out"
    const val DELETE_PROFILE = "Delete profile"

    //Screens
    const val SIGN_IN_SCREEN = "Sign in"
    const val FORGOT_PASSWORD_SCREEN = "Forgot password"
    const val SIGN_UP_SCREEN = "Sign up"
    const val HISTORY_SCREEN = "History"
    const val VERIFY_EMAIL_SCREEN = "Verify email"
    const val PROFILE_SCREEN = "Profile"
    const val NOISE_MAP_SCREEN = "Noise map"
    const val TIPS_SCREEN = "Tips"
    const val HOME_SCREEN = "Home"

    //Labels
    const val EMAIL_LABEL = "Email"
    const val PASSWORD_LABEL = "Password"

    //Useful
    const val NO_VALUE = ""
    const val VERTICAL_DIVIDER = "|"

    //Texts
    const val FORGOT_PASSWORD = "Forgot password?"
    const val NO_ACCOUNT = "No account? Sign up."
    const val ALREADY_USER = "Already a user? Sign in."
    const val ALREADY_VERIFIED = "Already verified?"
    const val SPAM_EMAIL = "If not, please also check the spam folder."

    //Messages
    const val VERIFY_EMAIL_MESSAGE = "We've sent you an email with a link to verify the email."
    const val EMAIL_NOT_VERIFIED_MESSAGE = "Your email is not verified."
    const val RESET_PASSWORD_MESSAGE = "We've sent you an email with a link to reset the password."
    const val SAVE_MEASURING_MESSAGE = "Successfully saved this measuring"
    const val DELETE_MEASURING_MESSAGE = "Successfully deleted measuring"
    const val UPDATE_PASSWORD_MESSAGE = "You've successfully updated your password."
    const val DELETE_PROFILE_MESSAGE = "You need to re-authenticate before deleting your profile."
    const val PROFILE_DELETED_MESSAGE = "Your profile has been deleted."

    //Error Messages
    const val SENSITIVE_OPERATION_MESSAGE = "This operation is sensitive and requires recent authentication. Log in again before retrying this request."


    // Bottom Navigation Items
    val BOTTOM_NAV_ITEMS = listOf(
        MainScreen.Home,
        MainScreen.History,
        MainScreen.NoiseMap,
        MainScreen.Tips,
        MainScreen.Profile
    )
}
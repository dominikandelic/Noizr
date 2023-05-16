package com.dandelic.noizr.presentation.forgot_password.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dandelic.noizr.components.BackIcon
import com.dandelic.noizr.core.Constants.FORGOT_PASSWORD_SCREEN

@Composable
fun ForgotPasswordTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar (
        backgroundColor = Color.DarkGray,
                title = {
            Text(
                color = Color.White,
                text = FORGOT_PASSWORD_SCREEN
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        }
    )
}
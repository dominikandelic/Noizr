package com.dandelic.noizr.presentation.sign_up.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dandelic.noizr.components.BackIcon
import com.dandelic.noizr.core.Constants.SIGN_UP_SCREEN

@Composable
fun SignUpTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar (
        backgroundColor = Color.DarkGray,
        title = {
            Text(
                text = SIGN_UP_SCREEN,
                color = Color.White
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        }
    )
}
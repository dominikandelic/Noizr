package com.dandelic.noizr.presentation.forgot_password

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dandelic.noizr.core.Constants.RESET_PASSWORD_MESSAGE
import com.dandelic.noizr.core.Utils.Companion.showMessage
import com.dandelic.noizr.presentation.forgot_password.components.ForgotPassword
import com.dandelic.noizr.presentation.forgot_password.components.ForgotPasswordContent
import com.dandelic.noizr.presentation.forgot_password.components.ForgotPasswordTopBar

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        backgroundColor = Color.DarkGray,
        topBar = {
            ForgotPasswordTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            ForgotPasswordContent(
                padding = padding,
                sendPasswordResetEmail = { email ->
                    viewModel.sendPasswordResetEmail(email)
                }
            )
        }
    )

    ForgotPassword(
        navigateBack = navigateBack,
        showResetPasswordMessage = {
            showMessage(context, RESET_PASSWORD_MESSAGE)
        },
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}
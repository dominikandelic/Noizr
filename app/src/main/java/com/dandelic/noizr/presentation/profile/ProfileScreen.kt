package com.dandelic.noizr.presentation.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dandelic.noizr.components.TopBar
import com.dandelic.noizr.core.Constants
import com.dandelic.noizr.core.Constants.PROFILE_SCREEN
import com.dandelic.noizr.core.Utils
import com.dandelic.noizr.presentation.profile.components.ProfileContent
import com.dandelic.noizr.presentation.profile.components.RevokeAccess
import com.dandelic.noizr.presentation.profile.components.UpdatePassword

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        backgroundColor = Color.DarkGray,
        topBar = {
            TopBar(
                title = PROFILE_SCREEN,
                signOut = {
                    viewModel.signOut()
                },
                revokeAccess = {
                    viewModel.revokeAccess()
                }
            )
        },
        content = { padding ->
            ProfileContent(
                email = viewModel.getUserEmail(),
                updatePassword = { password -> viewModel.updateUserPassword(password) },
                padding = padding
            )
        },
        scaffoldState = scaffoldState
    )

    RevokeAccess(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        signOut = {
            viewModel.signOut()
        }
    )

    UpdatePassword(
        showUpdatePasswordMessage = {
            Utils.showMessage(context, Constants.UPDATE_PASSWORD_MESSAGE)
        },
        showErrorMessage = { errorMessage ->
            Utils.showMessage(context, errorMessage)
        }
    )
}
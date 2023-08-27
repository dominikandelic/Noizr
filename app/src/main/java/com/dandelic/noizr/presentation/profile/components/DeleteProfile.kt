package com.dandelic.noizr.presentation.profile.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dandelic.noizr.components.ProgressBar
import com.dandelic.noizr.core.Constants.DELETE_PROFILE_MESSAGE
import com.dandelic.noizr.core.Constants.PROFILE_DELETED_MESSAGE
import com.dandelic.noizr.core.Constants.SENSITIVE_OPERATION_MESSAGE
import com.dandelic.noizr.core.Constants.SIGN_OUT
import com.dandelic.noizr.core.Utils.Companion.print
import com.dandelic.noizr.core.Utils.Companion.showMessage
import com.dandelic.noizr.domain.model.Response.Failure
import com.dandelic.noizr.domain.model.Response.Loading
import com.dandelic.noizr.domain.model.Response.Success
import com.dandelic.noizr.presentation.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DeleteProfile(
    viewModel: ProfileViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showDeleteProfileMessage() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = DELETE_PROFILE_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when (val deleteProfileResponse = viewModel.deleteProfileResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isAccessRevoked = deleteProfileResponse.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked) {
                    showMessage(context, PROFILE_DELETED_MESSAGE)
                }
            }
        }

        is Failure -> deleteProfileResponse.apply {
            LaunchedEffect(e) {
                print(e)
                if (e.message == SENSITIVE_OPERATION_MESSAGE) {
                    showDeleteProfileMessage()
                }
            }
        }
    }
}
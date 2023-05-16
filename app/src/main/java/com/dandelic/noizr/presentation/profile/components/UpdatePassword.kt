package com.dandelic.noizr.presentation.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.dandelic.noizr.components.ProgressBar
import com.dandelic.noizr.core.Utils
import com.dandelic.noizr.domain.model.Response
import com.dandelic.noizr.presentation.profile.ProfileViewModel

@Composable
fun UpdatePassword(
    viewModel: ProfileViewModel = hiltViewModel(),
    showUpdatePasswordMessage: () -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val updatePasswordResponse = viewModel.updatePasswordResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isUpdatePasswordDataSent = updatePasswordResponse.data
            LaunchedEffect(isUpdatePasswordDataSent) {
                if (isUpdatePasswordDataSent) {
                    showUpdatePasswordMessage()
                }
            }
        }
        is Response.Failure -> updatePasswordResponse.apply {
            LaunchedEffect(e) {
                Utils.print(e)
                showErrorMessage(e.message)
            }
        }
    }
}
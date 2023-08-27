package com.dandelic.noizr.presentation.noise_meter.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.dandelic.noizr.components.ProgressBar
import com.dandelic.noizr.core.Utils
import com.dandelic.noizr.domain.model.Response
import com.dandelic.noizr.presentation.noise_meter.NoiseMeterViewModel

@Composable
fun SaveMeasuring(viewModel: NoiseMeterViewModel = hiltViewModel(),
                  showSaveMeasuringMessage: () -> Unit,
                  showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val createMeasuringResponse = viewModel.createMeasuringResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isMeasuringCreated = createMeasuringResponse.data
            LaunchedEffect(isMeasuringCreated) {
                if(isMeasuringCreated) {
                    showSaveMeasuringMessage()
                }
            }
        }
        is Response.Failure -> createMeasuringResponse.apply {
            LaunchedEffect(e) {
                Utils.print(e)
                showErrorMessage(e.message)
            }
        }
    }
}
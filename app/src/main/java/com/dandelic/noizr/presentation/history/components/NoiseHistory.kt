package com.dandelic.noizr.presentation.history.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.dandelic.noizr.components.ProgressBar
import com.dandelic.noizr.core.Utils
import com.dandelic.noizr.domain.model.Measuring
import com.dandelic.noizr.domain.model.Response
import com.dandelic.noizr.presentation.history.HistoryViewModel

@Composable
fun NoiseHistory(
    viewModel: HistoryViewModel = hiltViewModel(),
    setMeasurings: (measuringsResponse: List<Measuring>) -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit,
    showDeleteMeasuringMessage: () -> Unit,
) {
    when (val getMeasuringsResponse = viewModel.getMeasuringsResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val measuringsResponse = getMeasuringsResponse.data
            setMeasurings(measuringsResponse)
        }

        is Response.Failure -> getMeasuringsResponse.apply {
            LaunchedEffect(e) {
                Utils.print(e)
                showErrorMessage(e.message)
            }
        }
    }

    when (val deleteMeasuringResponse = viewModel.deleteMeasuringResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isDeleted = deleteMeasuringResponse.data
            if (isDeleted) {
                showDeleteMeasuringMessage()
            }
        }

        is Response.Failure -> deleteMeasuringResponse.apply {
            LaunchedEffect(e) {
                Utils.print(e)
                showErrorMessage(e.message)
            }
        }
    }
}
package com.dandelic.noizr.presentation.history

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dandelic.noizr.core.Constants
import com.dandelic.noizr.core.Utils
import com.dandelic.noizr.domain.model.Measuring
import com.dandelic.noizr.navigation.main.components.BottomTabNavigation
import com.dandelic.noizr.presentation.history.components.HistoryContent
import com.dandelic.noizr.presentation.history.components.NoiseHistory
import kotlinx.coroutines.launch

@Composable
@SuppressLint("MissingPermission")
fun HistoryScreen(navController: NavHostController, viewModel: HistoryViewModel = hiltViewModel()) {


    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var measurings by remember {
        mutableStateOf(listOf<Measuring>())
    }

    LaunchedEffect(viewModel.deleteMeasuringResponse) {
        coroutineScope.launch {
            viewModel.getMeasurings()
        }
    }

    Scaffold(
        backgroundColor = Color.DarkGray,
        content = { padding ->
            HistoryContent(padding = padding, measurings = measurings, deleteMeasuring = {
                coroutineScope.launch {
                    viewModel.deleteMeasuring(it)
                }
            })
        },
        scaffoldState = scaffoldState,
        bottomBar = { BottomTabNavigation(navController) }
    )

    NoiseHistory(setMeasurings = { measuringsResponse ->
        measurings = measuringsResponse
    }, showErrorMessage = { errorMessage ->
        Utils.showMessage(context, errorMessage)
    },
        showDeleteMeasuringMessage = {
            Utils.showMessage(
                context,
                Constants.DELETE_MEASURING_MESSAGE
            )
        })

}
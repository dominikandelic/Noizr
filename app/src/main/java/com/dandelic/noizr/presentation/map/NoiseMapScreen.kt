package com.dandelic.noizr.presentation.map

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
import com.dandelic.noizr.core.Utils
import com.dandelic.noizr.domain.model.Measuring
import com.dandelic.noizr.navigation.main.components.BottomTabNavigation
import com.dandelic.noizr.presentation.map.components.NoiseMap
import com.dandelic.noizr.presentation.map.components.NoiseMapContent
import kotlinx.coroutines.launch

@Composable
fun NoiseMapScreen(
    navController: NavHostController,
    viewModel: NoiseMapViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var measurings by remember {
        mutableStateOf(listOf<Measuring>())
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.getMeasurings()
        }
    }

    Scaffold(
        backgroundColor = Color.DarkGray,
        content = { padding ->
            NoiseMapContent(padding = padding, measurings = measurings)
        },
        scaffoldState = scaffoldState,
        bottomBar = { BottomTabNavigation(navController) }
    )

    NoiseMap(setMeasurings = { measuringsResponse ->
        measurings = measuringsResponse
    }, showErrorMessage = { errorMessage ->
        Utils.showMessage(context, errorMessage)
    })
}
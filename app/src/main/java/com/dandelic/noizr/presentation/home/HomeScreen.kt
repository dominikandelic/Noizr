package com.dandelic.noizr.presentation.home

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.dandelic.noizr.navigation.main.components.BottomTabNavigation
import com.dandelic.noizr.presentation.home.components.HomeContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController
    ) {

    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()


    val permission = android.Manifest.permission.RECORD_AUDIO
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Open camera
        } else {
            ContextCompat.checkSelfPermission(context, permission)
        }
    }

    Scaffold(
        backgroundColor = Color.DarkGray,
        content = { HomeContent() },
        bottomBar = { BottomTabNavigation(navController) },
        scaffoldState = scaffoldState
    )
}



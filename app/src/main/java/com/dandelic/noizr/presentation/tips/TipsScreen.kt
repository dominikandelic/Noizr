package com.dandelic.noizr.presentation.tips

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dandelic.noizr.navigation.main.components.BottomTabNavigation
import com.dandelic.noizr.presentation.tips.components.TipsContent

@Composable
fun TipsScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        backgroundColor = Color.DarkGray,
        content = { padding ->
            TipsContent(padding = padding)
        },
        scaffoldState = scaffoldState,
        bottomBar = { BottomTabNavigation(navController) }
    )

}

@Preview
@Composable
fun TipsScreenPreview() {
    TipsScreen(navController = rememberNavController())
}
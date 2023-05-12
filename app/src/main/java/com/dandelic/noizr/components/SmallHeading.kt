package com.dandelic.noizr.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun SmallHeading(text: String) {
    Text(
        color = Color.White,
        text = text,
        fontSize = 40.sp
    )
}
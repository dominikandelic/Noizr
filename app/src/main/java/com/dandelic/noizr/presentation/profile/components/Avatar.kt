package com.dandelic.noizr.presentation.profile.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Avatar() {
    Icon(
        Icons.Rounded.AccountCircle,
        contentDescription = "User avatar",
        tint = Color.White,
        modifier = Modifier.size(96.dp)
    )
}
package com.dandelic.noizr.presentation.verify_email.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelic.noizr.components.SmallSpacer
import com.dandelic.noizr.core.Constants.ALREADY_VERIFIED
import com.dandelic.noizr.core.Constants.SPAM_EMAIL

@Composable
fun VerifyEmailContent(
    padding: PaddingValues,
    reloadUser: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding).padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable {
                reloadUser()
            },
            color = Color.White,
            text = ALREADY_VERIFIED,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline
        )
        SmallSpacer()
        Text(
            color = Color.White,
            text = SPAM_EMAIL,
            fontSize = 15.sp
        )
    }
}
package com.dandelic.noizr.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.dandelic.noizr.core.Constants.EMAIL_LABEL
import kotlinx.coroutines.job

@Composable
fun EmailField(
    email: TextFieldValue,
    onEmailValueChange: (newValue: TextFieldValue) -> Unit,
    enabled: Boolean = true
) {
    val focusRequester = FocusRequester()

    OutlinedTextField(
        enabled = enabled,
        value = email,
        onValueChange = { newValue ->
            onEmailValueChange(newValue)
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            cursorColor = Color.White,
            placeholderColor = Color.White,
            backgroundColor = Color.Transparent
        ),
        label = {
            Text(
                text = EMAIL_LABEL
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        modifier = Modifier.focusRequester(focusRequester)
    )

    LaunchedEffect(Unit) {
        coroutineContext.job.invokeOnCompletion {
            focusRequester.requestFocus()
        }
    }
}
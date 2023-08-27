package com.dandelic.noizr.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelic.noizr.components.EmailField
import com.dandelic.noizr.components.PasswordField
import com.dandelic.noizr.components.SmallSpacer
import com.dandelic.noizr.core.Constants

@Composable
fun ProfileContent(
    email: String?,
    updatePassword: (password: String) -> Unit,
    padding: PaddingValues,
) {
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver
    ) { mutableStateOf(TextFieldValue(Constants.NO_VALUE)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(top = 48.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Avatar",
            color = Color.White,
            fontSize = 36.sp,
        )
        Avatar()
        SmallSpacer()
        EmailField(
            email = TextFieldValue(email.orEmpty(), TextRange.Zero),
            onEmailValueChange = {},
            enabled = false
        )
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue -> password = newValue })
        SmallSpacer()
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = {
                updatePassword(password.text)
            }
        ) {
            Text(
                text = Constants.UPDATE_PASSWORD,
                fontSize = 15.sp
            )
        }
    }
}
package com.dandelic.noizr.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeContent(

)  {

    var score by remember {
        mutableStateOf(0f)
    }

    var scoreInput by remember {
        mutableStateOf("0")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6b4cba)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier
                .padding(vertical = 16.dp),
            text = "Progress for every level up: $maxProgressPerLevel",
            color = Color.LightGray,
            fontSize = 16.sp
        )

        ArcProgressbar(
            score = score,
        )

        Button(onClick = {
            score += scoreInput.toFloat()
        }) {
            Text("Add Score")
        }

        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = scoreInput,
            onValueChange = {
                scoreInput = it
            }
        )
    }
}
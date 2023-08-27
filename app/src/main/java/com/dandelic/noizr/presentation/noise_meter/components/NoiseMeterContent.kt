package com.dandelic.noizr.presentation.noise_meter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelic.noizr.presentation.noise_meter.NoiseStatus
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeContent(
    padding: PaddingValues,
    noiseStatus: NoiseStatus, noiseLevel: Int, audioPermissionState: PermissionState,
    locationPermissionState: PermissionState, currentAddress: String,
    addMeasuring: () -> Unit
) {
    val noiseMessageColor = when (noiseStatus) {
        NoiseStatus.NONE -> Color.Yellow
        NoiseStatus.LOW -> Color.Green
        NoiseStatus.MEDIUM -> Color(255, 165, 0)
        NoiseStatus.HIGH -> Color(255, 0, 0)
        NoiseStatus.EXTREME -> Color(139, 0, 0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally, // Center the elements horizontally
        verticalArrangement = Arrangement.SpaceEvenly // Space the elements evenly vertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            NoiseMeter(noiseLevel)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                when (audioPermissionState.status) {
                    // If the camera permission is granted, then show screen with the feature enabled
                    PermissionStatus.Granted -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Current noise level",
                                color = Color.White,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                text = noiseLevel.toString() + "dB",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(8.dp) // Add some padding around the text element
                            )
                            Text(
                                text = noiseStatus.message,
                                color = noiseMessageColor,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(8.dp) // Add some padding around the text element
                            )
                        }
                    }

                    is PermissionStatus.Denied -> {
                        Surface(
                            shape = MaterialTheme.shapes.medium, // Use a medium shape for rounded corners
                            elevation = 4.dp, // Add some elevation for shadow effect
                            modifier = Modifier.padding(8.dp) // Add some padding around the surface element
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                val textToShow =
                                    if ((audioPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                                        // If the user has denied the permission but the rationale can be shown,
                                        // then gently explain why the app requires this permission
                                        "Microphone is a crucial feature of Noizr. Please grant the permission."
                                    } else {
                                        // If it's the first time the user lands on this feature, or the user
                                        // doesn't want to be asked again for this permission, explain that the
                                        // permission is required
                                        "Microphone permission is required for Noizr to work properly. " +
                                                "Please grant the permission"
                                    }
                                Text(textToShow)
                                Button(onClick = { audioPermissionState.launchPermissionRequest() }) {
                                    Text("Request permission")
                                }
                            }
                        }
                    }
                }
            }
        }

        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Measuring information",
                    fontSize = 20.sp,
                )


                when (locationPermissionState.status) {
                    // If the camera permission is granted, then show screen with the feature enabled
                    PermissionStatus.Granted -> {
                        Text(
                            text = "Current location: $currentAddress",
                        )
                    }

                    is PermissionStatus.Denied -> {
                        Column {
                            val textToShow =
                                if ((locationPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                                    // If the user has denied the permission but the rationale can be shown,
                                    // then gently explain why the app requires this permission
                                    "Location is a feature of Noizr that allows us to better locate noisy areas. Please grant the permission."
                                } else {
                                    // If it's the first time the user lands on this feature, or the user
                                    // doesn't want to be asked again for this permission, explain that the
                                    // permission is required
                                    "Location permission is required for Noizr to locate noisy areas properly. " +
                                            "Please grant the permission"
                                }
                            Text(textToShow)
                            Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                                Text("Request permission")
                            }
                        }
                    }
                }
            }
        }

        // Use a button element to display a save button with green color and white text
        Button(
            onClick = { addMeasuring() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier.padding(8.dp) // Add some padding around the button element

        ) {
            Text(text = "Save", color = Color.Black)
        }
    }
}
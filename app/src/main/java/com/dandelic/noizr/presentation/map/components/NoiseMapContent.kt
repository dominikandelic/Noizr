package com.dandelic.noizr.presentation.map.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dandelic.noizr.domain.model.Measuring
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun NoiseMapContent(padding: PaddingValues, measurings: List<Measuring>) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(padding)) {
        // Latitude and Longitude for Osijek region
        val osijek = LatLng(45.5550, 18.6955)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(osijek, 10f)
        }
        GoogleMap(cameraPositionState = cameraPositionState) {
            measurings.forEach { measuring ->
                Marker(
                    state = MarkerState(
                        position = LatLng(
                            measuring.geolocation!!.latitude,
                            measuring.geolocation!!.longitude
                        )
                    ),
                    title = "Noise marker",
                    snippet = "Noise level: " + measuring.noiseLevel + "dB"
                )
            }
        }
    }
}
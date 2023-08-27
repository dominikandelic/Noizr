package com.dandelic.noizr.presentation.noise_meter

import android.Manifest
import android.annotation.SuppressLint
import android.media.MediaRecorder
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dandelic.noizr.core.Constants
import com.dandelic.noizr.core.Utils
import com.dandelic.noizr.core.Utils.Companion.getReadableLocation
import com.dandelic.noizr.domain.model.Measuring
import com.dandelic.noizr.navigation.main.components.BottomTabNavigation
import com.dandelic.noizr.presentation.noise_meter.components.HomeContent
import com.dandelic.noizr.presentation.noise_meter.components.SaveMeasuring
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit
import kotlin.math.log10

enum class NoiseStatus(val message: String) {
    NONE("Is microphone active?"),
    LOW("Great, your ears are safe!"),
    MEDIUM("Moderate, be careful!"),
    HIGH("Loud, protect your ears!"),
    EXTREME("Very loud, danger!")
}

// Amplitude in Pa
// Solution as per this answer https://stackoverflow.com/a/14870458
fun calculateDecibel(amplitude: Int): Int {
    val pressure = amplitude/51805.5336
    val reference = 0.00002
    Log.d("MICROPHONE_AMPLITUDE", amplitude.toString())
    val db = 20 * log10(pressure/reference)
    return if (db > 0) {
        db.toInt()
    } else {
        0
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("MissingPermission")
@Composable
fun NoiseMeterScreen(navController: NavHostController, viewModel: NoiseMeterViewModel = hiltViewModel()) {

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    var noiseLevel by remember { mutableIntStateOf(0) }

    var currentAddress by remember {
        mutableStateOf("")
    }

    var isRecording by remember {
        mutableStateOf((false))
    }

    var isTrackingLocation by remember {
        mutableStateOf(false)
    }

    var currentLocation by remember {
        mutableStateOf(GeoPoint(0.0, 0.0))
    }

    val locationProvider = LocationServices.getFusedLocationProviderClient(context)
    lateinit var locationCallback: LocationCallback

    val audioPermissionState = rememberPermissionState(
        Manifest.permission.RECORD_AUDIO
    )

    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val timer = Timer()

    //Instantiate
    val mediaRecorder: MediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        MediaRecorder(context)
    } else {
        MediaRecorder()
    }

    fun stopLocationUpdate() {
        try {
            //Removes all location updates for the given callback.
            val removeTask = locationProvider.removeLocationUpdates(locationCallback)
            removeTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("LOCATION", "Location Callback removed.")
                } else {
                    Log.d("LOCATION", "Failed to remove Location Callback.")
                }
            }
        } catch (se: SecurityException) {
            Log.e("LOCATION", "Failed to remove Location Callback.. $se")
        }
    }

    @SuppressLint("MissingPermission")
    fun locationUpdate() {
        locationCallback.let {
            val locationRequest: LocationRequest =
                LocationRequest.Builder(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    TimeUnit.SECONDS.toMillis(60)
                )
                    .setWaitForAccurateLocation(false)
                    .setMinUpdateIntervalMillis(TimeUnit.SECONDS.toMillis(30))
                    .setMaxUpdateDelayMillis(TimeUnit.MINUTES.toMillis(30))
                    .build()
            //use FusedLocationProviderClient to request location update
            locationProvider.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }

    DisposableEffect(locationPermissionState.status) {
        if (locationPermissionState.status.isGranted) {
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    locationProvider.lastLocation
                        .addOnSuccessListener { location ->
                            location?.let {

                                val lat = location.latitude
                                val long = location.longitude

                                currentLocation = GeoPoint(lat, long)

                                currentAddress = getReadableLocation(
                                    latitude = lat,
                                    longitude = long,
                                    context = context
                                )
                            }
                        }
                        .addOnFailureListener {
                            Log.e("LOCATION", "${it.message}")
                        }

                }
            }
            isTrackingLocation = true
            locationUpdate()
        }
        onDispose {
            if (locationPermissionState.status.isGranted && isTrackingLocation) {
                stopLocationUpdate()
                isTrackingLocation = false
            }
        }
    }


    DisposableEffect(audioPermissionState.status) {
        if (audioPermissionState.status.isGranted) {
            Log.d("RECORD_AUDIO", "Recording started")
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mediaRecorder.setOutputFile(context.filesDir.path + "/audio.3gp")
            mediaRecorder.prepare()
            mediaRecorder.start()
            isRecording = true
        }

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (audioPermissionState.status.isGranted) {
                    val amplitude = mediaRecorder.maxAmplitude
                    noiseLevel = calculateDecibel(amplitude)
                }
            }
        }, 0, 100) // Call every 100 milliseconds

        onDispose {
            timer.cancel()
            if (isRecording) {
                mediaRecorder.stop();     // stop recording
                mediaRecorder.reset();    // set state to idle
                mediaRecorder.release();  // release resources back to the system
                isRecording = false
            }
        }

    }


    // Define the noise status based on the noise level
    val noiseStatus = when {
        noiseLevel < 1 -> NoiseStatus.NONE
        noiseLevel < 40 -> NoiseStatus.LOW
        noiseLevel < 60 -> NoiseStatus.MEDIUM
        noiseLevel < 80 -> NoiseStatus.HIGH
        else -> NoiseStatus.EXTREME
    }

    Scaffold(
        backgroundColor = Color.DarkGray,
        content = {padding ->
            HomeContent(
                padding = padding,
                noiseStatus = noiseStatus,
                noiseLevel = noiseLevel,
                audioPermissionState = audioPermissionState,
                locationPermissionState = locationPermissionState,
                currentAddress = currentAddress
            ) {
                viewModel.createMeasuring(
                    measuring = Measuring(
                        createdAt = Timestamp.now(),
                        userUid = viewModel.getUserUid()!!,
                        geolocation = currentLocation,
                        noiseLevel = noiseLevel
                    )
                )
            }
        },
        scaffoldState = scaffoldState,
        bottomBar = { BottomTabNavigation(navController) }
    )

    SaveMeasuring(showSaveMeasuringMessage = {
        Utils.showMessage(
            context,
            Constants.SAVE_MEASURING_MESSAGE
        )
    }, showErrorMessage = { errorMessage ->
        Utils.showMessage(context, errorMessage)
    })
}




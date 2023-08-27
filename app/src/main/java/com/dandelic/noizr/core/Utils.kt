package com.dandelic.noizr.core

import android.content.Context
import android.location.Geocoder
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.dandelic.noizr.core.Constants.TAG
import java.io.IOException
import java.util.Locale

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = makeText(context, message, LENGTH_LONG).show()

        fun getReadableLocation(latitude: Double, longitude: Double, context: Context): String {
            var addressText = ""
            val geocoder = Geocoder(context, Locale.getDefault())

            try {

                val addresses = geocoder.getFromLocation(latitude, longitude, 1)

                if (addresses?.isNotEmpty() == true) {
                    val address = addresses[0]
                    addressText = address.getAddressLine(0)
                    Log.d("geolocation", addressText)
                }

            } catch (e: IOException) {
                Log.d("geolocation", e.message.toString())

            }

            return addressText
        }
    }
}
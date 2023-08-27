package com.dandelic.noizr.presentation.history.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dandelic.noizr.core.Utils.Companion.getReadableLocation
import com.dandelic.noizr.domain.model.Measuring

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MeasuringListItem(measuring: Measuring, deleteMeasuring: (documentId: String) -> Unit) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = MaterialTheme.shapes.medium, // Use a medium shape for rounded corners
        elevation = 4.dp, // Add some elevation for shadow effect
        onClick = { expanded = true },
        modifier = Modifier.padding(8.dp) // Add some padding around the surface element
    ) {
        Box {
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(text = "Noise level: " + measuring.noiseLevel + "dB")
                }
                Row {
                    Text(text = "Location: " + measuring.geolocation?.let {
                        getReadableLocation(
                            it.latitude,
                            it.longitude,
                            context
                        )
                    })
                }
                Row {
                    Text(text = "Created at: " + measuring.createdAt?.toDate().toString())
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = {
                    expanded = false
                    deleteMeasuring(measuring.documentId)
                }) {
                    Text(text = "Delete")
                    Icon(Icons.Rounded.Delete, contentDescription = "Delete measuring")
                }
            }
        }
    }
}
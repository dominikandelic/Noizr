package com.dandelic.noizr.presentation.history.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelic.noizr.domain.model.Measuring

@Composable
fun HistoryContent(
    padding: PaddingValues,
    measurings: List<Measuring>,
    deleteMeasuring: (measuring: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(8.dp)
    ) {
        Text(text = "Noise history", fontSize = 32.sp, color = Color.White)
        if (measurings.isNotEmpty()) {
            LazyColumn {
                items(measurings) { measuring ->
                    MeasuringListItem(
                        measuring,
                        deleteMeasuring = {
                            deleteMeasuring(measuring.documentId)
                        })
                }
            }
        } else {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 4.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "No data, add measurings to show historical records",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
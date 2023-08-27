package com.dandelic.noizr.presentation.noise_meter.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun NoiseMeter(noiseLevel: Int) {
    // Define the radius of the semi-circle as 150 dp
    val radius = 150.dp

    // Define the start angle of the semi-circle as -180 degrees (left)
    val startAngle = -180f

    // Define the sweep angle of the semi-circle as 180 degrees (half circle)
    val sweepAngle = 180f

    // Define the arrow length as 160 dp (slightly longer than the radius)
    val arrowLength = 140.dp

    // Define the arrow angle as the start angle plus a fraction of the sweep angle based on the noise level (0-100)
    val arrowAngle = startAngle + sweepAngle * noiseLevel / 100f

    // Define the arrow head size as 15 dp
    val arrowHeadSize = 15.dp

    // Define the arrow head angles as the arrow angle plus or minus 5 degrees
    val arrowHeadAngle1 = arrowAngle - 7.5f
    val arrowHeadAngle2 = arrowAngle + 7.5f

    // Use a canvas element to draw custom shapes
    Canvas(modifier = Modifier.size(radius * 2)) {
        // Use a drawArc function to draw the semi-circle with white color and 10 dp stroke width
        drawArc(
            color = Color.White,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = 40.dp.toPx()),
            size = size
        )

        // Use a drawLine function to draw the arrow line with red color and 10 dp stroke width
        drawLine(
            color = Color.Red,
            start = center,
            end = center + Offset(
                x = arrowLength.toPx() * cos(Math.toRadians(arrowAngle.toDouble())).toFloat(),
                y = arrowLength.toPx() * sin(Math.toRadians(arrowAngle.toDouble())).toFloat()
            ),
            strokeWidth = 5.dp.toPx()
        )

        // Use a drawLine function to draw the first arrow head line with red color and 10 dp stroke width
        drawLine(
            color = Color.Red,
            start = center + Offset(
                x = arrowLength.toPx() * cos(Math.toRadians(arrowAngle.toDouble())).toFloat(),
                y = arrowLength.toPx() * sin(Math.toRadians(arrowAngle.toDouble())).toFloat()
            ),
            end = center + Offset(
                x = (arrowLength.toPx() - arrowHeadSize.toPx()) * cos(Math.toRadians(arrowHeadAngle1.toDouble())).toFloat(),
                y = (arrowLength.toPx() - arrowHeadSize.toPx() ) * sin(Math.toRadians(arrowHeadAngle1.toDouble())).toFloat()
            ),
            strokeWidth = 4.dp.toPx()
        )

        // Use a drawLine function to draw the second arrow head line with red color and 10 dp stroke width
        drawLine(
            color = Color.Red,
            start = center + Offset(
                x = arrowLength.toPx() * cos(Math.toRadians(arrowAngle.toDouble())).toFloat(),
                y = arrowLength.toPx() * sin(Math.toRadians(arrowAngle.toDouble())).toFloat()
            ),
            end = center + Offset(
                x = (arrowLength.toPx() - arrowHeadSize.toPx()) * cos(Math.toRadians(arrowHeadAngle2.toDouble())).toFloat(),
                y = (arrowLength.toPx() - arrowHeadSize.toPx()) * sin(Math.toRadians(arrowHeadAngle2.toDouble())).toFloat()
            ),
            strokeWidth = 4.dp.toPx()
        )

        // Use a drawCircle function to draw the red dot at the center of the view with 5 dp radius
        drawCircle(
            color = Color.Red,
            center = center,
            radius = 5.dp.toPx()
        )
    }
}
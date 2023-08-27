package com.dandelic.noizr.presentation.tips.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelic.noizr.R

@Composable
fun TipsContent(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(8.dp)
    ) {
        Row {
            Text(text = "Useful tips", fontSize = 32.sp, color = Color.White)
        }
        LazyColumn {
            item {
                Row {
                    Surface(
                        shape = MaterialTheme.shapes.medium, // Use a medium shape for rounded corners
                        elevation = 4.dp, // Add some elevation for shadow effect
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Avoid or limit exposure to loud noises. " +
                                    "Loud noises are those that are above 85 decibels (dB), such as a lawnmower, a concert, or a firework. " +
                                    "You can use your app to measure the noise levels around you and see if they are safe or harmful. " +
                                    "If you are exposed to loud noises, you should try to move away from them, lower the volume, or use hearing protection. " +
                                    "You should also give your ears a rest after being exposed to loud noises, as this can help prevent hearing loss.\n",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            item {
                Row {
                    Surface(
                        shape = MaterialTheme.shapes.medium, // Use a medium shape for rounded corners
                        elevation = 4.dp, // Add some elevation for shadow effect
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Make your home and workplace quieter. " +
                                    "You can reduce the noise in your home and workplace by using carpets, curtains, furniture, plants, and other sound-absorbing materials. " +
                                    "You can also close the windows and doors, turn off or lower the volume of appliances and devices, and ask others to be quiet or respectful. " +
                                    "You can also use white noise machines, fans, or humidifiers to mask unwanted noises.\n",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            item {
                Image(
                    painter = painterResource(id = R.drawable.quiet),
                    contentDescription = stringResource(R.string.quiet_image_description),
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }
}
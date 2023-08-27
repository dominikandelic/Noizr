package com.dandelic.noizr.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dandelic.noizr.core.Constants.DELETE_PROFILE
import com.dandelic.noizr.core.Constants.SIGN_OUT


@Composable
fun TopBar(
    title: String,
    signOut: () -> Unit,
    deleteProfile: () -> Unit
) {
    var openMenu by remember { mutableStateOf(false) }

    TopAppBar (
        backgroundColor = Color.DarkGray,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = Color.White,
                    text = title
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            openMenu = !openMenu
                        }
                    ) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = null,
                        )
                    }
                }
            }
        },
        actions = {
            DropdownMenu(
                expanded = openMenu,
                onDismissRequest = {
                    openMenu = !openMenu
                }
            ) {
                DropdownMenuItem(
                    onClick = {
                        signOut()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = SIGN_OUT
                    )
                }
                DropdownMenuItem(
                    onClick = {
                        deleteProfile()
                        openMenu = !openMenu
                    }
                ) {
                    Text(
                        text = DELETE_PROFILE
                    )
                }
            }
        }
    )
}
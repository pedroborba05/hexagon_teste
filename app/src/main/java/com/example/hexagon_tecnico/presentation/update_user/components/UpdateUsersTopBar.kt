package com.example.hexagon_tecnico.presentation.update_user.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.hexagon_tecnico.core.Constants.Companion.TOPBAR_UPDATE_USERS_SCREEN

@Composable
fun UpdateUsersTopBar(
) {
    TopAppBar (
        title = {
            Text(
                text = TOPBAR_UPDATE_USERS_SCREEN
            )
        },
    )
}
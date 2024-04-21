package com.example.hexagon_tecnico.presentation.inactiveusers.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.hexagon_tecnico.core.Constants.Companion.INACTIVE_USERS_SCREEN

@Composable
fun InactiveUsersTopBar() {
    TopAppBar (
        title = {
            Text(
                text = INACTIVE_USERS_SCREEN
            )
        }
    )
}
package com.example.hexagon_tecnico.presentation.users.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.hexagon_tecnico.core.Constants.Companion.USERS_SCREEN

@Composable
fun UsersTopBar() {
    TopAppBar (
        title = {
            Text(
                text = USERS_SCREEN
            )
        }
    )
}
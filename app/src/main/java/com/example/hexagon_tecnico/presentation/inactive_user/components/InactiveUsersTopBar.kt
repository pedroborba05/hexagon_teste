package com.example.hexagon_tecnico.presentation.inactive_user.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hexagon_tecnico.core.Constants.Companion.TOPBAR_INACTIVE_USERS_SCREEN

@Composable
fun InactiveUsersTopBar(
) {
    TopAppBar (
        title = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(TOPBAR_INACTIVE_USERS_SCREEN)
            }
        },
    )
}
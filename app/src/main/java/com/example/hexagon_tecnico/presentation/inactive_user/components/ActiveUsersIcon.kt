package com.example.hexagon_tecnico.presentation.inactive_user.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import com.example.hexagon_tecnico.core.Constants.Companion.DELETE_USER

@Composable
fun ActiveUsersIcon(
    activeUsers: () -> Unit
) {
    IconButton(
        onClick = activeUsers
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = DELETE_USER,
        )
    }
}
package com.example.hexagon_tecnico.presentation.users.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import com.example.hexagon_tecnico.core.Constants.Companion.INACTIVE_USER

@Composable
fun DeleteIcon(
    inactiveUser: () -> Unit
) {
    IconButton(
        onClick = inactiveUser
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = INACTIVE_USER,
        )
    }
}
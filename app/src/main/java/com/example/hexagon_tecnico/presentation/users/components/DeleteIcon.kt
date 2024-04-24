package com.example.hexagon_tecnico.presentation.users.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
            imageVector = Icons.Rounded.Delete,
            contentDescription = INACTIVE_USER,
        )
    }
}
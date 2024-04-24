package com.example.hexagon_tecnico.presentation.inactive_user.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.R
import com.example.hexagon_tecnico.core.Constants.Companion.ACTIVE_USER

@Composable
fun ActiveUsersIcon(
    activeUsers: () -> Unit
) {
    IconButton(
        onClick = {
            activeUsers()
        }
    ) {
        Icon(
            painter = painterResource(id = (R.drawable.image_active)),
            contentDescription = ACTIVE_USER,
            tint = Color.Green,
            modifier = Modifier
                .size(25.dp)
        )
    }
}
package com.example.hexagon_tecnico.presentation.users.components


import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.R
import com.example.hexagon_tecnico.core.Constants.Companion.INACTIVE_USER

@Composable
fun DeleteIcon(
    inactiveUser: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    IconButton(
        onClick = { showDialog = true }
    ) {
        Icon(
            painter = painterResource(id = (R.drawable.image_block)),
            contentDescription = INACTIVE_USER,
            tint = Color.Red,
            modifier = Modifier
                .size(25.dp)
                .clip(CircleShape),
            )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Confirmar Ação")
            },
            text = {
                Text(text = "Deseja desativar esse usuário?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        inactiveUser()
                        showDialog = false
                    }) {
                    Text("Sim")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }) {
                    Text("Não")
                }
            }
        )
    }
}
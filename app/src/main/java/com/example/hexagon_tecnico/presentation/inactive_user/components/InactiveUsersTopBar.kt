package com.example.hexagon_tecnico.presentation.inactive_user.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun InactiveUsersTopBar(
    navigateToUsersScreen: () -> Unit
) {
    TopAppBar(
        title = { Text("Usuários Inativos") },
        navigationIcon = {
            IconButton(onClick = { navigateToUsersScreen }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
            }
        }
    )
}
package com.example.hexagon_tecnico.presentation.users.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    userName: String
) {
    Text(
        text = userName,
        color = Color.DarkGray,
        fontSize = 25.sp
    )
}
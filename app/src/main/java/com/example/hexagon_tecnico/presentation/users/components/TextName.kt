package com.example.hexagon_tecnico.presentation.users.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TextName(
    userName: String, style: TextStyle = MaterialTheme.typography.subtitle1
) {
    Text(
        text = userName,
        style = style,
        color = Color.DarkGray,
        fontSize = 25.sp
    )
}
package com.example.hexagon_tecnico.presentation.users.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun TextAge(
    userAge: Int, style: TextStyle = MaterialTheme.typography.body1
) {
    Text(
        text = "Idade: $userAge",
        style = style,
        color = Color.DarkGray,
        fontSize = 12.sp,
        textDecoration = TextDecoration.Underline
    )
}
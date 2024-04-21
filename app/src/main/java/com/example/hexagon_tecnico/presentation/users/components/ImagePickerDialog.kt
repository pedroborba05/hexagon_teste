package com.example.hexagon_tecnico.presentation.users.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun ImagePickerDialog(showDialog: Boolean, onDismiss: () -> Unit, imageUri: Uri?) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val context = LocalContext.current

    // Carregar a imagem de forma assíncrona quando o URI da imagem mudar
    LaunchedEffect(imageUri) {
        if (imageUri != null) {
            imageBitmap = loadImageBitmap(context, imageUri)
        } else {
            imageBitmap = null
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Selecione uma Imagem") },
            text = {
                Column {
                    if (imageBitmap != null) {
                        Image(
                            bitmap = imageBitmap!!,
                            contentDescription = "Imagem selecionada",
                            modifier = Modifier.height(200.dp)
                        )
                    } else {
                        Text("Nenhuma imagem selecionada")
                    }
                }
            },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        )
    }
}
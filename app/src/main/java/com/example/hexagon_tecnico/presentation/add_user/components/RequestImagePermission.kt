package com.example.hexagon_tecnico.presentation.add_user.components

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun RequestImagePermission(openDocument: ActivityResultLauncher<Array<String>>) {
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openDocument.launch(arrayOf("image/*"))
        } else {
            Toast.makeText(context, "Permissão para acessar imagens foi negada.", Toast.LENGTH_SHORT).show()
        }
    }

    Button(onClick = {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        requestPermissionLauncher.launch(permission)
    }) {
        Text("Selecionar Imagem")
    }
}
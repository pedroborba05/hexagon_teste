package com.example.hexagon_tecnico.presentation.update_user.components

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.R

@Composable
fun RequestToUpdateImagePermission(openDocument: ActivityResultLauncher<Array<String>>) {
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
        Row {
            Icon(
                painter = painterResource(R.drawable.icon_upload),
                contentDescription = "Ícone de imagem",
                tint = Color.White,
                modifier = Modifier
                    .size(15.dp)
                )
            Text(" Carregar imagem")
        }
    }
}
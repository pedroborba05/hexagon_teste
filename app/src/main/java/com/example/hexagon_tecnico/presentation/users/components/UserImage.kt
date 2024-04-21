package com.example.hexagon_tecnico.presentation.users.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hexagon_tecnico.R

@Composable
fun UserImage(uri: Uri?, width: Dp, height: Dp) {
    val imagePainter = // Adiciona um efeito de transição suave ao carregar a imagem
        rememberAsyncImagePainter(
            ImageRequest.Builder // Define uma imagem de placeholder
            // Define uma imagem para exibir em caso de erro
                (LocalContext.current).data(data = uri)
                .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                    crossfade(true) // Adiciona um efeito de transição suave ao carregar a imagem
                    placeholder(R.drawable.no_camera) // Define uma imagem de placeholder
                    error(R.drawable.ic_launcher_background) // Define uma imagem para exibir em caso de erro
                }).build()
        )

    Image(
        painter = imagePainter,
        contentDescription = "User Image",
        modifier = Modifier.size(width = width, height = height), // Modifique conforme necessário
        contentScale = ContentScale.Crop // Mantém a proporção da imagem
    )
}
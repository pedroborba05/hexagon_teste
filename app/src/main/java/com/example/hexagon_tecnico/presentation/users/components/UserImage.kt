package com.example.hexagon_tecnico.presentation.users.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hexagon_tecnico.R

@Composable
fun UserImage(uri: Uri?, modifier: Modifier) {
    val context = LocalContext.current
    val imagePainter: Painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context)
            .data(data = uri)
            .crossfade(true)
            .placeholder(R.drawable.no_camera)
            .error(R.drawable.no_camera)
            .build()
    )

    Image(
        painter = imagePainter,
        contentDescription = "User Image",
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}
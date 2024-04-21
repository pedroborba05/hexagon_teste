package com.example.hexagon_tecnico.presentation.users.components

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.withContext

@Composable
fun AddUsersAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addUser: (user: User) -> Unit
) {
    if (openDialog) {
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var cpf by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
        val context = LocalContext.current
        val focusRequester = FocusRequester()

        val openDocument = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.OpenDocument()
        ) { uri: Uri? ->
            uri?.let {
                // Obtenha permissões persistíveis
                val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(uri, takeFlags)

                // Atualize seu estado ou armazene o URI conforme necessário
                imageUri = uri
            }
        }

        val requestPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permissão concedida, abra a seleção de arquivos
                openDocument.launch(arrayOf("image/*"))
            } else {
                // Tratar a negação de permissão aqui
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }


        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(text = "Adicionar Usuário")
            },
            text = {
                Column {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text(text = "Nome do usuário") },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = age,
                        onValueChange = { age = it },
                        placeholder = { Text(text = "Idade") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = cpf,
                        onValueChange = { cpf = it },
                        placeholder = { Text(text = "CPF") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = city,
                        onValueChange = { city = it },
                        placeholder = { Text(text = "Cidade") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }) {
                        Text("Selecionar Imagem")
                    }
                    imageUri?.let { uri ->
                        LaunchedEffect(uri) {
                            imageBitmap = loadImageBitmap(context, uri)
                        }
                        imageBitmap?.let { img ->
                            Image(
                                bitmap = img,
                                contentDescription = "Imagem selecionada",
                                modifier = Modifier.height(200.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        val user = User(0, name = name, age = age, cpf = cpf, city = city, imageUri = imageUri, isActive = true)
                        addUser(user)
                    }
                ) {
                    Text("Adicionar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = closeDialog
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

suspend fun loadImageBitmap(context: Context, imageUri: Uri): ImageBitmap? {
    return withContext(Dispatchers.IO) {
        context.contentResolver.openFileDescriptor(imageUri, "r")?.use { pfd ->
            val bitmap = BitmapFactory.decodeFileDescriptor(pfd.fileDescriptor)
            bitmap?.asImageBitmap()
        }
    }
}

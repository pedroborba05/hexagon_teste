package com.example.hexagon_tecnico.presentation.users.components

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//
//@Composable
//fun DatePickerAddTextField(
//    label: String,
//    date: LocalDate?,
//    onDateChange: (LocalDate) -> Unit
//) {
//    val context = LocalContext.current
//    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//    var dateString by remember { mutableStateOf(date?.format(formatter) ?: "") }
//
//    TextField(
//        value = dateString,
//        onValueChange = { },
//        readOnly = true,
//        label = { Text(label) },
//        modifier = Modifier.clickable {  // Adicione o Modifier se necessário
//            val datePickerDialog = DatePickerDialog(
//                context,
//                { _, year, month, dayOfMonth ->
//                    val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
//                    dateString = selectedDate.format(formatter)
//                    onDateChange(selectedDate)
//                },
//                date?.year ?: LocalDate.now().year,
//                date?.monthValue?.minus(1) ?: (LocalDate.now().monthValue - 1),
//                date?.dayOfMonth ?: LocalDate.now().dayOfMonth
//            )
//            datePickerDialog.show()
//        }
//    )
//}
//
//@Composable
//fun AddUsersAlertDialog(
//    openDialog: Boolean,
//    closeDialog: () -> Unit,
//    addUser: (user: User) -> Unit
//) {
//    if (openDialog) {
//        var name by remember { mutableStateOf("") }
//        var age by remember { mutableStateOf(LocalDate.now()) }
//        var cpf by remember { mutableStateOf("") }
//        var city by remember { mutableStateOf("") }
//        var imageUri by remember { mutableStateOf<Uri?>(null) }
//        var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
//        val context = LocalContext.current
//        val focusRequester = FocusRequester()
//
//        val openDocument = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.OpenDocument()
//        ) { uri: Uri? ->
//            uri?.let {
//                // Obtenha permissões persistíveis
//                val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//                context.contentResolver.takePersistableUriPermission(uri, takeFlags)
//
//                // Atualize seu estado ou armazene o URI conforme necessário
//                imageUri = uri
//            }
//        }
//
//        val requestPermissionLauncher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                // Permissão concedida, abra a seleção de arquivos
//                openDocument.launch(arrayOf("image/*"))
//            } else {
//                // Tratar a negação de permissão aqui
//                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//        AlertDialog(
//            onDismissRequest = closeDialog,
//            title = {
//                Text(text = "Adicionar Usuário")
//            },
//            text = {
//                Column {
//                    TextField(
//                        value = name,
//                        onValueChange = { name = it },
//                        placeholder = { Text(text = "Nome do usuário") },
//                        modifier = Modifier.focusRequester(focusRequester)
//                    )
//                    LaunchedEffect(Unit) {
//                        coroutineContext.job.invokeOnCompletion {
//                            focusRequester.requestFocus()
//                        }
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
////                    DatePickerAddTextField(
////                        label = "Data de Nascimento",
////                        date = age,
////                        onDateChange = { newDate -> age = newDate }
////                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    TextField(
//                        value = cpf,
//                        onValueChange = { cpf = it },
//                        placeholder = { Text(text = "CPF") }
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    TextField(
//                        value = city,
//                        onValueChange = { city = it },
//                        placeholder = { Text(text = "Cidade") }
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(onClick = {
//                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//                    }) {
//                        Text("Selecionar Imagem")
//                    }
//                    imageUri?.let { uri ->
//                        LaunchedEffect(uri) {
//                            imageBitmap = loadImageBitmap(context, uri)
//                        }
//                        imageBitmap?.let { img ->
//                            Image(
//                                bitmap = img,
//                                contentDescription = "Imagem selecionada",
//                                modifier = Modifier.height(200.dp)
//                            )
//                        }
//                    }
//                }
//            },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        closeDialog()
//                        val user = User(0, name = name, age = age, cpf = cpf, city = city, imageUri = imageUri, isActive = true)
//                        addUser(user)
//                    }
//                ) {
//                    Text("Adicionar")
//                }
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = closeDialog
//                ) {
//                    Text("Cancelar")
//                }
//            }
//        )
//    }
//}
//

suspend fun loadImageBitmap(context: Context, imageUri: Uri): ImageBitmap? {
    return withContext(Dispatchers.IO) {
        context.contentResolver.openFileDescriptor(imageUri, "r")?.use { pfd ->
            val bitmap = BitmapFactory.decodeFileDescriptor(pfd.fileDescriptor)
            bitmap?.asImageBitmap()
        }
    }
}

package com.example.hexagon_tecnico.presentation.update_user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.core.Constants.Companion.UPDATE_BUTTON
import com.example.hexagon_tecnico.domain.model.User
import formatCpf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUsersContent(
    padding: PaddingValues,
    user: User,
    updateName: (name: String) -> Unit,
    updateAge: (age: String) -> Unit,
    updateCpf: (cpf: String) -> Unit,
    updateCity: (city: String) -> Unit,
//    updateImageUri: (imageUri: Uri?) -> Unit,
    updateUser: (user: User) -> Unit,
    navigateBack: () -> Unit
) {
//    var imageUri by remember { mutableStateOf(user.imageUri) }
//    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

//    val openDocument = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.OpenDocument()
//    ) { uri: Uri? ->
//        uri?.let {
//            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//            context.contentResolver.takePersistableUriPermission(uri, takeFlags)
//
//            updateImageUri = uri
//        }
//    }
//
//    val requestPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            openDocument.launch(arrayOf("image/*"))
//        } else {
//            Toast.makeText(context, "Permissão recusada", Toast.LENGTH_SHORT).show()
//        }
//    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                updateAge (millis.toBrazilianDateFormatteste())
                            }
                        showDatePickerDialog = false
                    }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = user.name,
            onValueChange = { name ->
                updateName(name)
            },
            label = { Text("Nome") },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        TextField(
            value = user.age,
            onValueChange = { age -> updateAge(age) },
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused) {
                        showDatePickerDialog = true
                        focusManager.clearFocus(force = true)
                    }
                },
            label = { Text("Data de nascimento") },
            readOnly = true
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        TextField(
            value = user.cpf,
            onValueChange = { cpf ->
                updateCpf(cpf)
            },
            label = { Text("CPF") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            visualTransformation =  formatCpf()
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        TextField(
            value = user.city,
            onValueChange = { city ->
                updateCity(city)
            },
            label = { Text("Cidade") },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
//        Button(onClick = {
//            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//        }) {
//            Text("Selecionar Imagem")
//        }
//        imageUri?.let { uri ->
//            LaunchedEffect(uri) {
//                imageBitmap = loadImageBitmap(context, uri)
//            }
//            imageBitmap?.let { img ->
//                Image(
//                    bitmap = img,
//                    contentDescription = "Imagem selecionada",
//                    modifier = Modifier.height(200.dp)
//                )
//            }
//        }
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Button(
            onClick = {
                updateUser(user)
                navigateBack()
            }
        ) {
            Text(
                text = UPDATE_BUTTON
            )
        }
    }
}

fun Long.toBrazilianDateFormatteste(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}
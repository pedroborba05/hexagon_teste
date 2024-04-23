
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.presentation.update_user.components.AddUsersTopBar
import com.example.hexagon_tecnico.presentation.users.UsersViewModel
import com.example.hexagon_tecnico.presentation.users.components.loadImageBitmap
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var age by remember() { mutableStateOf("")}
    var cpf by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current
    DatePicker(state = datePickerState)

    val context = LocalContext.current

    val openDocument = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, takeFlags)

            imageUri = uri
        }
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openDocument.launch(arrayOf("image/*"))
        } else {
            Toast.makeText(context, "Permissão recusada", Toast.LENGTH_SHORT).show()
        }
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                age = millis.toBrazilianDateFormat()
                            }
                        showDatePickerDialog = false
                    }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        topBar = {
            AddUsersTopBar(navigateBack = navigateBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = age,
                onValueChange = { age = it },
                Modifier
                    .onFocusChanged {
                        if (it.isFocused) {
                            showDatePickerDialog = true
                            focusManager.clearFocus(force = true)
                        }
                    },
                label = {
                    Text("Data de Nascimento")
                },
                readOnly = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = cpf,
                onValueChange = {
                    if (it.length <= 11) {
                        cpf = it
                    }
                },
                label = { Text("CPF") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                visualTransformation =  formatCpf()

            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Cidade") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )

            )
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
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val user = User(0, name, age, cpf, city, imageUri, isActive = true)
                    viewModel.addUser(user)
                    Toast.makeText(context, "Usuário adicionado!", Toast.LENGTH_SHORT).show()

                    name = ""; age = ""; cpf = ""; city = ""; imageUri = null
                }
            ) {
                Text("Adicionar Usuário")
            }
        }
    }
}

fun Long.toBrazilianDateFormat(
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

fun formatCpf(): VisualTransformation {
    return VisualTransformation { text ->
        val digits = text.filter { it.isDigit() }
        var out = ""
        for (i in digits.indices) {
            when (i) {
                3, 6 -> out += ".${digits[i]}"
                9 -> out += "-${digits[i]}"
                else -> out += digits[i]
            }
            if (i == 10) break
        }

        val formattedText = AnnotatedString(out)
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 6) return offset + 1
                if (offset <= 9) return offset + 2
                if (offset <= 11) return offset + 3
                return 14
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 7) return offset - 1
                if (offset <= 11) return offset - 2
                if (offset <= 14) return offset - 3
                return 11
            }
        }

        TransformedText(formattedText, offsetMapping)
    }
}
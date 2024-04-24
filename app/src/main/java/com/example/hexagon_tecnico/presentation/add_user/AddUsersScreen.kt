
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.core.Constants.Companion.ADD_USER
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.presentation.add_user.components.RequestImagePermission
import com.example.hexagon_tecnico.presentation.update_user.components.AddUsersTopBar
import com.example.hexagon_tecnico.presentation.users.UsersViewModel
import com.example.hexagon_tecnico.ui.theme.BackgroundTextField
import com.example.hexagon_tecnico.util.Converters.Companion.formatCpf
import com.example.hexagon_tecnico.util.Converters.Companion.loadImageBitmap
import com.example.hexagon_tecnico.util.Converters.Companion.toBrazilianDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUsersScreen(
    viewModel: UsersViewModel = hiltViewModel()
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
        topBar = { AddUsersTopBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            imageUri?.let { uri ->
                LaunchedEffect(uri) {
                    imageBitmap = loadImageBitmap(context, uri)
                }
                imageBitmap?.let { img ->
                    Image(
                        bitmap = img,
                        contentDescription = "Imagem selecionada",
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BackgroundTextField,
                    textColor = Color.Black
                ),
                trailingIcon = {
                    Icon(
                        Icons.Default.Person, contentDescription = null,
                        )
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = age,
                onValueChange = { age = it },
                Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) {
                            showDatePickerDialog = true
                            focusManager.clearFocus(force = true)
                        }
                    },
                label = {
                    Text("Data de Nascimento")
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BackgroundTextField,
                    textColor = Color.Black
                ),
                readOnly = true,
                trailingIcon = {
                    Icon(
                        Icons.Default.DateRange, contentDescription = null,
                        Modifier.clickable { showDatePickerDialog = true })
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = cpf,
                onValueChange = { if (it.length <= 11) cpf = it },
                label = { Text("CPF") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BackgroundTextField,
                    textColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = formatCpf()
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Cidade") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BackgroundTextField,
                    textColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        Icons.Default.LocationOn, contentDescription = null,
                    )
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
            Spacer(modifier = Modifier.height(12.dp))
            RequestImagePermission(openDocument)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val user = User(0, name, age, cpf, city, imageUri, isActive = true)
                    viewModel.addUser(user)
                    Toast.makeText(context, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    name = ""; age = ""; cpf = ""; city = ""; imageUri = null
                },
            ) {
                Text(ADD_USER)
            }
        }
    }
}
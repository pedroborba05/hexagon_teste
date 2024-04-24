package com.example.hexagon_tecnico.presentation.update_user.components

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.core.Constants.Companion.UPDATE_BUTTON
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.ui.theme.BackgroundTextField
import com.example.hexagon_tecnico.util.Converters.Companion.formatCpf
import com.example.hexagon_tecnico.util.Converters.Companion.toBrazilianDateFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUsersContent(
    padding: PaddingValues,
    user: User,
    updateName: (name: String) -> Unit,
    updateAge: (age: String) -> Unit,
    updateCpf: (cpf: String) -> Unit,
    updateCity: (city: String) -> Unit,
    updateImageUri: (imageUri: Uri?) -> Unit,
    updateUser: (user: User) -> Unit,
    navigateBack: () -> Unit
) {
//    var imageUri by remember { mutableStateOf(user.imageUri) }
//    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                updateAge (millis.toBrazilianDateFormat())
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
            modifier = Modifier
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BackgroundTextField,
                textColor = Color.Black
            ),
            readOnly = true,
            trailingIcon = {
                Icon(
                    Icons.Default.DateRange, contentDescription = null,
                    Modifier.clickable { showDatePickerDialog = true })
            },
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BackgroundTextField,
                textColor = Color.Black
            ),
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BackgroundTextField,
                textColor = Color.Black
            ),
            trailingIcon = {
                Icon(
                    Icons.Default.LocationOn, contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Button(
            onClick = {
                updateUser(user)
                navigateBack()
                Toast.makeText(context, "Usuário editado com sucesso!", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(
                text = UPDATE_BUTTON
            )
        }
    }
}
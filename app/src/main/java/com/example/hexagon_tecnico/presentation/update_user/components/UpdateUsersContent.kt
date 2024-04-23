package com.example.hexagon_tecnico.presentation.update_user.components

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.core.Constants.Companion.CITY
import com.example.hexagon_tecnico.core.Constants.Companion.CPF
import com.example.hexagon_tecnico.core.Constants.Companion.UPDATE_BUTTON
import com.example.hexagon_tecnico.core.Constants.Companion.USER_NAME
import com.example.hexagon_tecnico.domain.model.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerInput(
    date: LocalDate?,
    onDateChange: (LocalDate) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var dateString by remember(date) { mutableStateOf(date?.format(formatter) ?: "") }

    TextField(
        value = dateString,
        onValueChange = { },
        readOnly = true,
        label = { Text(label) },
        placeholder = { Text("dd/mm/aaaa") },
        modifier = modifier.clickable {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    dateString = selectedDate.format(formatter)
                    onDateChange(selectedDate)
                },
                date?.year ?: LocalDate.now().year,
                date?.monthValue?.minus(1) ?: LocalDate.now().monthValue - 1,
                date?.dayOfMonth ?: LocalDate.now().dayOfMonth
            ).show()
        }
    )
}

@Composable
fun UpdateUsersContent(
    padding: PaddingValues,
    user: User,
    updateName: (name: String) -> Unit,
    updateAge: (age: String) -> Unit,
    updateCpf: (cpf: String) -> Unit,
    updateCity: (city: String) -> Unit,
//    updateImageUri: (imageUri: Uri) -> Unit,
    updateUser: (user: User) -> Unit,
    navigateBack: () -> Unit
) {
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
            placeholder = {
                Text(
                    text = USER_NAME
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
//        DatePickerInput(
//            date = user.age,
//            onDateChange = { age ->
//                updateAge(age)
//            },
//            label = "Data de Nascimento"
//        )
        TextField(
            value = user.cpf,
            onValueChange = { cpf ->
                updateCpf(cpf)
            },
            placeholder ={
                Text(
                    text = CPF
                )
            }
        )
        TextField(
            value = user.city,
            onValueChange = { city ->
                updateCity(city)
            },
            placeholder ={
                Text(
                    text = CITY
                )
            }
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
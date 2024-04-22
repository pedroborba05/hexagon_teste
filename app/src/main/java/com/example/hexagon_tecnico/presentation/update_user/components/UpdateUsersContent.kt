package com.example.hexagon_tecnico.presentation.update_user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hexagon_tecnico.core.Constants.Companion.AGE
import com.example.hexagon_tecnico.core.Constants.Companion.CITY
import com.example.hexagon_tecnico.core.Constants.Companion.CPF
import com.example.hexagon_tecnico.core.Constants.Companion.UPDATE_BUTTON
import com.example.hexagon_tecnico.core.Constants.Companion.USER_NAME
import com.example.hexagon_tecnico.domain.model.User

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
//        Spacer(
//            modifier = Modifier.height(8.dp)
//        )
        TextField(
            value = user.age,
            onValueChange = { age ->
                updateAge(age)
            },
            placeholder = {
                Text(
                    text = AGE
                )
            }
        )
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
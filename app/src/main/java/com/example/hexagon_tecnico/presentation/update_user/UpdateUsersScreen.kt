package com.example.hexagon_tecnico.presentation.update_user

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.presentation.update_user.components.UpdateUsersContent
import com.example.hexagon_tecnico.presentation.update_user.components.UpdateUsersTopBar
import com.example.hexagon_tecnico.presentation.users.UsersViewModel

@Composable
fun UpdateUsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    userId: Int,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getUser(userId)
    }
    Scaffold(
        topBar = {
            UpdateUsersTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            UpdateUsersContent(
                padding = padding,
                user = viewModel.user,
                updateName = { name ->
                    viewModel.updateName(name)
                },
                updateAge = { age ->
                    viewModel.updateAge(age)
                },
                updateCpf = { cpf ->
                    viewModel.updateCpf(cpf)
                },
                updateCity = { city ->
                    viewModel.updateCity(city)
                },
//                updateImageUri = { imageUri ->
//                    viewModel.updateImageUri(imageUri)
//                },
                updateUser = { user ->
                    viewModel.updateUser(user)
                },
                navigateBack = navigateBack
            )
        }
    )
}
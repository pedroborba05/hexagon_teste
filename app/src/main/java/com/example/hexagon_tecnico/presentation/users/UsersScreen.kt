package com.example.hexagon_tecnico.presentation.users

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.presentation.users.components.AddUsersAlertDialog
import com.example.hexagon_tecnico.presentation.users.components.AddUsersFloatingActionButton
import com.example.hexagon_tecnico.presentation.users.components.UsersContent
import com.example.hexagon_tecnico.presentation.users.components.UsersTopBar


@Composable
@ExperimentalMaterialApi
fun UsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    navigateToUpdateUserScreen: (userId: Int) -> Unit,
    navigateToInactiveUsersScreen: () -> Unit
) {
    val users by viewModel.activeUsers.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            UsersTopBar()
        },
        content = { padding ->
            Column {
                Button(
                    onClick = navigateToInactiveUsersScreen
                ) {
                    Text("Usuários inativos")
                }
                // Conteúdo principal com a lista de usuários
                UsersContent(
                    padding = padding,
                    users = users,
                    inativeUser = { user ->
                        viewModel.inativeUser(user)
                    },
                    navigateToUpdateUserScreen = navigateToUpdateUserScreen
                )
            }
            // Diálogo para adicionar usuários
            AddUsersAlertDialog(
                openDialog = viewModel.openDialog,
                closeDialog = {
                    viewModel.closeDialog()
                },
                addUser = { user ->
                    viewModel.addUser(user)
                }
            )
        },
        floatingActionButton = {
            AddUsersFloatingActionButton(
                openDialog = {
                    viewModel.openDialog()
                }
            )
        }
    )
}

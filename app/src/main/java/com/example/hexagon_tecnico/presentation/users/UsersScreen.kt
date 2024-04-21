package com.example.hexagon_tecnico.presentation.users

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
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
    navigateToUpdateUserScreen: (bookId: Int) -> Unit
) {

    val users by viewModel.activeUsers.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            UsersTopBar()
        },
        content = { padding ->
            UsersContent(
                padding = padding,
                users = users,
                inativeUser = { user ->
                    viewModel.inativeUser(user)
                },
                navigateToUpdateUserScreen = navigateToUpdateUserScreen
            )
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
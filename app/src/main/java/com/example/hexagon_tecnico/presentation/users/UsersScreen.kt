package com.example.hexagon_tecnico.presentation.users

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.presentation.users.components.AddUsersFloatingActionButton
import com.example.hexagon_tecnico.presentation.users.components.UsersContent
import com.example.hexagon_tecnico.presentation.users.components.UsersTopBar


@Composable
@ExperimentalMaterialApi
fun UsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    navigateToUpdateUserScreen: (userId: Int) -> Unit,
    navigateToInactiveUsersScreen: () -> Unit,
    navigateToAddUsersScreen: () -> Unit
) {
    val users by viewModel.activeUsers.observeAsState(initial = emptyList())
    
    LaunchedEffect(key1 = users) {

    }

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
                UsersContent(
                    padding = padding,
                    users = users,
                    inativeUser = { user ->
                        viewModel.inativeUser(user)
                    },
                    navigateToUpdateUserScreen = navigateToUpdateUserScreen
                )
            }
        },
        floatingActionButton = {
            AddUsersFloatingActionButton(
                openDialog = {
                    navigateToAddUsersScreen()
                }
            )
        }
    )
}

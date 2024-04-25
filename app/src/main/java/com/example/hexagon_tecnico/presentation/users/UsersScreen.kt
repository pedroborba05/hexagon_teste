package com.example.hexagon_tecnico.presentation.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.presentation.users.components.ReturnWithoutUsers
import com.example.hexagon_tecnico.presentation.users.components.UsersContent
import com.example.hexagon_tecnico.presentation.users.components.UsersTopBar


@Composable
@ExperimentalMaterialApi
fun UsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    navigateToUpdateUserScreen: (userId: Int) -> Unit
) {
    val users by viewModel.activeUsers.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            UsersTopBar()
        },
        content = { padding ->
            if (users.isEmpty()) {
                ReturnWithoutUsers()
            } else {
                Column(modifier = Modifier.padding(padding)) {
                    UsersContent(
                        padding = padding,
                        users = users,
                        inativeUser = { user ->
                            viewModel.inativeUser(user)
                        },
                        navigateToUpdateUserScreen = navigateToUpdateUserScreen
                    )
                }
            }
        }
    )
}

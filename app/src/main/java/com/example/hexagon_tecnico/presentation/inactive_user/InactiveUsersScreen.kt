package com.example.hexagon_tecnico.presentation.inactive_user

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.presentation.inactive_user.components.InactiveUsersTopBar
import com.example.hexagon_tecnico.presentation.inactive_user.components.UsersInactiveContent
import com.example.hexagon_tecnico.presentation.users.UsersViewModel

@Composable
@ExperimentalMaterialApi
fun InactiveUsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    navigateToUsersScreen: () -> Unit
    ) {

    val usersInactive by viewModel.inactiveUsers.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            InactiveUsersTopBar(navigateToUsersScreen = navigateToUsersScreen)
        },
        content = { padding ->
            UsersInactiveContent(
                padding = padding,
                users = usersInactive,
                activeUsers = {
                    user -> viewModel.activeUser(user)
                }
            )
        },
    )
}

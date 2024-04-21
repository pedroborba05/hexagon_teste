package com.example.hexagon_tecnico.presentation.inactiveusers

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.presentation.inactiveusers.components.InactiveUsersTopBar
import com.example.hexagon_tecnico.presentation.inactiveusers.components.UsersInactiveContent
import com.example.hexagon_tecnico.presentation.users.UsersViewModel

@Composable
@ExperimentalMaterialApi
fun InactiveUsersScreen(
    viewModel: UsersViewModel = hiltViewModel()
) {
    val users by viewModel.inactiveUsers.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            InactiveUsersTopBar()
        },
        content = { padding ->
            UsersInactiveContent(
                padding = padding,
                users = users,
                activeUser = {user: User ->
                }
            )
        },
    )
}
